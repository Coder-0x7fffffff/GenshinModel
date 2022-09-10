package space.xiami.project.genshinmodel.domain.equipment;

import space.xiami.project.genshinmodel.common.entry.bonus.AbstractBonus;
import space.xiami.project.genshinmodel.domain.effect.Effect;

import java.util.List;

public class AbstractEquipment implements Equipment{

    private String name;

    private List<AbstractBonus> bonuses;

    private List<Effect> effects;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public List<AbstractBonus> getBonuses() {
        return bonuses;
    }

    @Override
    public void setBonuses(List<AbstractBonus> bonuses) {
        this.bonuses = bonuses;
    }

    @Override
    public List<Effect> getEffects() {
        return effects;
    }

    @Override
    public void setEffects(List<Effect> effects) {
        this.effects = effects;
    }
}
