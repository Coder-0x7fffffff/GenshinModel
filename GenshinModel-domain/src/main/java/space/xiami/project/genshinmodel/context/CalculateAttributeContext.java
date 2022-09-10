package space.xiami.project.genshinmodel.context;

import space.xiami.project.genshinmodel.entry.attributes.Attributes;
import space.xiami.project.genshinmodel.character.Character;

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
