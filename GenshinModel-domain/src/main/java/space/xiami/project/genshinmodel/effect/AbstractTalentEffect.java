package space.xiami.project.genshinmodel.effect;

import space.xiami.project.genshinmodel.character.Character;
import space.xiami.project.genshinmodel.equipment.talent.Talent;

public abstract class AbstractTalentEffect extends AbstractEffect implements TalentEffect{

    private Talent talent;

    public AbstractTalentEffect(){}

    public AbstractTalentEffect(Character character, Talent talent) {
        super(character);
        this.talent = talent;
    }

    @Override
    public Talent getTalent() {
        return talent;
    }

    @Override
    public void setTalent(Talent talent) {
        this.talent = talent;
    }
}
