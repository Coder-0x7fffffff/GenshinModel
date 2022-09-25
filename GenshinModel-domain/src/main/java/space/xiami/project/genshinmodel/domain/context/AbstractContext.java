package space.xiami.project.genshinmodel.domain.context;

import space.xiami.project.genshinmodel.domain.avatar.Avatar;
import space.xiami.project.genshinmodel.domain.effect.Effect;

import java.util.List;


/**
 * @author Xiami
 */
public abstract class AbstractContext<Result> implements Context<Result>{

    private List<Avatar> avatars;

    public AbstractContext(List<Avatar> avatars){
        this.avatars = avatars;
    }

    @Override
    public List<Avatar> getAvatars() {
        return avatars;
    }

    @Override
    public void setAvatars(List<Avatar> avatars) {
        this.avatars = avatars;
    }

    @Override
    public List<Effect> getEffectInvokeOrder(List<Effect> effects) {
        return effects;
    }
}
