package space.xiami.project.genshinmodel.domain.context;

import space.xiami.project.genshinmodel.domain.avatar.Avatar;
import space.xiami.project.genshinmodel.domain.entry.attributes.Attributes;
import space.xiami.project.genshinmodel.domain.entry.bonus.AbstractBonus;

import java.util.List;
import java.util.Map;

/**
 * @author Xiami
 */
public class CalculateAttributeContext extends AbstractContext<CalculateAttributeResult> {

    /**
     * avatarName -> 血量
     */
    private Map<String, Integer> realTimeHP;

    /**
     *  avatarName-> 面板
     */
    private Map<String, Attributes> realTimeAttributes;

    /**
     * avatarName -> bonusName -> bonus
     */
    private Map<String, Map<String, AbstractBonus>> bonuses;

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

    public Map<String, Map<String, AbstractBonus>> getBonuses() {
        return bonuses;
    }

    public void setBonuses(Map<String, Map<String, AbstractBonus>> bonuses) {
        this.bonuses = bonuses;
    }
}
