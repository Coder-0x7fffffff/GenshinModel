package space.xiami.project.genshinmodel.domain.effect.reliquaries;

import space.xiami.project.genshinmodel.domain.effect.AbstractEffect;
import space.xiami.project.genshinmodel.domain.equipment.reliquary.ReliquarySet;

import java.util.Collections;

/**
 * @author Xiami
 */
public class ReliquarySetEffect extends AbstractEffect {
    public ReliquarySetEffect(ReliquarySet parent, ReliquarySetAffix reliquarySetAffix) {
        super(parent, reliquarySetAffix);
    }
}
