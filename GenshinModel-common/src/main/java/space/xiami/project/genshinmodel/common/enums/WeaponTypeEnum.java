package space.xiami.project.genshinmodel.common.enums;

public enum WeaponTypeEnum {
    SWORD((byte) 0, "Sword"),
    CLAYMORE((byte) 1, "Claymore"),
    BOW((byte) 2, "Bow"),
    CATALYST((byte) 3, "Catalyst"),
    POLEARM((byte) 4, "Polearm"),;

    private final byte code;
    private final String desc;

    WeaponTypeEnum(byte code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public byte getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
