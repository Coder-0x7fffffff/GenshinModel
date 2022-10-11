package space.xiami.project.genshinmodel.domain.effect;

import space.xiami.project.genshinmodel.domain.context.CalculateAttributeContext;
import space.xiami.project.genshinmodel.domain.equipment.Equipment;

import java.util.List;

/**
 * @author Xiami
 */
public interface Effect {

    Equipment getParent();

    Affix getAffix();

    String uniqueKey();

    default void onCalculateAttribute(CalculateAttributeContext context) {}
}
