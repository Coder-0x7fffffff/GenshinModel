package space.xiami.project.genshinmodel.effect;

import space.xiami.project.genshinmodel.equipment.constellation.Constellation;

public interface ConstellationEffect extends Effect{

    Constellation getConstellation();

    void setConstellation(Constellation constellation);
}
