package space.xiami.project.genshinmodel.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import space.xiami.project.genshincommon.exception.DataRestTemplateException;
import space.xiami.project.genshindataviewer.domain.model.Reliquary;
import space.xiami.project.genshindataviewer.domain.model.ReliquarySet;
import space.xiami.project.genshinmodel.manager.ConstantManager;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Xiami
 */
@Component
public class ReliquaryRestTemplate {

    private static final Logger log = LoggerFactory.getLogger(ReliquaryRestTemplate.class);

    private static final String GET_SET_BY_ID = "reliquary/get_set_by_id";
    private static final String GET_SET_BY_NAME = "reliquary/get_set_by_name";
    private static final String GET_BY_ID = "reliquary/get_by_id";
    private static final String GET_BY_NAME = "reliquary/get_by_name";
    private static final String LIST = "reliquary/list";
    private static final String LIST_SET = "reliquary/list_set";

    @Resource
    private DataRestTemplate dataRestTemplate;

    @Resource
    private ConstantManager constantManager;

    public ReliquarySet getSetById(Long id) {
        return getSetById(id, constantManager.getLanguageCode());
    }

    public ReliquarySet getSetById(Long id, Byte lang) {
        try{
            Map<String, Object> params = new HashMap<>(2);
            params.put("lang", lang);
            params.put("id", id);
            return dataRestTemplate.get(GET_SET_BY_ID, params, ReliquarySet.class);
        }catch (DataRestTemplateException e){
            log.info("getSetById error.", e);
        }
        return null;
    }

    public Reliquary getSetByName(String name) {
        return getSetByName(name, constantManager.getLanguageCode());
    }

    public Reliquary getSetByName(String name, Byte lang) {
        try{
            Map<String, Object> params = new HashMap<>(2);
            params.put("lang", lang);
            params.put("name", name);
            return dataRestTemplate.get(GET_SET_BY_NAME, params, Reliquary.class);
        }catch (DataRestTemplateException e){
            log.info("getByName error.", e);
        }
        return null;
    }

    public Reliquary getById(Long id) {
        return getById(id, constantManager.getLanguageCode());
    }

    public Reliquary getById(Long id, Byte lang) {
        try{
            Map<String, Object> params = new HashMap<>(2);
            params.put("lang", lang);
            params.put("id", id);
            return dataRestTemplate.get(GET_BY_ID, params, Reliquary.class);
        }catch (DataRestTemplateException e){
            log.info("getById error.", e);
        }
        return null;
    }

    public Reliquary getByName(String name) {
        return getByName(name, constantManager.getLanguageCode());
    }

    public Reliquary getByName(String name, Byte lang) {
        try{
            Map<String, Object> params = new HashMap<>(2);
            params.put("lang", lang);
            params.put("name", name);
            return dataRestTemplate.get(GET_BY_NAME, params, Reliquary.class);
        }catch (DataRestTemplateException e){
            log.info("getByName error.", e);
        }
        return null;
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

    public Map listSet() {
        return listSet(constantManager.getLanguageCode());
    }

    public Map listSet(Byte lang) {
        try{
            Map<String, Object> params = new HashMap<>(2);
            params.put("lang", lang);
            return dataRestTemplate.get(LIST_SET, params, Map.class);
        }catch (DataRestTemplateException e){
            log.info("list error.", e);
        }
        return null;
    }
}
