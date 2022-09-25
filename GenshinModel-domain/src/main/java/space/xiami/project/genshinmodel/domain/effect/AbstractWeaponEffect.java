package space.xiami.project.genshinmodel.domain.effect;

import space.xiami.project.genshinmodel.domain.avatar.Avatar;
import space.xiami.project.genshinmodel.domain.equipment.weapon.Weapon;

/**
 * @author Xiami
 */
public abstract class AbstractWeaponEffect extends AbstractEffect implements WeaponEffect{

    private Weapon weapon;

    public AbstractWeaponEffect(){}

    public AbstractWeaponEffect(Avatar avatar, Weapon weapon) {
        super(avatar);
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
