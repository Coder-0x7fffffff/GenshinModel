package space.xiami.project.genshinmodel.domain.entry.bonus;

import space.xiami.project.genshinmodel.domain.entry.attributes.AbstractAttribute;
import space.xiami.project.genshinmodel.domain.entry.attributes.Attributes;

/**
 * @author Xiami
 */
public class AnemoDMGBonus extends AbstractAddAttributeBonus {

    public AnemoDMGBonus(){
        setValue(0);
    }

    public AnemoDMGBonus(double value) {
        setValue(value);
    }

    @Override
    public AbstractAttribute relatedAttribute(Attributes attributes) {
        return attributes.getAnemoDMGBonus();
    }
}
