package space.xiami.project.genshinmodel.domain.effect.reliquaries;

import space.xiami.project.genshinmodel.domain.effect.AbstractEffect;
import space.xiami.project.genshinmodel.domain.equipment.reliquary.ReliquarySet;

/**
 * @author Xiami
 */
public class ReliquarySetEffect extends AbstractEffect {

    private final ReliquarySet reliquarySet;

    private ReliquarySetAffix reliquarySetAffix;

    public ReliquarySetEffect(ReliquarySet reliquarySet){
        this.reliquarySet = reliquarySet;
    }

    public ReliquarySetEffect(ReliquarySet reliquarySet, ReliquarySetAffix reliquarySetAffix) {
        this.reliquarySet = reliquarySet;
        this.reliquarySetAffix = reliquarySetAffix;
    }

    public ReliquarySet reliquarySet() {
        return reliquarySet;
    }

    public ReliquarySetAffix getReliquarySetAffix() {
        return reliquarySetAffix;
    }

    public void setReliquarySetAffix(ReliquarySetAffix reliquarySetAffix) {
        this.reliquarySetAffix = reliquarySetAffix;
    }
}
