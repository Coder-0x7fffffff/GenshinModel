package space.xiami.project.genshinmodel.factory;

import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import space.xiami.project.genshindataviewer.domain.model.EquipAffix;
import space.xiami.project.genshinmodel.domain.effect.Effect;
import space.xiami.project.genshinmodel.domain.effect.reliquaries.ReliquarySetAffix;
import space.xiami.project.genshinmodel.domain.entry.bonus.AbstractBonus;
import space.xiami.project.genshinmodel.domain.equipment.reliquary.Reliquary;
import space.xiami.project.genshinmodel.domain.equipment.reliquary.ReliquarySet;
import space.xiami.project.genshinmodel.rest.ReliquaryRestTemplate;
import space.xiami.project.genshinmodel.util.converter.AffixConverter;
import space.xiami.project.genshinmodel.util.converter.EffectConverter;
import space.xiami.project.genshinmodel.util.converter.ReliquaryTypeConverter;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Xiami
 */
@Component
public class ReliquaryFactory {

    private Logger log = LoggerFactory.getLogger(ReliquaryFactory.class);

    @Resource
    private ReliquaryRestTemplate reliquaryRestTemplate;

    public Reliquary getById(Long id, Integer level, List<AbstractBonus> bonuses){
        return convertReliquary(reliquaryRestTemplate.getById(id), level, bonuses);
    }

    public Reliquary getByName(String name, Integer level, List<AbstractBonus> bonuses){
        return convertReliquary(reliquaryRestTemplate.getByName(name), level, bonuses);
    }

    public ReliquarySet getSetById(Long id){
        return convertReliquarySet(reliquaryRestTemplate.getSetById(id), true);
    }

    public Map list(){
        return reliquaryRestTemplate.list();
    }

    public Map listSet(){
        return reliquaryRestTemplate.listSet();
    }

    private Reliquary convertReliquary(space.xiami.project.genshindataviewer.domain.model.Reliquary from, Integer level, List<AbstractBonus> bonuses) {
        Reliquary to = new Reliquary();
        to.setName(from.getName());
        to.setBonuses(bonuses);
        to.setId(from.getId());
        to.setSetId(from.getSetId());
        to.setLevel(level);
        to.setReliquaryType(ReliquaryTypeConverter.string2Byte(from.getEquipType()));
        return to;
    }

    private ReliquarySet convertReliquarySet(space.xiami.project.genshindataviewer.domain.model.ReliquarySet from, boolean fillEffectAndReliquaries) {
        ReliquarySet to = new ReliquarySet();
        Set<String> nameSet = from.getGroupEquipAffix().values()
                .stream()
                .map(EquipAffix::getName)
                .collect(Collectors.toSet());
        if(nameSet.size() >= 1) {
            if(nameSet.size() > 1){
                log.warn("ReliquarySet's equipAffix have different name, {}", nameSet);
            }
            to.setName(new ArrayList<>(nameSet).get(0));
        }
        if(fillEffectAndReliquaries){
            // 获取所有圣遗物效果
            to.setEffects(
                    from.getGroupEquipAffix().values().stream().map(equipAffix -> {
                        ReliquarySetAffix reliquarySetAffix = AffixConverter.convertReliquarySetAffix(equipAffix);
                        return EffectConverter.toReliquarySetEffect(to, reliquarySetAffix);
                    }).collect(Collectors.toList())
            );
            to.setReliquaries(from.getReliquaries().stream().map(v -> convertReliquary(v, null, null)).collect(Collectors.toList()));
        }
        return to;
    }

    public List<ReliquarySet> createReliquarySet(List<Reliquary> reliquaries){
        List<ReliquarySet> reliquarySetList = new ArrayList<>();
        Set<Long> setIds = reliquaries.stream().map(Reliquary::getSetId).collect(Collectors.toSet());
        // 获取圣遗物并填充对应效果
        setIds.stream().map(setId -> reliquaryRestTemplate.getSetById(setId)).forEach(data -> {
            ReliquarySet reliquarySet = convertReliquarySet(data, false);
            List<Reliquary> targetReliquaries = reliquaries.stream()
                    .filter(v->data.getSetId().equals(v.getSetId()))
                    .collect(Collectors.toList());
            reliquarySet.setReliquaries(targetReliquaries);
            List<Effect> effects = new ArrayList<>();
            data.getGroupEquipAffix().forEach((limit, equipAffix) -> {
                if(limit <= targetReliquaries.size()){
                    ReliquarySetAffix reliquarySetAffix = AffixConverter.convertReliquarySetAffix(equipAffix);
                    effects.add(EffectConverter.toReliquarySetEffect(reliquarySet, reliquarySetAffix));
                }
            });
            reliquarySet.setEffects(effects);
            if(effects.size() > 0){
                reliquarySetList.add(reliquarySet);
            }
        });
        return reliquarySetList;
    }
}
