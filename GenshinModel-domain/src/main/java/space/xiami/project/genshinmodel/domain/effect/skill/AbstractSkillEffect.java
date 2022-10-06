package space.xiami.project.genshinmodel.domain.effect.skill;

import space.xiami.project.genshinmodel.domain.avatar.Avatar;
import space.xiami.project.genshinmodel.domain.effect.AbstractEffect;
import space.xiami.project.genshinmodel.domain.effect.EquipAffix;
import space.xiami.project.genshinmodel.domain.effect.SkillEffect;
import space.xiami.project.genshinmodel.domain.equipment.skill.Skill;

import java.util.List;

/**
 * @author Xiami
 */
public abstract class AbstractSkillEffect extends AbstractEffect implements SkillEffect {

    private Skill skill;

    public AbstractSkillEffect(){}

    public AbstractSkillEffect(List<EquipAffix> equipAffixes, Skill skill) {
        super(equipAffixes);
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
