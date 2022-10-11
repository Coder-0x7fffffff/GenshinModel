package space.xiami.project.genshinmodel.domain.equipment.reliquary;


import space.xiami.project.genshinmodel.domain.equipment.AbstractEquipment;

import java.util.List;

/**
 * @author Xiami
 */
public class ReliquarySet extends AbstractEquipment {

    private List<Reliquary> reliquaries;

    public List<Reliquary> getReliquaries() {
        return reliquaries;
    }

    public void setReliquaries(List<Reliquary> reliquaries) {
        this.reliquaries = reliquaries;
    }
}
