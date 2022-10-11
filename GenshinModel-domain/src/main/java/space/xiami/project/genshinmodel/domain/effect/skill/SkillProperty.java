package space.xiami.project.genshinmodel.domain.effect.skill;

import java.util.List;
import java.util.Map;

/**
 * @author Xiami
 */
public class SkillProperty {
    /**
     * 技能等级
     */
    private Integer level;

    /**
     * 从 paramDescList -> paramList 映射
     */
    private Map<String, String> paramDescValueMap;

    /**
     * 参数
     */
    private List<Double> params;

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Map<String, String> getParamDescValueMap() {
        return paramDescValueMap;
    }

    public void setParamDescValueMap(Map<String, String> paramDescValueMap) {
        this.paramDescValueMap = paramDescValueMap;
    }

    public List<Double> getParams() {
        return params;
    }

    public void setParams(List<Double> params) {
        this.params = params;
    }
}