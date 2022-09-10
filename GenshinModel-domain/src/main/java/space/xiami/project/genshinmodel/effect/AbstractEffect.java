package space.xiami.project.genshinmodel.effect;

import space.xiami.project.genshinmodel.character.Character;
import space.xiami.project.genshinmodel.context.CalculateAttributeContext;

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
