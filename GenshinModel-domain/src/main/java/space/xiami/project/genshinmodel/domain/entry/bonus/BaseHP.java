package space.xiami.project.genshinmodel.domain.entry.bonus;

import space.xiami.project.genshinmodel.domain.entry.attributes.AbstractAttribute;
import space.xiami.project.genshinmodel.domain.entry.attributes.Attributes;

/**
 * @author Xiami
 */
public class BaseHP extends AbstractAddAttributeBonus {

    public BaseHP(){
        setValue(0);
    }

    public BaseHP(double value) {
        setValue(value);
    }

    @Override
    public AbstractAttribute relatedAttribute(Attributes attributes) {
        return attributes.getBaseHP();
    }
}
