package space.xiami.project.genshinmodel.effect;

import space.xiami.project.genshinmodel.equipment.constellation.Constellation;
import space.xiami.project.genshinmodel.character.Character;

public abstract class AbstractConstellationEffect extends AbstractEffect implements ConstellationEffect{

    private Constellation constellation;

    public AbstractConstellationEffect(){}

    public AbstractConstellationEffect(Character character, Constellation constellation) {
        super(character);
        this.constellation = constellation;
    }

    @Override
    public Constellation getConstellation() {
        return constellation;
    }

    @Override
    public void setConstellation(Constellation constellation) {
        this.constellation = constellation;
    }
}
