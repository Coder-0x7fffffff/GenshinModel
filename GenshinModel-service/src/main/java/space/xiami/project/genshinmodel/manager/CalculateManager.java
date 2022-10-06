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

/**
 * @author Xiami
 */
@Component
public class CalculateManager {

    @Resource
    private DefaultEffectExecutor defaultEffectExecutor;

    public CalculateAttributeResult calculateAttribute(CalculateAttributeContext context) {
        // 计算基础面板

        // 计算加成 技能+命座+武器+圣遗物

        // 执行所有effect

        // 结算面板

        return null;
    }
}
