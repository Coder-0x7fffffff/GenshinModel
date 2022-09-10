package space.xiami.project.genshinmodel.domain.effect;

import space.xiami.project.genshinmodel.domain.equipment.talent.Talent;

public interface TalentEffect extends Effect{

    Talent getTalent();

    void setTalent(Talent talent);
}
