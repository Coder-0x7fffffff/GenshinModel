package space.xiami.project.genshinmodel.domain.entry.bonus;

import space.xiami.project.genshinmodel.domain.entry.attributes.AbstractAttribute;
import space.xiami.project.genshinmodel.domain.entry.attributes.Attributes;

/**
 * @author Xiami
 */
public class DendroDMGBonus extends AbstractAddAttributeBonus {

    public DendroDMGBonus(){
        setValue(0);
    }

    public DendroDMGBonus(double value) {
        setValue(value);
    }

    @Override
    public AbstractAttribute relatedAttribute(Attributes attributes) {
        return attributes.getDendroDMGBonus();
    }
}
