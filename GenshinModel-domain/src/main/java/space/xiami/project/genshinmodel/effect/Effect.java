package space.xiami.project.genshinmodel.effect;

import space.xiami.project.genshinmodel.character.Character;
import space.xiami.project.genshinmodel.context.CalculateAttributeContext;

public interface Effect {

    Character character();

    void onCalculateAttribute(CalculateAttributeContext context);
}
