package space.xiami.project.genshinmodel.domain.effect;

import space.xiami.project.genshinmodel.domain.avatar.Avatar;
import space.xiami.project.genshinmodel.domain.equipment.skill.Skill;

/**
 * @author Xiami
 */
public abstract class AbstractSkillEffect extends AbstractEffect implements SkillEffect{

    private Skill skill;

    public AbstractSkillEffect(){}

    public AbstractSkillEffect(Avatar avatar, Skill skill) {
        super(avatar);
        this.skill = skill;
    }

    @Override
    public Skill getSkill() {
        return skill;
    }

    @Override
    public void setSkill(Skill skill) {
        this.skill = skill;
    }
}
