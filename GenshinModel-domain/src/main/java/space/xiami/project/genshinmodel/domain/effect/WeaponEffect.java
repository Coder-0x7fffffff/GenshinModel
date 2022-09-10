package space.xiami.project.genshinmodel.domain.effect;

import space.xiami.project.genshinmodel.domain.equipment.weapon.Weapon;

public interface WeaponEffect extends Effect{

    Weapon getWeapon();

    void setWeapon(Weapon weapon);
}
