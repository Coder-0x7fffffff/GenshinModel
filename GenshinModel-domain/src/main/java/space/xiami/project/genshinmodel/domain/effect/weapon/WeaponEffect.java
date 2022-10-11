package space.xiami.project.genshinmodel.domain.effect.weapon;

import space.xiami.project.genshinmodel.domain.effect.AbstractEffect;
import space.xiami.project.genshinmodel.domain.equipment.weapon.Weapon;

import java.util.Collections;

/**
 * @author Xiami
 */
public class WeaponEffect extends AbstractEffect {
    public WeaponEffect(Weapon parent, WeaponAffix weaponAffix) {
        super(parent, weaponAffix);
    }
}
