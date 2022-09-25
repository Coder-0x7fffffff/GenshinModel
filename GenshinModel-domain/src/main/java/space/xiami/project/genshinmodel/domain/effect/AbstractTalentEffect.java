package space.xiami.project.genshinmodel.domain.effect;

import space.xiami.project.genshinmodel.domain.avatar.Avatar;
import space.xiami.project.genshinmodel.domain.equipment.talent.Talent;

/**
 * @author Xiami
 */
public abstract class AbstractTalentEffect extends AbstractEffect implements TalentEffect{

    private Talent talent;

    public AbstractTalentEffect(){}

    public AbstractTalentEffect(Avatar avatar, Talent talent) {
        super(avatar);
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
