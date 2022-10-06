package space.xiami.project.genshinmodel.domain.effect.weapon;

import space.xiami.project.genshinmodel.domain.effect.AbstractEffect;
import space.xiami.project.genshinmodel.domain.effect.EquipAffix;
import space.xiami.project.genshinmodel.domain.equipment.weapon.Weapon;

import java.util.List;

/**
 * @author Xiami
 */
public class WeaponEffect extends AbstractEffect {

    private final Weapon weapon;

    public WeaponEffect(Weapon weapon){
        this.weapon = weapon;
    }

    public WeaponEffect(EquipAffix equipAffix, Weapon weapon) {
        super(equipAffix);
        this.weapon = weapon;
    }

    public Weapon weapon() {
        return weapon;
    }
}
