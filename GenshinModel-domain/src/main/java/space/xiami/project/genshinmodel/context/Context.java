package space.xiami.project.genshinmodel.context;

import space.xiami.project.genshinmodel.character.Character;
import space.xiami.project.genshinmodel.effect.Effect;

import java.util.List;

public interface Context<Result> {

    List<Character> getCharacters();

    void setCharacters(List<Character> characters);

    List<Effect> getEffectInvokeOrder(List<Effect> effects);

    Result buildResult();
}
