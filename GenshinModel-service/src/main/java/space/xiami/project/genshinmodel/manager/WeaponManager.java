package space.xiami.project.genshinmodel.manager;

import org.springframework.stereotype.Component;
import space.xiami.project.genshindataviewer.domain.model.LevelProperty;
import space.xiami.project.genshinmodel.domain.effect.Effect;
import space.xiami.project.genshinmodel.domain.effect.weapon.WeaponAffix;
import space.xiami.project.genshinmodel.domain.effect.weapon.WeaponEffect;
import space.xiami.project.genshinmodel.domain.entry.bonus.AbstractBonus;
import space.xiami.project.genshinmodel.domain.equipment.weapon.Weapon;
import space.xiami.project.genshinmodel.rest.WeaponRestTemplate;
import space.xiami.project.genshinmodel.util.converter.AffixConverter;
import space.xiami.project.genshinmodel.util.converter.EffectConverter;
import space.xiami.project.genshinmodel.util.converter.EquipPropTypeConverter;
import space.xiami.project.genshinmodel.util.converter.WeaponTypeConverter;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Xiami
 */
@Component
public class WeaponManager {

    @Resource
    private WeaponRestTemplate weaponRestTemplate;

    public Weapon getById(Long id, String level, Integer refinementRank){
        return convertWeapon(weaponRestTemplate.getById(id), level, refinementRank);
    }

    public Weapon getByName(String name, String level, Integer refinementRank){
        return convertWeapon(weaponRestTemplate.getByName(name), level, refinementRank);
    }

    public Map list(){
        return weaponRestTemplate.list();
    }

    private static Weapon convertWeapon(space.xiami.project.genshindataviewer.domain.model.Weapon from, String level, Integer refinementRank){
        if(from == null){
            return null;
        }
        Weapon to = new Weapon();
        to.setId(from.getId());
        to.setName(from.getName());
        to.setWeaponType(WeaponTypeConverter.string2Byte(from.getWeaponType()));
        to.setRankLevel(from.getRankLevel());
        to.setLevel(level);
        List<AbstractBonus> bonusList = new ArrayList<>();
        for(LevelProperty levelProperty : from.getWeaponProperties()){
            if(levelProperty.getLevel().equals(level)){
                levelProperty.getProperties().forEach(property -> {
                    if(property.getPropType() == null || property.getValue() == null){
                        return;
                    }
                    bonusList.add(
                            EquipPropTypeConverter.property2Bonus(
                                    property.getPropType(),
                                    property.getValue()
                            )
                    );
                });
                break;
            }
        }
        to.setBonuses(bonusList);
        to.setRefinementRank(refinementRank);
        List<Effect> effects = new ArrayList<>();
        for(space.xiami.project.genshindataviewer.domain.model.Weapon.WeaponEquipAffix weaponEquipAffix : from.getWeaponEquipAffixes()){
            if(weaponEquipAffix.getRefinementRank().equals(refinementRank)){
                weaponEquipAffix.getEquipAffix().forEach(equipAffix -> {
                    WeaponAffix convertedAffix = AffixConverter.convertEquipAffix(equipAffix);
                    WeaponEffect weaponEffect = EffectConverter.toWeaponEffect(to, convertedAffix);
                    effects.add(weaponEffect);
                });
                break;
            }
        }
        to.setEffects(effects);
        return to;
    }
}
