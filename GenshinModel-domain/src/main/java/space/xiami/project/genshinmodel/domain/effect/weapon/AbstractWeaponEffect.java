package space.xiami.project.genshinmodel.domain.effect.weapon;

import space.xiami.project.genshinmodel.domain.avatar.Avatar;
import space.xiami.project.genshinmodel.domain.effect.AbstractEffect;
import space.xiami.project.genshinmodel.domain.effect.EquipAffix;
import space.xiami.project.genshinmodel.domain.effect.WeaponEffect;
import space.xiami.project.genshinmodel.domain.equipment.weapon.Weapon;

import java.util.List;

/**
 * @author Xiami
 */
public abstract class AbstractWeaponEffect extends AbstractEffect implements WeaponEffect {

    private Weapon weapon;

    public AbstractWeaponEffect(){}

    public AbstractWeaponEffect(List<EquipAffix> equipAffixes, Weapon weapon) {
        super(equipAffixes);
        this.weapon = weapon;
    }

    @Override
    public Weapon getWeapon() {
        return weapon;
    }

    @Override
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }


}
