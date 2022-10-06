package space.xiami.project.genshinmodel.util.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import space.xiami.project.genshinmodel.domain.effect.skill.active.ActiveSkillAffix;
import space.xiami.project.genshinmodel.domain.effect.skill.active.ActiveSkillEffect;
import space.xiami.project.genshinmodel.domain.effect.skill.passive.PassiveSkillAffix;
import space.xiami.project.genshinmodel.domain.effect.skill.passive.PassiveSkillEffect;
import space.xiami.project.genshinmodel.domain.effect.talent.TalentAffix;
import space.xiami.project.genshinmodel.domain.effect.weapon.WeaponAffix;
import space.xiami.project.genshinmodel.domain.effect.talent.TalentEffect;
import space.xiami.project.genshinmodel.domain.effect.weapon.WeaponEffect;
import space.xiami.project.genshinmodel.domain.equipment.skill.active.ActiveSkill;
import space.xiami.project.genshinmodel.domain.equipment.skill.passive.PassiveSkill;
import space.xiami.project.genshinmodel.domain.equipment.talent.Talent;
import space.xiami.project.genshinmodel.domain.equipment.weapon.Weapon;

/**
 * @author Xiami
 */
public class EffectConverter {

    private static Logger log = LoggerFactory.getLogger(EffectConverter.class);

    public static WeaponEffect toWeaponEffect(Weapon weapon, WeaponAffix weaponAffix) {
        // TODO 为每一个武器提供不同的WeaponEffect
        return new WeaponEffect(weapon, weaponAffix);
    }

    public static ActiveSkillEffect toActiveSkillEffect(ActiveSkill skill, ActiveSkillAffix activeSkillAffix) {
        // TODO 为每一个技能提供不同的ActiveSkillEffect
        return new ActiveSkillEffect(skill, activeSkillAffix);
    }

    public static PassiveSkillEffect toPassiveSkillEffect(PassiveSkill skill, PassiveSkillAffix passiveSkillAffix) {
        // TODO 为每一个技能提供不同的PassiveSkillEffect
        return new PassiveSkillEffect(skill, passiveSkillAffix);
    }

    public static TalentEffect toTalentEffect(Talent talent, TalentAffix talentAffix) {
        // TODO 为每一个天赋提供不同的TalentEffect
        return new TalentEffect(talent, talentAffix);
    }
}
