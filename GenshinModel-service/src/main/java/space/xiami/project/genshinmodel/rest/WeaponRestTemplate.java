package space.xiami.project.genshinmodel.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import space.xiami.project.genshincommon.exception.DataRestTemplateException;
import space.xiami.project.genshindataviewer.domain.model.Weapon;
import space.xiami.project.genshinmodel.manager.ConstantManager;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Xiami
 */
@Component
public class WeaponRestTemplate {

    private static final Logger log = LoggerFactory.getLogger(WeaponRestTemplate.class);

    private static final String GET_BY_ID = "weapon/get_by_id";
    private static final String GET_BY_NAME = "weapon/get_by_id";
    private static final String LIST = "weapon/list";

    @Resource
    private DataRestTemplate dataRestTemplate;

    @Resource
    private ConstantManager constantManager;

    public Weapon getById(Long id) {
        return getById(id, constantManager.getLanguageCode());
    }

    public Weapon getById(Long id, Byte lang) {
        try{
            Map<String, Object> params = new HashMap<>(2);
            params.put("lang", lang);
            params.put("id", id);
            return dataRestTemplate.get(GET_BY_ID, params, Weapon.class);
        }catch (DataRestTemplateException e){
            log.info("getById error.", e);
        }
        return null;
    }

    public Weapon getByName(String name) {
        return getByName(name, constantManager.getLanguageCode());
    }

    public Weapon getByName(String name, Byte lang) {
        try{
            Map<String, Object> params = new HashMap<>(2);
            params.put("lang", lang);
            params.put("name", name);
            return dataRestTemplate.get(GET_BY_NAME, params, Weapon.class);
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
}
