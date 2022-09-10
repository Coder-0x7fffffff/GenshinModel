package space.xiami.project.genshinmodel.equipment.artifact;


import space.xiami.project.genshinmodel.equipment.AbstractEquipment;

import java.util.List;

public class Artifacts extends AbstractEquipment {

    private List<Artifact> artifacts;

    public List<Artifact> getArtifacts() {
        return artifacts;
    }

    public void setArtifacts(List<Artifact> artifacts) {
        this.artifacts = artifacts;
    }
}
