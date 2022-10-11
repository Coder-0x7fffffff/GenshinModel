package space.xiami.project.genshinmodel.domain.effect;

import space.xiami.project.genshinmodel.domain.equipment.Equipment;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Xiami
 */
public abstract class AbstractEffect implements Effect{

    private final Equipment parent;

    private final Affix affix;

    private final String uniqueKey;

    public AbstractEffect(Equipment parent, Affix affix) {
        this.parent = parent;
        this.affix = affix;
        this.uniqueKey = String.join(
                "@",
                getClass().getSimpleName(),
                parent.getClass().getSimpleName(),
                this.affix.getClass().getSimpleName()
        );
    }

    @Override
    public String uniqueKey() {
        return uniqueKey;
    }

    @Override
    public Equipment getParent() {
        return parent;
    }

    @Override
    public Affix getAffix() {
        return affix;
    }
}
