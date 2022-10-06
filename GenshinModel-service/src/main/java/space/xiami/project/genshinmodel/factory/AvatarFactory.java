package space.xiami.project.genshinmodel.factory;

import org.springframework.stereotype.Component;
import space.xiami.project.genshincommon.NumberRange;
import space.xiami.project.genshindataviewer.domain.model.LevelProperty;
import space.xiami.project.genshinmodel.domain.avatar.Avatar;
import space.xiami.project.genshinmodel.domain.effect.skill.active.ActiveSkillAffix;
import space.xiami.project.genshinmodel.domain.effect.skill.active.ActiveSkillEffect;
import space.xiami.project.genshinmodel.domain.effect.skill.passive.PassiveSkillAffix;
import space.xiami.project.genshinmodel.domain.effect.skill.passive.PassiveSkillEffect;
import space.xiami.project.genshinmodel.domain.effect.talent.TalentAffix;
import space.xiami.project.genshinmodel.domain.effect.talent.TalentEffect;
import space.xiami.project.genshinmodel.domain.entry.bonus.AbstractBonus;
import space.xiami.project.genshinmodel.domain.equipment.skill.active.ActiveSkill;
import space.xiami.project.genshinmodel.domain.equipment.skill.passive.PassiveSkill;
import space.xiami.project.genshinmodel.domain.equipment.talent.Talent;
import space.xiami.project.genshinmodel.rest.AvatarRestTemplate;
import space.xiami.project.genshinmodel.util.converter.AffixConverter;
import space.xiami.project.genshinmodel.util.converter.EffectConverter;
import space.xiami.project.genshinmodel.util.converter.EquipPropTypeConverter;
import space.xiami.project.genshinmodel.util.converter.WeaponTypeConverter;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Xiami
 */
@Component
public class AvatarFactory {

    @Resource
    private AvatarRestTemplate avatarRestTemplate;

    public Avatar getByName(String name, String level, Map<String, Integer> skillLevel, Integer talentLevel){
        return convertAvatar(avatarRestTemplate.getByName(name), level, skillLevel, talentLevel);
    }

    public Avatar getById(Long id, String level, Map<String, Integer> skillLevel, Integer talentLevel){
        return convertAvatar(avatarRestTemplate.getById(id), level, skillLevel, talentLevel);
    }

    public Map<String, NumberRange<Integer>> getSkillLevelRangeByName(String name){
        return avatarRestTemplate.getSkillLevelRangeByName(name);
    }

    public Map<String, NumberRange<Integer>> getSkillLevelRangeById(Long id){
        return avatarRestTemplate.getSkillLevelRangeById(id);
    }

    private static Avatar convertAvatar(space.xiami.project.genshindataviewer.domain.model.Avatar from, String level, Map<String, Integer> skillLevelMap, Integer talentLevel){
        Avatar to = new Avatar();
        to.setId(from.getId());
        to.setName(from.getName());
        // to.setElementalType();
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
        List<AbstractBonus> bonuses = levelProperty.getProperties().stream()
                .map(v -> EquipPropTypeConverter.property2Bonus(v.getPropType(), v.getValue()))
                .collect(Collectors.toList());
        to.setBonuses(bonuses);
        return to;
    }
}
