package space.xiami.project.genshinmodel.domain.equipment.reliquary;

import space.xiami.project.genshinmodel.common.enums.ReliquaryTypeEnum;
import space.xiami.project.genshinmodel.domain.equipment.AbstractEquipment;

/**
 * @author Xiami
 */
public class Reliquary extends AbstractEquipment {

    /**
     * 等级
     */
    private String level;

    /**
     * 类型
     */
    private ReliquaryTypeEnum reliquaryType;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public ReliquaryTypeEnum getReliquaryType() {
        return reliquaryType;
    }

    public void setReliquaryType(ReliquaryTypeEnum reliquaryType) {
        this.reliquaryType = reliquaryType;
    }
}
