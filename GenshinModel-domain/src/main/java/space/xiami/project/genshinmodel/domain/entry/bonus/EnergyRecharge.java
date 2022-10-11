package space.xiami.project.genshinmodel.domain.entry.bonus;

import space.xiami.project.genshinmodel.domain.entry.attributes.AbstractAttribute;
import space.xiami.project.genshinmodel.domain.entry.attributes.Attributes;

/**
 * @author Xiami
 */
public class EnergyRecharge extends AbstractAddAttributeBonus {

    public EnergyRecharge(){
        setValue(0);
    }

    public EnergyRecharge(double value) {
        setValue(value);
    }

    @Override
    public AbstractAttribute relatedAttribute(Attributes attributes) {
        return attributes.getEnergyRecharge();
    }
}
