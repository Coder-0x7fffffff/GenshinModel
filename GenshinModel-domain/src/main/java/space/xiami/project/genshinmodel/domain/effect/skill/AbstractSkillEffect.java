package space.xiami.project.genshinmodel.domain.effect.skill;

import space.xiami.project.genshinmodel.domain.effect.AbstractEffect;
import space.xiami.project.genshinmodel.domain.effect.Affix;
import space.xiami.project.genshinmodel.domain.equipment.skill.AbstractSkill;

import java.util.List;

/**
 * @author Xiami
 */
public abstract class AbstractSkillEffect extends AbstractEffect {
    public AbstractSkillEffect(AbstractSkill parent, Affix affix) {
        super(parent, affix);
    }
}
