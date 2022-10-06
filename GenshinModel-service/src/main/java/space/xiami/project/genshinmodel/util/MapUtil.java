package space.xiami.project.genshinmodel.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * @author Xiami
 */
public class MapUtil {

    private static Logger log = LoggerFactory.getLogger(MapUtil.class);

    public static <K, V> void fillMap(Map<K, V> map, List<K> keys, V value){
        keys.forEach(key -> {
            if(map.containsKey(key)){
                log.warn("Same key: {} for value: {}", key, value);
                return;
            }
            map.put(key, value);
        });
    }
}
