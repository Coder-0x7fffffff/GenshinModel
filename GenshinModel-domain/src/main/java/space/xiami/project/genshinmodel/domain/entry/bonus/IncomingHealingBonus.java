package space.xiami.project.genshinmodel.domain.entry.bonus;

import space.xiami.project.genshinmodel.domain.entry.attributes.AbstractAttribute;
import space.xiami.project.genshinmodel.domain.entry.attributes.Attributes;

/**
 * @author Xiami
 */
public class IncomingHealingBonus extends AbstractAddAttributeBonus {

    public IncomingHealingBonus(){
        setValue(0);
    }

    public IncomingHealingBonus(double value) {
        setValue(value);
    }

    @Override
    public AbstractAttribute relatedAttribute(Attributes attributes) {
        return attributes.getIncomingHealingBonus();
    }
}
