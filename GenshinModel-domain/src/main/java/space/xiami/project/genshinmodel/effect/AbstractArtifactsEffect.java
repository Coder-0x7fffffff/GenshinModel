package space.xiami.project.genshinmodel.effect;

import space.xiami.project.genshinmodel.equipment.artifact.Artifacts;
import space.xiami.project.genshinmodel.character.Character;

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
