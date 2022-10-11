package space.xiami.project.genshinmodel.domain.entry.bonus;

import space.xiami.project.genshinmodel.domain.entry.attributes.AbstractAttribute;
import space.xiami.project.genshinmodel.domain.entry.attributes.Attributes;

/**
 * @author Xiami
 */
public class GeoDMGBonus extends AbstractAddAttributeBonus {

    public GeoDMGBonus(){
        setValue(0);
    }

    public GeoDMGBonus(double value) {
        setValue(value);
    }

    @Override
    public AbstractAttribute relatedAttribute(Attributes attributes) {
        return attributes.getGeoDMGBonus();
    }
}
