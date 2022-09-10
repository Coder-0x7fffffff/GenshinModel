package space.xiami.project.genshinmodel.equipment.artifact;

import space.xiami.project.genshinmodel.enums.ArtifactTypeEnum;
import space.xiami.project.genshinmodel.equipment.AbstractEquipment;

public class Artifact extends AbstractEquipment {

    /**
     * 等级
     */
    private String level;

    /**
     * 类型
     */
    private ArtifactTypeEnum artifactType;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public ArtifactTypeEnum getArtifactType() {
        return artifactType;
    }

    public void setArtifactType(ArtifactTypeEnum artifactType) {
        this.artifactType = artifactType;
    }
}
