package space.xiami.project.genshinmodel.domain.equipment.weapon;

import space.xiami.project.genshinmodel.domain.equipment.AbstractEquipment;

public class Weapon extends AbstractEquipment {

    /**
     * 武器类型
     */
    private Byte weaponType;

    /**
     * 等级
     */
    private String level;

    /**
     * 精炼等级
     */
    private Integer weaponRank;

    public Byte getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(Byte weaponType) {
        this.weaponType = weaponType;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getWeaponRank() {
        return weaponRank;
    }

    public void setWeaponRank(Integer weaponRank) {
        this.weaponRank = weaponRank;
    }
}
