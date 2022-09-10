package space.xiami.project.genshinmodel.effect;

import space.xiami.project.genshinmodel.equipment.talent.Talent;

public interface TalentEffect extends Effect{

    Talent getTalent();

    void setTalent(Talent talent);
}
