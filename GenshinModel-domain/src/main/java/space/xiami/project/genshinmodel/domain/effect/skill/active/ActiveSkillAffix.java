package space.xiami.project.genshinmodel.domain.effect.skill.active;

import space.xiami.project.genshinmodel.domain.effect.skill.AbstractSkillAffix;

/**
 * @author Xiami
 */
public class ActiveSkillAffix extends AbstractSkillAffix {
    /**
     * 唯一id
     */
    private Long id;

    /**
     * 冷却时间
     */
    private Double cdTime;

    /**
     * 消耗元素类型
     */
    private String costElemType;

    /**
     * 消耗元素能量
     */
    private Double costElemVal;

    /**
     * 消耗体力
     */
    private Double costStamina;

    /**
     * 最多充能次数
     */
    private Integer maxChargeNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getCdTime() {
        return cdTime;
    }

    public void setCdTime(Double cdTime) {
        this.cdTime = cdTime;
    }

    public String getCostElemType() {
        return costElemType;
    }

    public void setCostElemType(String costElemType) {
        this.costElemType = costElemType;
    }

    public Double getCostElemVal() {
        return costElemVal;
    }

    public void setCostElemVal(Double costElemVal) {
        this.costElemVal = costElemVal;
    }

    public Double getCostStamina() {
        return costStamina;
    }

    public void setCostStamina(Double costStamina) {
        this.costStamina = costStamina;
    }

    public Integer getMaxChargeNum() {
        return maxChargeNum;
    }

    public void setMaxChargeNum(Integer maxChargeNum) {
        this.maxChargeNum = maxChargeNum;
    }
}
