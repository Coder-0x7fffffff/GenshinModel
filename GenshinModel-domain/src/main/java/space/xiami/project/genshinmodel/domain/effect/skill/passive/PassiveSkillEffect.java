package space.xiami.project.genshinmodel.domain.effect.skill.passive;

import space.xiami.project.genshinmodel.domain.effect.skill.AbstractSkillEffect;
import space.xiami.project.genshinmodel.domain.equipment.skill.passive.PassiveSkill;

import java.util.Collections;

/**
 * @author Xiami
 */
public class PassiveSkillEffect extends AbstractSkillEffect {
    public PassiveSkillEffect(PassiveSkill parent, PassiveSkillAffix passiveSkillAffix) {
        super(parent, passiveSkillAffix);
    }
}
