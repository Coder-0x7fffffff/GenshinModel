package space.xiami.project.genshinmodel.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import space.xiami.project.genshinmodel.domain.context.Context;
import space.xiami.project.genshinmodel.domain.effect.Effect;
import space.xiami.project.genshinmodel.domain.effect.EffectMethodEnum;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author Xiami
 */
@Component
public class DefaultEffectExecutor implements EffectExecutor{

    private final Logger log = LoggerFactory.getLogger(DefaultEffectExecutor.class);

    @Override
    public <Result> void execute(List<Effect> effects, Context<Result> context, EffectMethodEnum effectMethodEnum) {
        List<Effect> orderedEffects = context.getEffectInvokeOrder(effects);
        Method method = effectMethodEnum.getMethod();
        if(method == null){
            return;
        }
        for(Effect effect : orderedEffects){
            try {
                method.invoke(effect, context);
            } catch (IllegalAccessException | InvocationTargetException e) {
                log.error("Execute error", e);
            }
        }
    }
}
