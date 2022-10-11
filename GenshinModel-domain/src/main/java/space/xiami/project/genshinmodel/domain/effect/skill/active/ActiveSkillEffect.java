package space.xiami.project.genshinmodel.domain.effect.skill.active;

import space.xiami.project.genshinmodel.domain.effect.skill.AbstractSkillEffect;
import space.xiami.project.genshinmodel.domain.equipment.skill.active.ActiveSkill;

import java.util.Collections;

/**
 * @author Xiami
 */
public class ActiveSkillEffect extends AbstractSkillEffect {
    public ActiveSkillEffect(ActiveSkill parent, ActiveSkillAffix activeSkillAffix) {
        super(parent, activeSkillAffix);
    }
}
