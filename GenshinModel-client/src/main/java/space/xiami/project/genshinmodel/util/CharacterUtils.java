package space.xiami.project.genshinmodel.util;

import space.xiami.project.genshinmodel.character.Character;
import space.xiami.project.genshinmodel.effect.Effect;
import space.xiami.project.genshinmodel.equipment.Equipment;

import java.util.ArrayList;
import java.util.List;

public class CharacterUtils {

    public static List<Effect> getAllEffects(List<Character> characters){
        List<Effect> result = new ArrayList<>();
        for(Character character : characters){
            List<Equipment> equipments = character.getEquipments();
            List<Effect> effects = new ArrayList<>();
            for(Equipment equipment : equipments){
                effects.addAll(equipment.getEffects());
            }
            result.addAll(effects);
        }
        return result;
    }
}
