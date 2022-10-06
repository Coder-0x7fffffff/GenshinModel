package space.xiami.project.genshinmodel.util.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import space.xiami.project.genshinmodel.domain.effect.EquipAffix;
import space.xiami.project.genshinmodel.domain.effect.weapon.WeaponEffect;
import space.xiami.project.genshinmodel.domain.equipment.weapon.Weapon;

/**
 * @author Xiami
 */
public class EffectConverter {

    private static Logger log = LoggerFactory.getLogger(EffectConverter.class);

    public static WeaponEffect toWeaponEffect(Weapon weapon, EquipAffix equipAffix){
        WeaponEffect weaponEffect = new WeaponEffect(weapon);
        weaponEffect.setEquipAffix(equipAffix);
        return weaponEffect;
    }
}
