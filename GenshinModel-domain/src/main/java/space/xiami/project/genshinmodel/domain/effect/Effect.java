package space.xiami.project.genshinmodel.domain.effect;

import space.xiami.project.genshinmodel.domain.context.CalculateAttributeContext;

import java.util.List;

/**
 * @author Xiami
 */
public interface Effect {

    List<Affix> getAffixes();

    void setAffixes(List<Affix> affixes);

    default void onCalculateAttribute(CalculateAttributeContext context) {}
}
