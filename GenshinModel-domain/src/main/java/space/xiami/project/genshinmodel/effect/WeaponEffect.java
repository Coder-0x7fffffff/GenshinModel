package space.xiami.project.genshinmodel.effect;

import space.xiami.project.genshinmodel.equipment.weapon.Weapon;

public interface WeaponEffect extends Effect{

    Weapon getWeapon();

    void setWeapon(Weapon weapon);
}
