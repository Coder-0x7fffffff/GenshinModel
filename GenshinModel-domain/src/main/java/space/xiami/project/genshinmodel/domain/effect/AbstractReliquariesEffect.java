package space.xiami.project.genshinmodel.domain.effect;

import space.xiami.project.genshinmodel.domain.avatar.Avatar;
import space.xiami.project.genshinmodel.domain.equipment.reliquary.Reliquaries;

/**
 * @author Xiami
 */
public abstract class AbstractReliquariesEffect extends AbstractEffect implements ReliquariesEffect {

    private Reliquaries reliquaries;

    public AbstractReliquariesEffect(){}

    public AbstractReliquariesEffect(Avatar avatar) {
        super(avatar);
    }

    @Override
    public Reliquaries getReliquaries() {
        return reliquaries;
    }

    @Override
    public void setReliquaries(Reliquaries reliquaries) {
        this.reliquaries = reliquaries;
    }
}
