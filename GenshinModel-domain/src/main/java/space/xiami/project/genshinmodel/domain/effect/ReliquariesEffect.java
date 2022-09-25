package space.xiami.project.genshinmodel.domain.effect;


import space.xiami.project.genshinmodel.domain.equipment.reliquary.Reliquaries;

/**
 * @author Xiami
 */
public interface ReliquariesEffect extends Effect{

    Reliquaries getReliquaries();

    void setReliquaries(Reliquaries reliquaries);
}
