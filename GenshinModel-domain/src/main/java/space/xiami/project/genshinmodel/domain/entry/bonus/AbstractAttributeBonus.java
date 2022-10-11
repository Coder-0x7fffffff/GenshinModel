package space.xiami.project.genshinmodel.domain.entry.bonus;

import space.xiami.project.genshinmodel.domain.entry.AbstractEntry;
import space.xiami.project.genshinmodel.domain.entry.attributes.AbstractAttribute;
import space.xiami.project.genshinmodel.domain.entry.attributes.Attributes;

/**
 * @author Xiami
 */
public abstract class AbstractAttributeBonus extends AbstractBonus {
    public abstract AbstractAttribute relatedAttribute(Attributes attributes);
}
