package space.xiami.project.genshinmodel.util.converter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import space.xiami.project.genshinmodel.domain.entry.bonus.AbstractBonus;
import space.xiami.project.genshinmodel.util.FileUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Xiami
 */
public class EntryConverter {

    private static Logger log = LoggerFactory.getLogger(EntryConverter.class);

    private static Map<String, Class<? extends AbstractBonus>> propType2BonusMap = new HashMap<>();

    private static String equipPropTypeFile = "equipPropType.json";

    private static String packageName = "space.xiami.project.genshinmodel.domain.entry.bonus";

    static{
        ClassPathResource equipPropTypeResource = new ClassPathResource(equipPropTypeFile);
        try {
            if(equipPropTypeResource.getFile().exists()){
                try{
                    JSONObject jsonObject = JSON.parseObject(new String(FileUtil.readFileOnce(equipPropTypeResource.getFile())));
                    jsonObject.forEach((key, val) -> {
                        if(val instanceof List){
                            String fullName = packageName +"."+key;
                            try{
                                Class<? extends AbstractBonus> clazz = (Class<? extends AbstractBonus>) ClassLoader.getSystemClassLoader().loadClass(fullName);
                                fillMap(propType2BonusMap, (List<String>) val, clazz);
                            }catch (Exception ignore) {}
                        }
                    });
                }catch (Exception ignore){}
            }
        } catch (Exception ignore) {}
    }

    private static <K, V> void fillMap(Map<K, V> map, List<K> keys, V value){
        keys.forEach(key -> {
            if(map.containsKey(key)){
                log.warn("Same key: {} for value: {}", key, value);
                return;
            }
            map.put(key, value);
        });
    }

    public static AbstractBonus property2Bonus(String name, Double value){
        Class<? extends AbstractBonus> clazz = propType2BonusMap.get(name);
        if(clazz != null){
            try{
                AbstractBonus bonus = clazz.newInstance();
                bonus.setValue(value);
                return bonus;
            }catch (Exception ignore) {}
        }
        return null;
    }
}
