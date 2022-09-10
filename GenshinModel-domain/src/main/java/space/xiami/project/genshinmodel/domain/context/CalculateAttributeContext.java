package space.xiami.project.genshinmodel.domain.context;

import space.xiami.project.genshinmodel.domain.character.Character;
import space.xiami.project.genshinmodel.common.entry.attributes.Attributes;

import java.util.List;

public class CalculateAttributeContext extends AbstractContext<CalculateAttributeResult> {

    private List<Integer> realTimeHP;

    private List<Attributes> realTimeAttributes;

    public CalculateAttributeContext(List<Character> characters) {
        super(characters);
    }

    @Override
    public CalculateAttributeResult buildResult() {
        CalculateAttributeResult result = new CalculateAttributeResult();
        result.setCharacters(getCharacters());
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
