package space.xiami.project.genshinmodel.domain.effect.talent;

import space.xiami.project.genshinmodel.domain.effect.AbstractEffect;
import space.xiami.project.genshinmodel.domain.effect.EquipAffix;
import space.xiami.project.genshinmodel.domain.equipment.talent.Talent;

import java.util.List;

/**
 * @author Xiami
 */
public class TalentEffect extends AbstractEffect {

    private final Talent talent;

    public TalentEffect(Talent talent){
        this.talent = talent;
    }

    public TalentEffect(EquipAffix equipAffix, Talent talent) {
        super(equipAffix);
        this.talent = talent;
    }

    public Talent talent() {
        return talent;
    }
}
