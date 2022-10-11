package space.xiami.project.genshinmodel.domain.context;

import space.xiami.project.genshinmodel.domain.avatar.Avatar;
import space.xiami.project.genshinmodel.domain.effect.Effect;

import java.util.List;

/**
 * @author Xiami
 */
public interface Context<Result> {

    List<Avatar> getAvatars();

    void setAvatars(List<Avatar> avatars);

    List<Effect> getEffectInvokeOrder(List<Effect> effects);

    Result buildResult();
}
