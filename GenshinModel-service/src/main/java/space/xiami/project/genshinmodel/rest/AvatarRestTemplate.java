package space.xiami.project.genshinmodel.rest;

import javafx.util.Pair;
import org.apache.commons.collections4.map.ListOrderedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import space.xiami.project.genshincommon.NumberRange;
import space.xiami.project.genshincommon.exception.DataRestTemplateException;
import space.xiami.project.genshindataviewer.domain.model.Avatar;
import space.xiami.project.genshinmodel.manager.ConstantManager;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Xiami
 */
@Component
public class AvatarRestTemplate {

    private static final Logger log = LoggerFactory.getLogger(AvatarRestTemplate.class);

    private static final String GET_BY_ID = "avatar/get_by_id";
    private static final String GET_BY_NAME = "avatar/get_by_name";
    private static final String LIST = "avatar/list";

    @Resource
    private DataRestTemplate dataRestTemplate;

    @Resource
    private ConstantManager constantManager;

    public Avatar getById(Long id) {
        return getById(id, constantManager.getLanguageCode());
    }

    public Avatar getById(Long id, Byte lang) {
        try{
            Map<String, Object> params = new HashMap<>(2);
            params.put("lang", lang);
            params.put("id", id);
            return dataRestTemplate.get(GET_BY_ID, params, Avatar.class);
        }catch (DataRestTemplateException e){
            log.info("getById error.", e);
        }
        return null;
    }

    public Avatar getByName(String name) {
        return getByName(name, constantManager.getLanguageCode());
    }

    public Avatar getByName(String name, Byte lang) {
        try{
            Map<String, Object> params = new HashMap<>(2);
            params.put("lang", lang);
            params.put("name", name);
            return dataRestTemplate.get(GET_BY_NAME, params, Avatar.class);
        }catch (DataRestTemplateException e){
            log.info("getByName error.", e);
        }
        return null;
    }

    public Map<String, NumberRange<Integer>> getSkillLevelRangeById(Long id){
        return getSkillLevelRange(getById(id));
    }

    public Map<String, NumberRange<Integer>> getSkillLevelRangeById(Long id, Byte lang){
        return getSkillLevelRange(getById(id, lang));
    }

    public Map<String, NumberRange<Integer>> getSkillLevelRangeByName(String name){
        return getSkillLevelRange(getByName(name));
    }

    public Map<String, NumberRange<Integer>> getSkillLevelRangeByName(String name, Byte lang){
        return getSkillLevelRange(getByName(name, lang));
    }

    public Map list() {
        return list(constantManager.getLanguageCode());
    }

    public Map list(Byte lang) {
        try{
            Map<String, Object> params = new HashMap<>(2);
            params.put("lang", lang);
            return dataRestTemplate.get(LIST, params, Map.class);
        }catch (DataRestTemplateException e){
            log.info("list error.", e);
        }
        return null;
    }

    private static Map<String, NumberRange<Integer>> getSkillLevelRange(Avatar avatar){
        Map<String, NumberRange<Integer>> skillLevelRange = new ListOrderedMap<>();
        if(avatar != null){
            avatar.getSkillActive().values().forEach(activeSkills -> {
                activeSkills.forEach(activeSkill -> {
                    int minLevel = Integer.MAX_VALUE, maxLevel = Integer.MIN_VALUE;
                    for(Avatar.Skill.SkillProperty skillProperty : activeSkill.getSkillProperties()){
                        minLevel = Math.min(minLevel, skillProperty.getLevel());
                        maxLevel = Math.max(maxLevel, skillProperty.getLevel());
                    }
                    skillLevelRange.put(
                            activeSkill.getName(),
                            new NumberRange<>(minLevel, maxLevel)
                    );
                });
            });
        }
        return skillLevelRange;
    }
}
