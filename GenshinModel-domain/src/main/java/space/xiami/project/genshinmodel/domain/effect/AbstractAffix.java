package space.xiami.project.genshinmodel.domain.effect;

import space.xiami.project.genshinmodel.domain.entry.bonus.AbstractBonus;

import java.util.List;

/**
 * @author Xiami
 */
public abstract class AbstractAffix implements Affix{

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String desc;

    /**
     * 属性增长
     */
    private List<AbstractBonus> bonuses;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    @Override
    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public List<AbstractBonus> getBonuses() {
        return bonuses;
    }

    @Override
    public void setBonuses(List<AbstractBonus> bonuses) {
        this.bonuses = bonuses;
    }
}
