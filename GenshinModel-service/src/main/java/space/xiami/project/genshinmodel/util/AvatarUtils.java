package space.xiami.project.genshinmodel.util;

import space.xiami.project.genshinmodel.domain.avatar.Avatar;
import space.xiami.project.genshinmodel.domain.effect.Effect;
import space.xiami.project.genshinmodel.domain.equipment.Equipment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Xiami
 */
public class AvatarUtils {

    public static List<Effect> getAllEffects(List<Avatar> avatars){
        List<Effect> result = new ArrayList<>();
        for(Avatar avatar : avatars){
            List<Equipment> equipments = avatar.getEquipments();
            List<Effect> effects = new ArrayList<>();
            for(Equipment equipment : equipments){
                effects.addAll(equipment.getEffects());
            }
            result.addAll(effects);
        }
        return result;
    }
}
