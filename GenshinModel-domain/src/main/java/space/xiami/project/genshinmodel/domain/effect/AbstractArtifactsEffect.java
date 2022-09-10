package space.xiami.project.genshinmodel.domain.effect;

import space.xiami.project.genshinmodel.domain.character.Character;
import space.xiami.project.genshinmodel.domain.equipment.artifact.Artifacts;

public abstract class AbstractArtifactsEffect extends AbstractEffect implements ArtifactsEffect{

    private Artifacts artifacts;

    public AbstractArtifactsEffect(){}

    public AbstractArtifactsEffect(Character character) {
        super(character);
    }

    @Override
    public Artifacts getArtifacts() {
        return artifacts;
    }

    @Override
    public void setArtifacts(Artifacts artifacts) {
        this.artifacts = artifacts;
    }
}
