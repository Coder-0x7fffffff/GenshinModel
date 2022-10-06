package space.xiami.project.genshinmodel.domain.effect.weapon;

import space.xiami.project.genshinmodel.domain.effect.AbstractAffix;
import space.xiami.project.genshinmodel.domain.entry.bonus.AbstractBonus;

import java.util.List;
import java.util.Map;

/**
 * @author Xiami
 */
public class WeaponAffix extends AbstractAffix {

    /**
     * 进阶id
     */
    private Long affixId;

    /**
     * 唯一id
     */
    private Long id;

    /**
     * 等级
     */
    private Integer level;

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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public List<Double> getParamList() {
        return paramList;
    }

    public void setParamList(List<Double> paramList) {
        this.paramList = paramList;
    }
}
