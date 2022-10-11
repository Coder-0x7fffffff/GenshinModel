package space.xiami.project.genshinmodel.domain.entry.bonus;

import space.xiami.project.genshinmodel.domain.entry.attributes.AbstractAttribute;
import space.xiami.project.genshinmodel.domain.entry.attributes.Attributes;

/**
 * @author Xiami
 */
public class HydroDMGBonus extends AbstractAddAttributeBonus {

    public HydroDMGBonus(){
        setValue(0);
    }

    public HydroDMGBonus(double value) {
        setValue(value);
    }

    @Override
    public AbstractAttribute relatedAttribute(Attributes attributes) {
        return attributes.getHydroDMGBonus();
    }
}
