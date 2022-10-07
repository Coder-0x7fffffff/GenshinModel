package space.xiami.project.genshinmodel.util.converter;

import space.xiami.project.genshindataviewer.domain.model.AddProperty;
import space.xiami.project.genshindataviewer.domain.model.Avatar;
import space.xiami.project.genshindataviewer.domain.model.EquipAffix;
import space.xiami.project.genshinmodel.domain.effect.reliquaries.ReliquarySetAffix;
import space.xiami.project.genshinmodel.domain.effect.skill.SkillProperty;
import space.xiami.project.genshinmodel.domain.effect.skill.active.ActiveSkillAffix;
import space.xiami.project.genshinmodel.domain.effect.skill.passive.PassiveSkillAffix;
import space.xiami.project.genshinmodel.domain.effect.talent.TalentAffix;
import space.xiami.project.genshinmodel.domain.effect.weapon.WeaponAffix;
import space.xiami.project.genshinmodel.domain.entry.bonus.AbstractBonus;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Xiami
 */
public class AffixConverter {

    public static WeaponAffix convertEquipAffix(EquipAffix from){
        if(from == null){
            return null;
        }
        WeaponAffix to = new WeaponAffix();
        to.setAffixId(from.getAffixId());
        to.setId(from.getId());
        to.setLevel(from.getLevel());
        to.setName(from.getName());
        to.setDesc(from.getDesc());
        to.setBonuses(convertBonus(from.getAddProperties()));
        to.setParamList(from.getParamList());
        return to;
    }

    public static ActiveSkillAffix convertActiveSkillAffix(Avatar.ActiveSkill skill, Integer level) {
        if(skill == null){
            return null;
        }
        ActiveSkillAffix to = new ActiveSkillAffix();
        to.setName(skill.getName());
        to.setDesc(skill.getDesc());
        to.setSkillProperty(convertSkillProperty(skill, level));
        to.setId(skill.getId());
        to.setCdTime(skill.getCdTime());
        to.setCostElemType(skill.getCostElemType());
        to.setCostElemVal(skill.getCostElemVal());
        to.setCostStamina(skill.getCostStamina());
        to.setMaxChargeNum(skill.getMaxChargeNum());
        return to;
    }

    public static PassiveSkillAffix convertPassiveSkillAffix(Avatar.PassiveSkill skill, Integer level) {
        if(skill == null){
            return null;
        }
        PassiveSkillAffix to = new PassiveSkillAffix();
        to.setName(skill.getName());
        to.setDesc(skill.getDesc());
        to.setSkillProperty(convertSkillProperty(skill, level));
        to.setProudSkillGroupId(skill.getProudSkillGroupId());
        to.setNeedAvatarPromoteLevel(skill.getNeedAvatarPromoteLevel());
        return to;
    }

    public static TalentAffix convertTalentAffix(Avatar.Talent talent) {
        if(talent == null){
            return null;
        }
        TalentAffix to = new TalentAffix();
        to.setName(talent.getName());
        to.setDesc(talent.getDesc());
        to.setBonuses(convertBonus(talent.getAddProperties()));
        to.setTalentId(talent.getTalentId());
        to.setPrevTalentId(talent.getPrevTalentId());
        to.setParamList(talent.getParamList());
        return to;
    }

    public static ReliquarySetAffix convertReliquarySetAffix(EquipAffix from) {
        if(from == null){
            return null;
        }
        ReliquarySetAffix to = new ReliquarySetAffix();
        to.setAffixId(from.getAffixId());
        to.setId(from.getId());
        to.setName(from.getName());
        to.setDesc(from.getDesc());
        to.setBonuses(convertBonus(from.getAddProperties()));
        to.setParamList(from.getParamList());
        return to;
    }

    private static List<AbstractBonus> convertBonus(List<AddProperty> from){
        List<AbstractBonus> bonuses = new ArrayList<>();
        from.forEach(addProperty -> {
            if(addProperty.getPropType() == null || addProperty.getValue() == null){
                return;
            }
            bonuses.add(
                    EquipPropTypeConverter.property2Bonus(
                            addProperty.getPropType(),
                            addProperty.getValue()
                    )
            );
        });
        return bonuses;
    }

    private static SkillProperty convertSkillProperty(Avatar.Skill from, Integer level) {
        SkillProperty skillProperty = new SkillProperty();
        from.getSkillProperties().forEach(sp -> {
            if(sp.getLevel() != null && sp.getLevel().equals(level)){
                skillProperty.setLevel(sp.getLevel());
                skillProperty.setParamDescValueMap(sp.getParamDescValueMap());
                skillProperty.setParams(sp.getParams());
            }
        });
        return skillProperty;
    }
}
