package space.xiami.project.genshinmodel.domain.entry.bonus;

import space.xiami.project.genshinmodel.domain.entry.attributes.AbstractAttribute;
import space.xiami.project.genshinmodel.domain.entry.attributes.Attributes;

/**
 * @author Xiami
 */
public class ATK extends AbstractMultiplyAttributeBonus {

    public ATK(){
        setValue(0);
    }

    public ATK(double value) {
        setValue(value);
    }

    @Override
    public AbstractAttribute relatedAttribute(Attributes attributes) {
        return attributes.getBaseATK();
    }
}
