package space.xiami.project.genshinmodel.manager;

import org.springframework.stereotype.Component;
import space.xiami.project.genshinmodel.context.CalculateAttributeContext;
import space.xiami.project.genshinmodel.context.CalculateAttributeResult;
import space.xiami.project.genshinmodel.effect.Effect;
import space.xiami.project.genshinmodel.effect.EffectMethodEnum;
import space.xiami.project.genshinmodel.executor.DefaultEffectExecutor;
import space.xiami.project.genshinmodel.util.CharacterUtils;

import javax.annotation.Resource;
import java.util.List;

@Component
public class CalculateManager {

    @Resource
    private DefaultEffectExecutor defaultEffectExecutor;

    public CalculateAttributeResult calculateAttribute(CalculateAttributeContext context) {
        List<Effect> effects = CharacterUtils.getAllEffects(context.getCharacters());
        return defaultEffectExecutor.execute(effects, context, EffectMethodEnum.CALCULATE_ATTRIBUTE);
    }
}
