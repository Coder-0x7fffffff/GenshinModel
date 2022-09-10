package space.xiami.project.genshinmodel.domain.context;

import space.xiami.project.genshinmodel.domain.character.Character;
import space.xiami.project.genshinmodel.domain.effect.Effect;

import java.util.List;

public interface Context<Result> {

    List<Character> getCharacters();

    void setCharacters(List<Character> characters);

    List<Effect> getEffectInvokeOrder(List<Effect> effects);

    Result buildResult();
}
