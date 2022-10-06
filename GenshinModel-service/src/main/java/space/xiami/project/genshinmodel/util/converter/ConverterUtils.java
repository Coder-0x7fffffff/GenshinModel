package space.xiami.project.genshinmodel.util.converter;

import space.xiami.project.genshinmodel.domain.effect.EquipAffix;
import space.xiami.project.genshinmodel.domain.entry.bonus.AbstractBonus;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Xiami
 */
public class ConverterUtils {

    public static EquipAffix convertEquipAffix(space.xiami.project.genshindataviewer.domain.model.EquipAffix from){
        if(from == null){
            return null;
        }
        EquipAffix to = new EquipAffix();
        to.setAffixId(from.getAffixId());
        to.setId(from.getId());
        to.setLevel(from.getLevel());
        to.setName(from.getName());
        to.setDesc(from.getDesc());
        List<AbstractBonus> bonuses = new ArrayList<>();
        from.getAddProperties().forEach(addProperty -> {
            if(addProperty.getPropType() == null || addProperty.getValue() == null){
                return;
            }
            bonuses.add(
                    EquipPropTypeConverter.property2Bonus(
                            addProperty.getPropType(),
                            addProperty.getValue()
                    )
            );
        });
        to.setAddProperties(bonuses);
        to.setParamList(from.getParamList());
        return to;
    }
}
