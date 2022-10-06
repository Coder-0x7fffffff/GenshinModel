package space.xiami.project.genshinmodel.domain.effect.skill;

import space.xiami.project.genshinmodel.domain.effect.AbstractEffect;
import space.xiami.project.genshinmodel.domain.effect.EquipAffix;
import space.xiami.project.genshinmodel.domain.equipment.skill.Skill;

import java.util.List;

/**
 * @author Xiami
 */
public class SkillEffect extends AbstractEffect {

    private final Skill skill;

    public SkillEffect(Skill skill){
        this.skill = skill;
    }

    public SkillEffect(EquipAffix equipAffix, Skill skill) {
        super(equipAffix);
        this.skill = skill;
    }

    public Skill skill() {
        return skill;
    }
}
