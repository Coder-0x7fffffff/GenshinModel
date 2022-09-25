package space.xiami.project.genshinmodel.domain.effect;

import space.xiami.project.genshinmodel.domain.equipment.skill.Skill;

public interface SkillEffect extends Effect{

    Skill getSkill();

    void setSkill(Skill skill);
}
