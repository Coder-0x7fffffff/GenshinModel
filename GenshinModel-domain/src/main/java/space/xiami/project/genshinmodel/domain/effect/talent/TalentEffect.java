package space.xiami.project.genshinmodel.domain.effect.talent;

import space.xiami.project.genshinmodel.domain.effect.AbstractEffect;
import space.xiami.project.genshinmodel.domain.equipment.talent.Talent;

import java.util.Collections;

/**
 * @author Xiami
 */
public class TalentEffect extends AbstractEffect {

    private final Talent talent;

    public TalentEffect(Talent talent){
        this.talent = talent;
    }

    public TalentEffect(Talent talent, TalentAffix talentAffix) {
        this.talent = talent;
        setAffixes(Collections.singletonList(talentAffix));
    }

    public Talent talent() {
        return talent;
    }
}
