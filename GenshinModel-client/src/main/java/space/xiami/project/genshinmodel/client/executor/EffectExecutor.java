package space.xiami.project.genshinmodel.client.executor;

import space.xiami.project.genshinmodel.domain.context.Context;
import space.xiami.project.genshinmodel.domain.effect.Effect;
import space.xiami.project.genshinmodel.domain.effect.EffectMethodEnum;

import java.util.List;

public interface EffectExecutor {
    <Result> Result execute(List<Effect> effects, Context<Result> context, EffectMethodEnum effectMethodEnum);
}
