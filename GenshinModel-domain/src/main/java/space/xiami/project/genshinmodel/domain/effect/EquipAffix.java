package space.xiami.project.genshinmodel.domain.effect;

import space.xiami.project.genshinmodel.domain.entry.bonus.AbstractBonus;

import java.util.List;

/**
 * @author Xiami
 */
public class EquipAffix {
    private Long affixId;
    private Long id;
    private Integer level;
    private String name;
    private String desc;
    private List<AbstractBonus> addProperties;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<AbstractBonus> getAddProperties() {
        return addProperties;
    }

    public void setAddProperties(List<AbstractBonus> addProperties) {
        this.addProperties = addProperties;
    }

    public List<Double> getParamList() {
        return paramList;
    }

    public void setParamList(List<Double> paramList) {
        this.paramList = paramList;
    }
}
