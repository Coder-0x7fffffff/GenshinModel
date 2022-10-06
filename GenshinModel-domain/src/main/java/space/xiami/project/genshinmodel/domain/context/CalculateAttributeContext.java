package space.xiami.project.genshinmodel.domain.context;

import space.xiami.project.genshinmodel.domain.avatar.Avatar;
import space.xiami.project.genshinmodel.domain.entry.attributes.Attributes;

import java.util.List;

/**
 * @author Xiami
 */
public class CalculateAttributeContext extends AbstractContext<CalculateAttributeResult> {

    private List<Integer> realTimeHP;

    private List<Attributes> realTimeAttributes;

    public CalculateAttributeContext(List<Avatar> avatars) {
        super(avatars);
    }

    @Override
    public CalculateAttributeResult buildResult() {
        CalculateAttributeResult result = new CalculateAttributeResult();
        result.setAvatars(getAvatars());
        result.setRealTimeHP(realTimeHP);
        result.setRealTimeAttributes(realTimeAttributes);
        return result;
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
