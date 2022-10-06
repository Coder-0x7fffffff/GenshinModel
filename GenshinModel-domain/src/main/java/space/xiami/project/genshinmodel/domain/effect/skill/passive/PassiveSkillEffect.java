package space.xiami.project.genshinmodel.domain.effect.skill.passive;

import space.xiami.project.genshinmodel.domain.effect.skill.AbstractSkillEffect;
import space.xiami.project.genshinmodel.domain.equipment.skill.passive.PassiveSkill;

import java.util.Collections;

/**
 * @author Xiami
 */
public class PassiveSkillEffect extends AbstractSkillEffect {

    private final PassiveSkill passiveSkill;

    public PassiveSkillEffect(PassiveSkill passiveSkill){
        this.passiveSkill = passiveSkill;
    }

    public PassiveSkillEffect(PassiveSkill passiveSkill, PassiveSkillAffix passiveSkillAffix) {
        this.passiveSkill = passiveSkill;
        setAffixes(Collections.singletonList(passiveSkillAffix));
    }

    public PassiveSkill passiveSkill() {
        return passiveSkill;
    }
}
