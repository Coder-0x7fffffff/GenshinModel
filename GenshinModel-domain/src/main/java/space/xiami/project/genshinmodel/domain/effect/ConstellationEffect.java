package space.xiami.project.genshinmodel.domain.effect;

import space.xiami.project.genshinmodel.domain.equipment.constellation.Constellation;

public interface ConstellationEffect extends Effect{

    Constellation getConstellation();

    void setConstellation(Constellation constellation);
}
