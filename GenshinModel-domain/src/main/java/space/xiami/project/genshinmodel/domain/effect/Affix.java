package space.xiami.project.genshinmodel.domain.effect;

import space.xiami.project.genshinmodel.domain.entry.bonus.AbstractBonus;

import java.util.List;

public interface Affix {

    String getName();

    void setName(String name);

    String getDesc();

    void setDesc(String desc);

    List<AbstractBonus> getBonuses();

    void setBonuses(List<AbstractBonus> bonuses);
}
