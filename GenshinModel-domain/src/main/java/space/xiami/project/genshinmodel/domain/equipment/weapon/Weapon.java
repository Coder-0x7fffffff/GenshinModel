package space.xiami.project.genshinmodel.domain.equipment.weapon;

import space.xiami.project.genshinmodel.domain.entry.attributes.AbstractAttribute;
import space.xiami.project.genshinmodel.domain.equipment.AbstractEquipment;

/**
 * @author Xiami
 */
public class Weapon extends AbstractEquipment {

    /**
     * 武器唯一标识
     */
    private Long id;

    /**
     * 武器类型
     * @see space.xiami.project.genshincommon.enums.WeaponTypeEnum
     */
    private Byte weaponType;

    /**
     * 武器星级
     */
    private Integer rankLevel;

    /**
     * 等级
     */
    private String level;

    /**
     * 精炼等级
     */
    private Integer refinementRank;

    /**
     * 主属性
     */
    private AbstractAttribute mainAttribute;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(Byte weaponType) {
        this.weaponType = weaponType;
    }

    public Integer getRankLevel() {
        return rankLevel;
    }

    public void setRankLevel(Integer rankLevel) {
        this.rankLevel = rankLevel;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getRefinementRank() {
        return refinementRank;
    }

    public void setRefinementRank(Integer refinementRank) {
        this.refinementRank = refinementRank;
    }

    public AbstractAttribute getMainAttribute() {
        return mainAttribute;
    }

    public void setMainAttribute(AbstractAttribute mainAttribute) {
        this.mainAttribute = mainAttribute;
    }
}
