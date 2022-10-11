package space.xiami.project.genshinmodel.domain.entry.bonus;

import space.xiami.project.genshinmodel.domain.entry.attributes.AbstractAttribute;
import space.xiami.project.genshinmodel.domain.entry.attributes.Attributes;

/**
 * @author Xiami
 */
public class BaseATK extends AbstractAddAttributeBonus {

    public BaseATK(){
        setValue(0);
    }

    public BaseATK(double value) {
        setValue(value);
    }

    @Override
    public AbstractAttribute relatedAttribute(Attributes attributes) {
        return attributes.getBaseATK();
    }
}
