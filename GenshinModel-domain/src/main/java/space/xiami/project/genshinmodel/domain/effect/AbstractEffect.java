package space.xiami.project.genshinmodel.domain.effect;

import space.xiami.project.genshinmodel.domain.avatar.Avatar;
import space.xiami.project.genshinmodel.domain.context.CalculateAttributeContext;

/**
 * @author Xiami
 */
public abstract class AbstractEffect implements Effect{

    private Avatar avatar;

    public AbstractEffect(){}

    public AbstractEffect(Avatar avatar){
        this.avatar = avatar;
    }

    @Override
    public Avatar avatar() {
        return avatar;
    }

    @Override
    public void onCalculateAttribute(CalculateAttributeContext context) {

    }
}
