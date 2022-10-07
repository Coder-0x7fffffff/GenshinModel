package space.xiami.project.genshinmodel.domain.effect.reliquaries;

import space.xiami.project.genshinmodel.domain.effect.AbstractAffix;

import java.util.List;

/**
 * @author Xiami
 */
public class ReliquarySetAffix extends AbstractAffix {

    /**
     * 进阶id
     */
    private Long affixId;

    /**
     * 唯一id
     */
    private Long id;

    /**
     * 参数列表
     */
    private List<Double> paramList;

    public Long getAffixId() {
        return affixId;
    }

    public void setAffixId(Long affixId) {
        this.affixId = affixId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Double> getParamList() {
        return paramList;
    }

    public void setParamList(List<Double> paramList) {
        this.paramList = paramList;
    }
}
