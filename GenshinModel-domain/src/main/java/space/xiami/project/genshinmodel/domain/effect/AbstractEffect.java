package space.xiami.project.genshinmodel.domain.effect;

import java.util.List;

/**
 * @author Xiami
 */
public abstract class AbstractEffect implements Effect{

    private List<Affix> affixes;

    @Override
    public List<Affix> getAffixes() {
        return affixes;
    }

    @Override
    public void setAffixes(List<Affix> affixes) {
        this.affixes = affixes;
    }
}
