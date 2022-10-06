package space.xiami.project.genshinmodel.domain.effect.weapon;

import space.xiami.project.genshinmodel.domain.effect.AbstractEffect;
import space.xiami.project.genshinmodel.domain.equipment.weapon.Weapon;

import java.util.Collections;

/**
 * @author Xiami
 */
public class WeaponEffect extends AbstractEffect {

    private final Weapon weapon;

    public WeaponEffect(Weapon weapon){
        this.weapon = weapon;
    }

    public WeaponEffect(Weapon weapon, WeaponAffix weaponAffix) {
        this.weapon = weapon;
        setAffixes(Collections.singletonList(weaponAffix));
    }

    public Weapon weapon() {
        return weapon;
    }
}
