package space.xiami.project.genshinmodel.client.util;

import space.xiami.project.genshinmodel.domain.character.Character;
import space.xiami.project.genshinmodel.domain.effect.Effect;
import space.xiami.project.genshinmodel.domain.equipment.Equipment;

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
