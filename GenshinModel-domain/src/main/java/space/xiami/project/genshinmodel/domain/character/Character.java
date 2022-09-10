package space.xiami.project.genshinmodel.domain.character;

import space.xiami.project.genshinmodel.common.entry.attributes.Attributes;
import space.xiami.project.genshinmodel.domain.equipment.Equipment;

import java.util.List;

public class Character {

    //角色基础信息
    /**
     * 名称
     */
    private String name;

    /**
     * 元素种类
     */
    private Byte elementalType;

    /**
     * 武器种类
     */
    private Byte weaponType;

    //角色实例信息
    /**
     * 等级
     */
    private String level;

    /**
     * 基础属性
     */
    private Attributes attributes;

    /**
     * 装备
     */
    private List<Equipment> equipments;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getElementalType() {
        return elementalType;
    }

    public void setElementalType(Byte elementalType) {
        this.elementalType = elementalType;
    }

    public Byte getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(Byte weaponType) {
        this.weaponType = weaponType;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public List<Equipment> getEquipments() {
        return equipments;
    }

    public void setEquipments(List<Equipment> equipments) {
        this.equipments = equipments;
    }
}
