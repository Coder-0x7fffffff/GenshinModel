package space.xiami.project.genshinmodel.domain.effect;

import space.xiami.project.genshinmodel.domain.avatar.Avatar;
import space.xiami.project.genshinmodel.domain.context.CalculateAttributeContext;

import java.util.List;

/**
 * @author Xiami
 */
public abstract class AbstractEffect implements Effect{

    private EquipAffix equipAffix;

    public AbstractEffect(){}

    public AbstractEffect(EquipAffix equipAffix){
        this.equipAffix = equipAffix;
    }

    @Override
    public EquipAffix getEquipAffix() {
        return equipAffix;
    }

    @Override
    public void setEquipAffix(EquipAffix equipAffix) {
        this.equipAffix = equipAffix;
    }
}
