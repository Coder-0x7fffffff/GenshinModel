package space.xiami.project.genshinmodel.domain.effect;

import space.xiami.project.genshinmodel.domain.avatar.Avatar;
import space.xiami.project.genshinmodel.domain.context.CalculateAttributeContext;

import java.util.List;

/**
 * @author Xiami
 */
public interface Effect {

    EquipAffix getEquipAffix();

    void setEquipAffix(EquipAffix equipAffix);

    default void onCalculateAttribute(CalculateAttributeContext context) {}
}
