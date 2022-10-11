package space.xiami.project.genshinmodel.executor;

import space.xiami.project.genshinmodel.domain.context.Context;
import space.xiami.project.genshinmodel.domain.effect.Effect;
import space.xiami.project.genshinmodel.domain.effect.EffectMethodEnum;

import java.util.List;

/**
 * @author Xiami
 */
public interface EffectExecutor {
    <Result> void execute(List<Effect> effects, Context<Result> context, EffectMethodEnum effectMethodEnum);
}
