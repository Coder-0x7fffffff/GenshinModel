package space.xiami.project.genshinmodel.domain.effect;

import space.xiami.project.genshinmodel.domain.avatar.Avatar;
import space.xiami.project.genshinmodel.domain.context.CalculateAttributeContext;

import java.util.List;

public interface Effect {

    List<EquipAffix> affixes();

    void onCalculateAttribute(CalculateAttributeContext context);
}
