package space.xiami.project.genshinmodel.domain.equipment;

import space.xiami.project.genshinmodel.domain.avatar.Avatar;
import space.xiami.project.genshinmodel.domain.entry.bonus.AbstractBonus;
import space.xiami.project.genshinmodel.domain.effect.Effect;

import java.util.List;

/**
 * @author Xiami
 */
public interface Equipment {

    Avatar getParent();

    void setParent(Avatar parent);

    String getName();

    void setName(String name);

    List<AbstractBonus> getBonuses();

    void setBonuses(List<AbstractBonus> bonuses);

    List<Effect> getEffects();

    void setEffects(List<Effect> effects);
}
