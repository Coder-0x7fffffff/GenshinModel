package space.xiami.project.genshinmodel.domain.effect;

import space.xiami.project.genshinmodel.domain.avatar.Avatar;
import space.xiami.project.genshinmodel.domain.context.CalculateAttributeContext;

public interface Effect {

    Avatar avatar();

    void onCalculateAttribute(CalculateAttributeContext context);
}
