package space.xiami.project.genshinmodel.factory;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.stereotype.Component;
import space.xiami.project.genshincommon.NumberRange;
import space.xiami.project.genshindataviewer.domain.model.LevelProperty;
import space.xiami.project.genshindataviewer.domain.model.TeamResonance;
import space.xiami.project.genshindataviewer.domain.model.TeamResonanceGroup;
import space.xiami.project.genshinmodel.domain.avatar.Avatar;
import space.xiami.project.genshinmodel.domain.effect.resonance.TeamResonanceAffix;
import space.xiami.project.genshinmodel.domain.effect.resonance.TeamResonanceEffect;
import space.xiami.project.genshinmodel.domain.effect.skill.active.ActiveSkillAffix;
import space.xiami.project.genshinmodel.domain.effect.skill.active.ActiveSkillEffect;
import space.xiami.project.genshinmodel.domain.effect.skill.passive.PassiveSkillAffix;
import space.xiami.project.genshinmodel.domain.effect.skill.passive.PassiveSkillEffect;
import space.xiami.project.genshinmodel.domain.effect.talent.TalentAffix;
import space.xiami.project.genshinmodel.domain.effect.talent.TalentEffect;
import space.xiami.project.genshinmodel.domain.entry.attributes.AbstractAttribute;
import space.xiami.project.genshinmodel.domain.entry.attributes.CRITDMG;
import space.xiami.project.genshinmodel.domain.entry.attributes.CRITRate;
import space.xiami.project.genshinmodel.domain.entry.attributes.EnergyRecharge;
import space.xiami.project.genshinmodel.domain.entry.bonus.AbstractBonus;
import space.xiami.project.genshinmodel.domain.equipment.skill.active.ActiveSkill;
import space.xiami.project.genshinmodel.domain.equipment.skill.passive.PassiveSkill;
import space.xiami.project.genshinmodel.domain.equipment.talent.Talent;
import space.xiami.project.genshinmodel.rest.AvatarRestTemplate;
import space.xiami.project.genshinmodel.util.ConfigUtil;
import space.xiami.project.genshinmodel.util.converter.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Xiami
 */
@Component
public class AvatarFactory {

    @Resource
    private AvatarRestTemplate avatarRestTemplate;

    public Avatar getByName(String name, Byte elementalType, String level, Map<String, Integer> skillLevel, Integer talentLevel){
        return convertAvatar(avatarRestTemplate.getByName(name, elementalType), level, skillLevel, talentLevel);
    }

    public Avatar getById(Long id, Byte elementalType, String level, Map<String, Integer> skillLevel, Integer talentLevel){
        return convertAvatar(avatarRestTemplate.getById(id, elementalType), level, skillLevel, talentLevel);
    }

    public Map<String, NumberRange<Integer>> getSkillLevelRangeByName(String name){
        return avatarRestTemplate.getSkillLevelRangeByName(name);
    }

    public Map<String, NumberRange<Integer>> getSkillLevelRangeById(Long id){
        return avatarRestTemplate.getSkillLevelRangeById(id);
    }

    public List<TeamResonanceEffect> matchTeamResonance(Map<Long, Byte> id2appoint){
        List<String> idAppointList = new ArrayList<>(id2appoint.size());
        id2appoint.forEach((id, elemType) -> {
            idAppointList.add(id + (elemType != null ? ("@"+ elemType) : ""));
        });
        TeamResonanceGroup teamResonanceGroup = avatarRestTemplate.matchTeamResonance(StringUtils.join(idAppointList, '~'));
        List<TeamResonanceEffect> effects = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(teamResonanceGroup.getTeamResonances())){
            List<TeamResonance> teamResonances = teamResonanceGroup.getTeamResonances();
            teamResonances.forEach(teamResonance -> {
                TeamResonanceAffix affix = AffixConverter.convertTeamResonanceAffix(teamResonance);
                TeamResonanceEffect effect = EffectConverter.toTeamResonanceEffect(affix);
                effects.add(effect);
            });
        }
        return effects;
    }

    private static Byte getAvatarElementalType(space.xiami.project.genshindataviewer.domain.model.Avatar avatar){
        Byte elementalType = null;
        if(CollectionUtils.isNotEmpty(avatar.getSkillActive().values())){
            for(List<space.xiami.project.genshindataviewer.domain.model.Avatar.ActiveSkill> activeSkills : avatar.getSkillActive().values()) {
                if(CollectionUtils.isNotEmpty(activeSkills)){
                    for(space.xiami.project.genshindataviewer.domain.model.Avatar.ActiveSkill activeSkill : activeSkills) {
                        if(activeSkill.getCostElemType() != null){
                            elementalType = ElementalTypeConverter.string2Byte(activeSkill.getCostElemType());
                        }
                    }
                }
            }
        }
        return elementalType;
    }

    private static Avatar convertAvatar(space.xiami.project.genshindataviewer.domain.model.Avatar from, String level, Map<String, Integer> skillLevelMap, Integer talentLevel){
        Avatar to = new Avatar();
        to.setId(from.getId());
        to.setName(from.getName());
        to.setElementalType(getAvatarElementalType(from));
        to.setWeaponType(WeaponTypeConverter.string2Byte(from.getWeaponType()));
        to.setLevel(level);
        // activeSkill + passiveSkill + talent
        List<ActiveSkill> activeSkills = new ArrayList<>();
        from.getSkillActive().values().forEach(skills -> {
            skills.forEach(skill -> {
                ActiveSkillAffix convertedAffix = AffixConverter.convertActiveSkillAffix(skill, skillLevelMap.get(skill.getName()));
                ActiveSkill convertedSkill = new ActiveSkill();
                ActiveSkillEffect skillEffect = EffectConverter.toActiveSkillEffect(convertedSkill, convertedAffix);
                convertedSkill.setEffects(Collections.singletonList(skillEffect));
                activeSkills.add(convertedSkill);
            });
        });
        to.setActiveSkills(activeSkills);
        List<PassiveSkill> passiveSkills = new ArrayList<>();
        from.getSkillPassive().values().forEach(skills -> {
            skills.forEach(skill -> {
                PassiveSkillAffix convertedAffix = AffixConverter.convertPassiveSkillAffix(skill, skillLevelMap.get(skill.getName()));
                PassiveSkill convertedSkill = new PassiveSkill();
                PassiveSkillEffect skillEffect = EffectConverter.toPassiveSkillEffect(convertedSkill, convertedAffix);
                convertedSkill.setEffects(Collections.singletonList(skillEffect));
                passiveSkills.add(convertedSkill);
            });
        });
        to.setPassiveSkills(passiveSkills);
        List<Talent> talents = new ArrayList<>();
        from.getTalents().values().forEach(talentList -> {
            talentList.stream().limit(talentLevel).forEach(skill -> {
                TalentAffix convertedAffix = AffixConverter.convertTalentAffix(skill);
                Talent convertedTalent = new Talent();
                TalentEffect talentEffect = EffectConverter.toTalentEffect(convertedTalent, convertedAffix);
                convertedTalent.setEffects(Collections.singletonList(talentEffect));
                talents.add(convertedTalent);
            });
        });
        to.setTalents(talents);
        // levelProperty
        LevelProperty levelProperty = from.getAvatarProperties().stream()
                .filter(lp -> lp.getLevel() != null && lp.getLevel().equals(level)).findAny().get();
        // 基础面板 + 与突破加成
        List<AbstractAttribute> attributes = new ArrayList<>();
        List<AbstractBonus> bonuses = new ArrayList<>();
        attributes.add(new CRITRate(from.getCritical()));
        attributes.add(new CRITDMG(from.getCriticalHurt()));
        attributes.add(new EnergyRecharge(from.getChargeEfficiency()));
        levelProperty.getProperties()
                .forEach(v -> {
                    AbstractBonus bonus = EquipPropTypeConverter.property2Bonus(v.getPropType(), v.getValue());
                    if(bonus != null){
                        if(ConfigUtil.getConfig().getAvatarBonusToAttribute().contains(bonus.getName())){
                            attributes.add(AttributeLevelPropTypeConverter.property2Bonus(v.getPropType(), v.getValue()));
                        }else{
                            bonuses.add(bonus);
                        }
                    }
                });
        to.setAttributes(attributes);
        to.setBonuses(bonuses);
        return to;
    }
}
