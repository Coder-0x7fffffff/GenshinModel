package space.xiami.project.genshinmodel.domain.effect;

import space.xiami.project.genshinmodel.domain.character.Character;
import space.xiami.project.genshinmodel.domain.context.CalculateAttributeContext;

public interface Effect {

    Character character();

    void onCalculateAttribute(CalculateAttributeContext context);
}
