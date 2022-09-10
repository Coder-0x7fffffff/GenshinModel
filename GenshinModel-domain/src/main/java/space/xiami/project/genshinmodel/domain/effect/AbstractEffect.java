package space.xiami.project.genshinmodel.domain.effect;

import space.xiami.project.genshinmodel.domain.character.Character;
import space.xiami.project.genshinmodel.domain.context.CalculateAttributeContext;

public abstract class AbstractEffect implements Effect{

    private Character character;

    public AbstractEffect(){}

    public AbstractEffect(Character character){
        this.character = character;
    }

    @Override
    public Character character() {
        return character;
    }

    @Override
    public void onCalculateAttribute(CalculateAttributeContext context) {

    }
}
