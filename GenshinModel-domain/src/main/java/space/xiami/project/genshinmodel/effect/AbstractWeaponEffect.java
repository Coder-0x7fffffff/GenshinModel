package space.xiami.project.genshinmodel.effect;

import space.xiami.project.genshinmodel.character.Character;
import space.xiami.project.genshinmodel.equipment.weapon.Weapon;

public abstract class AbstractWeaponEffect extends AbstractEffect implements WeaponEffect{

    private Weapon weapon;

    public AbstractWeaponEffect(){}

    public AbstractWeaponEffect(Character character, Weapon weapon) {
        super(character);
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
