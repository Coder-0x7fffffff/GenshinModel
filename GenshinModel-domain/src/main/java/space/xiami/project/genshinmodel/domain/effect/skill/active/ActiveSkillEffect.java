package space.xiami.project.genshinmodel.domain.effect.skill.active;

import space.xiami.project.genshinmodel.domain.effect.skill.AbstractSkillEffect;
import space.xiami.project.genshinmodel.domain.equipment.skill.active.ActiveSkill;

import java.util.Collections;

/**
 * @author Xiami
 */
public class ActiveSkillEffect extends AbstractSkillEffect {

    private final ActiveSkill activeSkill;

    public ActiveSkillEffect(ActiveSkill activeSkill){
        this.activeSkill = activeSkill;
    }

    public ActiveSkillEffect(ActiveSkill activeSkill, ActiveSkillAffix activeSkillAffix) {
        this.activeSkill = activeSkill;
        setAffixes(Collections.singletonList(activeSkillAffix));
    }

    public ActiveSkill skill() {
        return activeSkill;
    }
}
