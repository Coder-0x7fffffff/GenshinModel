package space.xiami.project.genshinmodel.domain.effect.reliquaries;

import space.xiami.project.genshinmodel.domain.avatar.Avatar;
import space.xiami.project.genshinmodel.domain.effect.AbstractEffect;
import space.xiami.project.genshinmodel.domain.effect.EquipAffix;
import space.xiami.project.genshinmodel.domain.effect.ReliquariesEffect;
import space.xiami.project.genshinmodel.domain.equipment.reliquary.Reliquaries;

import java.util.List;

/**
 * @author Xiami
 */
public abstract class AbstractReliquariesEffect extends AbstractEffect implements ReliquariesEffect {

    private Reliquaries reliquaries;

    public AbstractReliquariesEffect(){}

    public AbstractReliquariesEffect(List<EquipAffix> equipAffixes, Reliquaries reliquaries) {
        super(equipAffixes);
        this.reliquaries = reliquaries;
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
