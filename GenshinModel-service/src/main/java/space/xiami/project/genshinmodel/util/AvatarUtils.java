package space.xiami.project.genshinmodel.util;

import space.xiami.project.genshinmodel.domain.avatar.Avatar;
import space.xiami.project.genshinmodel.domain.effect.Effect;
import space.xiami.project.genshinmodel.domain.equipment.AbstractEquipment;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Xiami
 */
public class AvatarUtils {

    public static List<Effect> getAllEffect(List<Avatar> avatars){
        return avatars
                .stream().map(avatar -> CollectionUtils
                        .flatList(
                                avatar.getWeapons(),
                                avatar.getReliquaries(),
                                avatar.getReliquarySets(),
                                avatar.getActiveSkills(),
                                avatar.getPassiveSkills(),
                                avatar.getTalents()
                        )
                        .stream().map(AbstractEquipment::getEffects)
                        .flatMap(Collection::stream)
                        .collect(Collectors.toList())
                ).flatMap(Collection::stream).collect(Collectors.toList());
    }
}
