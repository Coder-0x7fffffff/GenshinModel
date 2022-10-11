package space.xiami.project.genshinmodel.domain.avatar;

import space.xiami.project.genshinmodel.domain.entry.attributes.AbstractAttribute;
import space.xiami.project.genshinmodel.domain.entry.bonus.AbstractBonus;
import space.xiami.project.genshinmodel.domain.equipment.reliquary.Reliquary;
import space.xiami.project.genshinmodel.domain.equipment.reliquary.ReliquarySet;
import space.xiami.project.genshinmodel.domain.equipment.skill.active.ActiveSkill;
import space.xiami.project.genshinmodel.domain.equipment.skill.passive.PassiveSkill;
import space.xiami.project.genshinmodel.domain.equipment.talent.Talent;
import space.xiami.project.genshinmodel.domain.equipment.weapon.Weapon;

import java.util.List;

/**
 * @author Xiami
 */
public class Avatar {

    //角色基础信息
    /**
     * 唯一id
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * TODO 元素种类
     * @see space.xiami.project.genshincommon.enums.ElementalTypeEnum
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
     * 角色属性
     */
    private List<AbstractAttribute> attributes;

    /**
     * 加成
     */
    private List<AbstractBonus> bonuses;

    /**
     * 武器
     */
    private List<Weapon> weapons;

    /**
     * 圣遗物
     */
    private List<Reliquary> reliquaries;

    /**
     * 圣遗物套装效果
     */
    private List<ReliquarySet> reliquarySets;

    /**
     * 主动技能
     */
    private List<ActiveSkill> activeSkills;

    /**
     * 被动技能
     */
    private List<PassiveSkill> passiveSkills;

    /**
     * 命座
     */
    private List<Talent> talents;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<AbstractAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<AbstractAttribute> attributes) {
        this.attributes = attributes;
    }

    public List<AbstractBonus> getBonuses() {
        return bonuses;
    }

    public void setBonuses(List<AbstractBonus> bonuses) {
        this.bonuses = bonuses;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public List<Weapon> getWeapons() {
        return weapons;
    }

    public void setWeapons(List<Weapon> weapons) {
        this.weapons = weapons;
    }

    public List<Reliquary> getReliquaries() {
        return reliquaries;
    }

    public void setReliquaries(List<Reliquary> reliquaries) {
        this.reliquaries = reliquaries;
    }

    public List<ReliquarySet> getReliquarySets() {
        return reliquarySets;
    }

    public void setReliquarySets(List<ReliquarySet> reliquarySets) {
        this.reliquarySets = reliquarySets;
    }

    public List<ActiveSkill> getActiveSkills() {
        return activeSkills;
    }

    public void setActiveSkills(List<ActiveSkill> activeSkills) {
        this.activeSkills = activeSkills;
    }

    public List<PassiveSkill> getPassiveSkills() {
        return passiveSkills;
    }

    public void setPassiveSkills(List<PassiveSkill> passiveSkills) {
        this.passiveSkills = passiveSkills;
    }

    public List<Talent> getTalents() {
        return talents;
    }

    public void setTalents(List<Talent> talents) {
        this.talents = talents;
    }
}
