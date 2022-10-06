package space.xiami.project.genshinmodel.domain.effect.talent;

import space.xiami.project.genshinmodel.domain.avatar.Avatar;
import space.xiami.project.genshinmodel.domain.effect.AbstractEffect;
import space.xiami.project.genshinmodel.domain.effect.EquipAffix;
import space.xiami.project.genshinmodel.domain.effect.TalentEffect;
import space.xiami.project.genshinmodel.domain.equipment.talent.Talent;

import java.util.List;

/**
 * @author Xiami
 */
public abstract class AbstractTalentEffect extends AbstractEffect implements TalentEffect {

    private Talent talent;

    public AbstractTalentEffect(){}

    public AbstractTalentEffect(List<EquipAffix> equipAffixes, Talent talent) {
        super(equipAffixes);
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
