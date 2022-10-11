package space.xiami.project.genshinmodel.domain.entry.bonus;

import space.xiami.project.genshinmodel.domain.entry.attributes.AbstractAttribute;
import space.xiami.project.genshinmodel.domain.entry.attributes.Attributes;

/**
 * @author Xiami
 */
public class AnemoRES extends AbstractAddAttributeBonus {

    public AnemoRES(){
        setValue(0);
    }

    public AnemoRES(double value) {
        setValue(value);
    }

    @Override
    public AbstractAttribute relatedAttribute(Attributes attributes) {
        return attributes.getAnemoRES();
    }
}
