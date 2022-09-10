package space.xiami.project.genshinmodel.executor;

import space.xiami.project.genshinmodel.context.Context;
import space.xiami.project.genshinmodel.effect.Effect;
import space.xiami.project.genshinmodel.effect.EffectMethodEnum;

import java.util.List;

public interface EffectExecutor {
    <Result> Result execute(List<Effect> effects, Context<Result> context, EffectMethodEnum effectMethodEnum);
}
