package space.xiami.project.genshinmodel.domain.context;

import space.xiami.project.genshinmodel.domain.avatar.Avatar;
import space.xiami.project.genshinmodel.domain.entry.attributes.Attributes;

import java.util.List;
import java.util.Map;

/**
 * @author Xiami
 */
public class CalculateAttributeResult {

    private List<Avatar> avatars;

    private Map<String, Integer> realTimeHP;

    private Map<String, Attributes> realTimeAttributes;

    public List<Avatar> getAvatars() {
        return avatars;
    }

    public void setAvatars(List<Avatar> avatars) {
        this.avatars = avatars;
    }

    public Map<String, Integer> getRealTimeHP() {
        return realTimeHP;
    }

    public void setRealTimeHP(Map<String, Integer> realTimeHP) {
        this.realTimeHP = realTimeHP;
    }

    public Map<String, Attributes> getRealTimeAttributes() {
        return realTimeAttributes;
    }

    public void setRealTimeAttributes(Map<String, Attributes> realTimeAttributes) {
        this.realTimeAttributes = realTimeAttributes;
    }
}
