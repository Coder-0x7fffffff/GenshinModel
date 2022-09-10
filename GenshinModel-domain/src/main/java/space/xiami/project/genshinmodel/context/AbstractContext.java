package space.xiami.project.genshinmodel.context;

import space.xiami.project.genshinmodel.character.Character;
import space.xiami.project.genshinmodel.effect.Effect;

import java.util.List;


public abstract class AbstractContext<Result> implements Context<Result>{

    private List<Character> characters;

    public AbstractContext(List<Character> characters){
        this.characters = characters;
    }

    @Override
    public List<Character> getCharacters() {
        return characters;
    }

    @Override
    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }

    @Override
    public List<Effect> getEffectInvokeOrder(List<Effect> effects) {
        return effects;
    }
}
