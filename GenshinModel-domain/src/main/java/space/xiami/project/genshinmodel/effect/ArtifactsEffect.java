package space.xiami.project.genshinmodel.effect;

import space.xiami.project.genshinmodel.equipment.artifact.Artifacts;

public interface ArtifactsEffect extends Effect{

    Artifacts getArtifacts();

    void setArtifacts(Artifacts artifacts);
}
