package space.xiami.project.genshinmodel.client.executor;

import org.springframework.stereotype.Component;
import space.xiami.project.genshinmodel.domain.context.Context;
import space.xiami.project.genshinmodel.domain.effect.Effect;
import space.xiami.project.genshinmodel.domain.effect.EffectMethodEnum;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

@Component
public class DefaultEffectExecutor implements EffectExecutor{

    @Override
    public <Result> Result execute(List<Effect> effects, Context<Result> context, EffectMethodEnum effectMethodEnum) {
        List<Effect> orderedEffects = context.getEffectInvokeOrder(effects);
        Method method = effectMethodEnum.getMethod();
        if(method == null){
            return null;
        }
        for(Effect effect : orderedEffects){
            try {
                method.invoke(effect, context);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return context.buildResult();
    }
}
