package space.xiami.project.genshinmodel.domain.effect;

import space.xiami.project.genshinmodel.domain.avatar.Avatar;
import space.xiami.project.genshinmodel.domain.context.CalculateAttributeContext;

import java.util.List;

/**
 * @author Xiami
 */
public abstract class AbstractEffect implements Effect{

    private List<EquipAffix> equipAffixes;

    public AbstractEffect(){}

    public AbstractEffect(List<EquipAffix> equipAffixes){
        this.equipAffixes = equipAffixes;
    }

    @Override
    public List<EquipAffix> affixes(){
        return equipAffixes;
    }

    @Override
    public void onCalculateAttribute(CalculateAttributeContext context) {}
}
