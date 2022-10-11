package space.xiami.project.genshinmodel.domain.effect.talent;

import space.xiami.project.genshinmodel.domain.effect.AbstractAffix;

import java.util.List;

/**
 * @author Xiami
 */
public class TalentAffix extends AbstractAffix {

    /**
     * 唯一命座id
     */
    private Long talentId;

    /**
     * 前置命座
     */
    private Long prevTalentId;

    /**
     * 参数
     */
    private List<Double> paramList;

    public Long getTalentId() {
        return talentId;
    }

    public void setTalentId(Long talentId) {
        this.talentId = talentId;
    }

    public Long getPrevTalentId() {
        return prevTalentId;
    }

    public void setPrevTalentId(Long prevTalentId) {
        this.prevTalentId = prevTalentId;
    }

    public List<Double> getParamList() {
        return paramList;
    }

    public void setParamList(List<Double> paramList) {
        this.paramList = paramList;
    }
}
