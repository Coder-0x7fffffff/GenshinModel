package space.xiami.project.genshinmodel.domain.effect;

import space.xiami.project.genshinmodel.domain.equipment.artifact.Artifacts;

public interface ArtifactsEffect extends Effect{

    Artifacts getArtifacts();

    void setArtifacts(Artifacts artifacts);
}
