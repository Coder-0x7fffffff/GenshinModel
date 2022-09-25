package space.xiami.project.genshinmodel.domain.context;

import space.xiami.project.genshinmodel.domain.avatar.Avatar;
import space.xiami.project.genshinmodel.common.entry.attributes.Attributes;

import java.util.List;

/**
 * @author Xiami
 */
public class CalculateAttributeResult {

    private List<Avatar> avatars;

    private List<Integer> realTimeHP;

    private List<Attributes> realTimeAttributes;

    public List<Avatar> getAvatars() {
        return avatars;
    }

    public void setAvatars(List<Avatar> avatars) {
        this.avatars = avatars;
    }

    public List<Integer> getRealTimeHP() {
        return realTimeHP;
    }

    public void setRealTimeHP(List<Integer> realTimeHP) {
        this.realTimeHP = realTimeHP;
    }

    public List<Attributes> getRealTimeAttributes() {
        return realTimeAttributes;
    }

    public void setRealTimeAttributes(List<Attributes> realTimeAttributes) {
        this.realTimeAttributes = realTimeAttributes;
    }
}
