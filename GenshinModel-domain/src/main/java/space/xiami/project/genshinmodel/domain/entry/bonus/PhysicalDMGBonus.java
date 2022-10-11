package space.xiami.project.genshinmodel.domain.entry.bonus;

import space.xiami.project.genshinmodel.domain.entry.attributes.AbstractAttribute;
import space.xiami.project.genshinmodel.domain.entry.attributes.Attributes;

/**
 * @author Xiami
 */
public class PhysicalDMGBonus extends AbstractAddAttributeBonus {

    public PhysicalDMGBonus(){
        setValue(0);
    }

    public PhysicalDMGBonus(double value) {
        setValue(value);
    }

    @Override
    public AbstractAttribute relatedAttribute(Attributes attributes) {
        return attributes.getPhysicalDMGBonus();
    }
}
