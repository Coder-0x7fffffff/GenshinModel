package space.xiami.project.genshinmodel.context;

import space.xiami.project.genshinmodel.entry.attributes.Attributes;
import space.xiami.project.genshinmodel.character.Character;

import java.util.List;

public class CalculateAttributeResult {

    private List<Character> characters;

    private List<Integer> realTimeHP;

    private List<Attributes> realTimeAttributes;

    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
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
