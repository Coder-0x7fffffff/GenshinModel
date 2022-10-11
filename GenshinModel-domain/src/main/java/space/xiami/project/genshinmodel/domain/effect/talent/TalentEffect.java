package space.xiami.project.genshinmodel.domain.effect.talent;

import space.xiami.project.genshinmodel.domain.effect.AbstractEffect;
import space.xiami.project.genshinmodel.domain.equipment.talent.Talent;

import java.util.Collections;

/**
 * @author Xiami
 */
public class TalentEffect extends AbstractEffect {

    public TalentEffect(Talent parent, TalentAffix talentAffix) {
        super(parent, talentAffix);
    }
}
