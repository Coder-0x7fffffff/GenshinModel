package space.xiami.project.genshinmodel.domain.effect;

import space.xiami.project.genshinmodel.domain.character.Character;
import space.xiami.project.genshinmodel.domain.equipment.constellation.Constellation;

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
