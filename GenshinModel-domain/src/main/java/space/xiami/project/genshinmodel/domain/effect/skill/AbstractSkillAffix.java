package space.xiami.project.genshinmodel.domain.effect.skill;

import space.xiami.project.genshinmodel.domain.effect.AbstractAffix;

/**
 * @author Xiami
 */
public abstract class AbstractSkillAffix extends AbstractAffix {

    /**
     * 技能属性列
     */
    private SkillProperty skillProperty;

    public SkillProperty getSkillProperty() {
        return skillProperty;
    }

    public void setSkillProperty(SkillProperty skillProperty) {
        this.skillProperty = skillProperty;
    }
}
