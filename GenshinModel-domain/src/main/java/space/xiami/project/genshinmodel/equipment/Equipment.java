package space.xiami.project.genshinmodel.equipment;

import space.xiami.project.genshinmodel.entry.bonus.AbstractBonus;
import space.xiami.project.genshinmodel.effect.Effect;

import java.util.List;

public interface Equipment {

    String getName();

    void setName(String name);

    List<AbstractBonus> getBonuses();

    void setBonuses(List<AbstractBonus> bonuses);

    List<Effect> getEffects();

    void setEffects(List<Effect> effects);
}
