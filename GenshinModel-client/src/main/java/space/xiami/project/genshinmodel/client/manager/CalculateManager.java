package space.xiami.project.genshinmodel.client.manager;

import org.springframework.stereotype.Component;
import space.xiami.project.genshinmodel.domain.context.CalculateAttributeContext;
import space.xiami.project.genshinmodel.domain.context.CalculateAttributeResult;
import space.xiami.project.genshinmodel.domain.effect.Effect;
import space.xiami.project.genshinmodel.domain.effect.EffectMethodEnum;
import space.xiami.project.genshinmodel.client.executor.DefaultEffectExecutor;
import space.xiami.project.genshinmodel.client.util.CharacterUtils;

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
