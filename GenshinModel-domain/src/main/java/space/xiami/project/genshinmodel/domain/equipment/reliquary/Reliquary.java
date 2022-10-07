package space.xiami.project.genshinmodel.domain.equipment.reliquary;

import space.xiami.project.genshincommon.enums.ReliquaryTypeEnum;
import space.xiami.project.genshinmodel.domain.equipment.AbstractEquipment;

/**
 * @author Xiami
 */
public class Reliquary extends AbstractEquipment {

    /**
     * 唯一id
     */
    private Long id;

    /**
     * 分组id
     */
    private Long setId;

    /**
     * 等级
     */
    private Integer level;

    /**
     * 类型
     * @see space.xiami.project.genshincommon.enums.ReliquaryTypeEnum
     */
    private Byte reliquaryType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSetId() {
        return setId;
    }

    public void setSetId(Long setId) {
        this.setId = setId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Byte getReliquaryType() {
        return reliquaryType;
    }

    public void setReliquaryType(Byte reliquaryType) {
        this.reliquaryType = reliquaryType;
    }
}
