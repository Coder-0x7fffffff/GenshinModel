package space.xiami.project.genshinmodel.common.enums;

public enum ElementalTypeEnum {
    //火
    PYRO((byte) 0, "Pyro"),
    //水
    HYDRO((byte) 1, "Hydro"),
    //草
    DENDRO((byte) 2, "Dendro"),
    //雷
    ELECTRO((byte) 3, "Electro"),
    //风
    ANEMO((byte) 4, "Anemo"),
    //冰
    CRYO((byte) 5, "Cryo"),
    //岩
    GEO((byte) 6, "Geo");

    private final byte code;
    private final String desc;

    ElementalTypeEnum(byte code, String desc) {
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
