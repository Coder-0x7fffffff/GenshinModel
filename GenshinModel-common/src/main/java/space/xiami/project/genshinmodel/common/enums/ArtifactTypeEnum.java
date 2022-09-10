package space.xiami.project.genshinmodel.common.enums;

public enum ArtifactTypeEnum {

    FLOWER_OF_LIFE((byte) 0, "FlowerOfLife"),
    PLUME_OF_DEATH((byte) 1, "PlumeOfDeath"),
    SANDS_OF_EON((byte) 2, "SandsOfEon"),
    GOBLET_OF_EONOTHEM((byte) 3, "GobletOfEonothem"),
    CIRCLET_OF_LOGOS((byte) 4, "CircletOfLogos"),;

    private final byte code;
    private final String desc;

    ArtifactTypeEnum(byte code, String desc) {
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
