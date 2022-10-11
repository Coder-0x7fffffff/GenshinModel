package space.xiami.project.genshinmodel.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import space.xiami.project.genshincommon.exception.EntryException;
import space.xiami.project.genshinmodel.domain.avatar.Avatar;
import space.xiami.project.genshinmodel.domain.context.CalculateAttributeContext;
import space.xiami.project.genshinmodel.domain.context.CalculateAttributeResult;
import space.xiami.project.genshinmodel.domain.effect.EffectMethodEnum;
import space.xiami.project.genshinmodel.domain.entry.Entry;
import space.xiami.project.genshinmodel.domain.entry.attributes.AbstractAttribute;
import space.xiami.project.genshinmodel.domain.entry.attributes.Attributes;
import space.xiami.project.genshinmodel.domain.entry.bonus.AbstractAddAttributeBonus;
import space.xiami.project.genshinmodel.domain.entry.bonus.AbstractAttributeBonus;
import space.xiami.project.genshinmodel.domain.entry.bonus.AbstractBonus;
import space.xiami.project.genshinmodel.domain.entry.bonus.AbstractMultiplyAttributeBonus;
import space.xiami.project.genshinmodel.domain.equipment.Equipment;
import space.xiami.project.genshinmodel.executor.DefaultEffectExecutor;
import space.xiami.project.genshinmodel.util.AvatarUtils;
import space.xiami.project.genshinmodel.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Xiami
 */
@Component
public class CalculateManager {

    private Logger log = LoggerFactory.getLogger(CalculateManager.class);

    @Resource
    private DefaultEffectExecutor defaultEffectExecutor;

    public CalculateAttributeResult calculateAttribute(CalculateAttributeContext context) {
        // 计算基础属性
        calculateBaseAttribute(context);
        // 计算加成
        calculateBonus(context);
        // 执行所有effect
        executeEffect(context);
        // 结算面板
        summaryAttribute(context);
        return context.buildResult();
    }

    /**
     * 角色基础属性+武器主属性
     * @param context context
     */
    public void calculateBaseAttribute(CalculateAttributeContext context){
        context.setRealTimeAttributes(
            context.getAvatars().stream().collect(Collectors.toMap(
                Avatar::getName,
                avatar -> {
                    Attributes attributes = new Attributes();
                    avatar.getAttributes().forEach(attribute -> {
                        try{
                            attributes.setEntryValue(attribute.getName(), attribute.getValue());
                        } catch (EntryException e) {
                            log.error("Entry error", e);
                        }
                    });
                    if(avatar.getWeapons()!=null){
                        avatar.getWeapons().forEach(weapon -> {
                            try{
                                AbstractAttribute mainAttribute = weapon.getMainAttribute();
                                Entry targetEntry = attributes.getEntry(mainAttribute.getName());
                                targetEntry.setValue(targetEntry.getValue() + mainAttribute.getValue());
                            } catch (EntryException e) {
                                log.error("Entry error", e);
                            }
                        });
                    }
                    return attributes;
                })
            )
        );
    }

    /**
     * 角色+武器+圣遗物+技能+命座 -> avatar.bonus + equipment.bonus + equipment.effect.affix.bonus
     * @param context context
     */
    private void calculateBonus(CalculateAttributeContext context) {
        Map<String, Map<String, AbstractBonus>> bonuses = new HashMap<>();
        context.getAvatars().forEach(avatar -> {
            List<AbstractBonus> bonusList = new ArrayList<>();
            CollectionUtils.addAllList(bonusList, avatar.getBonuses());
            List<Equipment> equipments = new ArrayList<>();
            CollectionUtils.addAllList(equipments,
                    avatar.getWeapons(),
                    avatar.getReliquaries(),
                    avatar.getReliquarySets(),
                    avatar.getActiveSkills(),
                    avatar.getPassiveSkills(),
                    avatar.getTalents()
            );
            equipments.forEach(equipment -> {
                CollectionUtils.addAllList(bonusList, equipment.getBonuses());
                if(equipment.getEffects()!=null){
                    equipment.getEffects().forEach(effect -> {
                        if(effect.getAffix() != null){
                            CollectionUtils.addAllList(bonusList, effect.getAffix().getBonuses());
                        }
                    });
                }
            });
            bonuses.put(avatar.getName(), sumBonus(bonusList));
        });
        context.setBonuses(bonuses);
    }

    /**
     * 执行所有的effect的onCalculateAttribute
     * @param context context
     */
    private void executeEffect(CalculateAttributeContext context) {
        defaultEffectExecutor.execute(
                AvatarUtils.getAllEffect(context.getAvatars()),
                context,
                EffectMethodEnum.CALCULATE_ATTRIBUTE);
    }

    /**
     * 结算面板
     * @param context context
     */
    private void summaryAttribute(CalculateAttributeContext context){
        context.getAvatars().forEach(avatar -> {
            Attributes attributes = context.getRealTimeAttributes().get(avatar.getName());
            Map<AbstractAttribute, List<AbstractAttributeBonus>> attributeBonusMap = new HashMap<>();
            context.getBonuses().get(avatar.getName()).values().forEach(bonus -> {
                if(bonus instanceof AbstractAddAttributeBonus) {
                    AbstractAddAttributeBonus attributeBonus = (AbstractAddAttributeBonus) bonus;
                    attributeBonusMap.computeIfAbsent(
                            attributeBonus.relatedAttribute(attributes),
                            v -> new ArrayList<>()
                    ).add(attributeBonus);
                } else if(bonus instanceof AbstractMultiplyAttributeBonus) {
                    AbstractMultiplyAttributeBonus attributeBonus = (AbstractMultiplyAttributeBonus) bonus;
                    attributeBonusMap.computeIfAbsent(
                            attributeBonus.relatedAttribute(attributes),
                            v -> new ArrayList<>()
                    ).add(attributeBonus);
                }
            });
            // ori = ori * (1+mb) + ab = ori + (ori * mb0) + (ori * mb1) + ab0 + ab1
            attributeBonusMap.forEach((attribute, bonuses) ->
                    attribute.setValue(
                            attribute.getValue() +
                            bonuses.stream().mapToDouble(bonus -> {
                                double bonusValue = 0.0;
                                if(bonus instanceof AbstractAddAttributeBonus){
                                    bonusValue = bonus.getValue();
                                }else if(bonus instanceof AbstractMultiplyAttributeBonus){
                                    bonusValue = attribute.getValue() * bonus.getValue();
                                }
                                return bonusValue;
                            }).sum()
                    )
            );
        });
    }

    private Map<String, AbstractBonus> sumBonus(List<AbstractBonus> bonuses){
        Map<String, AbstractBonus> bonusMap = new HashMap<>();
        if(bonuses != null){
            bonuses.forEach(bonus -> {
                // 累加加成数值
                AbstractBonus ori = bonusMap.get(bonus.getName());
                if(ori == null){
                    bonusMap.put(bonus.getName(), bonus);
                }else{
                    ori.setValue(ori.getValue() + bonus.getValue());
                }
            });
        }
        return bonusMap;
    }
}
