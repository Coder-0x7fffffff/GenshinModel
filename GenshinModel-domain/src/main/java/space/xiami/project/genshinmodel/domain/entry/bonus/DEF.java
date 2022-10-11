package space.xiami.project.genshinmodel.domain.entry.bonus;

import space.xiami.project.genshinmodel.domain.entry.attributes.AbstractAttribute;
import space.xiami.project.genshinmodel.domain.entry.attributes.Attributes;

/**
 * @author Xiami
 */
public class DEF extends AbstractMultiplyAttributeBonus {

    public DEF(){
        setValue(0);
    }

    public DEF(double value) {
        setValue(value);
    }

    @Override
    public AbstractAttribute relatedAttribute(Attributes attributes) {
        return attributes.getBaseDEF();
    }
}
