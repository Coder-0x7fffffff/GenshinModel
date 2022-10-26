package space.xiami.project.genshinmodel.domain.effect.resonance;

import space.xiami.project.genshinmodel.domain.effect.AbstractAffix;

import java.util.List;
import java.util.Map;

/**
 * @author Xiami
 */
public class TeamResonanceAffix extends AbstractAffix {

    /**
     * 共鸣id
     */
    private Long teamResonanceId;

    /**
     * 共鸣分组id
     */
    private Long teamResonanceGroupId;

    /**
     * 共鸣等级
     */
    private Integer level;

    /**
     * 需要的角色数量
     */
    private Map<Byte, Integer> elementAvatarLimit;

    /**
     * 所有角色元素不同
     */
    private Boolean allDifferent;

    /**
     * openConfig
     */
    private String openConfig;

    /**
     * 参数列表
     */
    private List<Double> paramList;

    public Long getTeamResonanceId() {
        return teamResonanceId;
    }

    public void setTeamResonanceId(Long teamResonanceId) {
        this.teamResonanceId = teamResonanceId;
    }

    public Long getTeamResonanceGroupId() {
        return teamResonanceGroupId;
    }

    public void setTeamResonanceGroupId(Long teamResonanceGroupId) {
        this.teamResonanceGroupId = teamResonanceGroupId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Map<Byte, Integer> getElementAvatarLimit() {
        return elementAvatarLimit;
    }

    public void setElementAvatarLimit(Map<Byte, Integer> elementAvatarLimit) {
        this.elementAvatarLimit = elementAvatarLimit;
    }

    public Boolean getAllDifferent() {
        return allDifferent;
    }

    public void setAllDifferent(Boolean allDifferent) {
        this.allDifferent = allDifferent;
    }

    public String getOpenConfig() {
        return openConfig;
    }

    public void setOpenConfig(String openConfig) {
        this.openConfig = openConfig;
    }

    public List<Double> getParamList() {
        return paramList;
    }

    public void setParamList(List<Double> paramList) {
        this.paramList = paramList;
    }
}
