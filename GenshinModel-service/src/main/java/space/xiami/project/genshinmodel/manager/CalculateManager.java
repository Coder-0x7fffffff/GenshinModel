package space.xiami.project.genshinmodel.manager;

import org.springframework.stereotype.Component;
import space.xiami.project.genshinmodel.domain.context.CalculateAttributeContext;
import space.xiami.project.genshinmodel.domain.context.CalculateAttributeResult;
import space.xiami.project.genshinmodel.domain.effect.Effect;
import space.xiami.project.genshinmodel.domain.effect.EffectMethodEnum;
import space.xiami.project.genshinmodel.executor.DefaultEffectExecutor;
import space.xiami.project.genshinmodel.util.AvatarUtils;

import javax.annotation.Resource;
import java.util.List;

@Component
public class CalculateManager {

    @Resource
    private DefaultEffectExecutor defaultEffectExecutor;

    public CalculateAttributeResult calculateAttribute(CalculateAttributeContext context) {

        // 累加所有装备属性


        // 执行所有effect
        List<Effect> effects = AvatarUtils.getAllEffects(context.getAvatars());
        return defaultEffectExecutor.execute(effects, context, EffectMethodEnum.CALCULATE_ATTRIBUTE);
    }
}
