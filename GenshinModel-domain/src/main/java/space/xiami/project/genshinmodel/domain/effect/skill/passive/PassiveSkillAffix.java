package space.xiami.project.genshinmodel.domain.effect.skill.passive;

import space.xiami.project.genshinmodel.domain.effect.skill.AbstractSkillAffix;

/**
 * @author Xiami
 */
public class PassiveSkillAffix extends AbstractSkillAffix {

    /**
     * 被动的id
     */
    private Long proudSkillGroupId;

    /**
     * 需要的人物突破等级
     */
    private Integer needAvatarPromoteLevel;

    public Long getProudSkillGroupId() {
        return proudSkillGroupId;
    }

    public void setProudSkillGroupId(Long proudSkillGroupId) {
        this.proudSkillGroupId = proudSkillGroupId;
    }

    public Integer getNeedAvatarPromoteLevel() {
        return needAvatarPromoteLevel;
    }

    public void setNeedAvatarPromoteLevel(Integer needAvatarPromoteLevel) {
        this.needAvatarPromoteLevel = needAvatarPromoteLevel;
    }
}
