package space.xiami.project.genshinmodel.domain.effect.reliquaries;

import space.xiami.project.genshinmodel.domain.effect.AbstractEffect;
import space.xiami.project.genshinmodel.domain.effect.EquipAffix;
import space.xiami.project.genshinmodel.domain.equipment.reliquary.ReliquarySet;

import java.util.List;

/**
 * @author Xiami
 */
public class ReliquarySetEffect extends AbstractEffect {

    private final ReliquarySet reliquarySet;

    public ReliquarySetEffect(ReliquarySet reliquarySet){
        this.reliquarySet = reliquarySet;
    }

    public ReliquarySetEffect(EquipAffix equipAffix, ReliquarySet reliquarySet) {
        super(equipAffix);
        this.reliquarySet = reliquarySet;
    }

    public ReliquarySet reliquarySet() {
        return reliquarySet;
    }
}
