package space.xiami.project.genshinmodel.util.converter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import space.xiami.project.genshinmodel.domain.entry.attributes.AbstractAttribute;
import space.xiami.project.genshinmodel.util.FileUtil;
import space.xiami.project.genshinmodel.util.MapUtil;
import space.xiami.project.genshinmodel.util.PathUtil;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Xiami
 */
public class AttributeLevelPropTypeConverter {

    private static Logger log = LoggerFactory.getLogger(AttributeLevelPropTypeConverter.class);

    private static Map<String, Class<? extends AbstractAttribute>> propType2BonusMap = new HashMap<>();

    private static String attributeLevelPropTypeFile = "attributeLevelPropType.json";

    private static String packageName = "space.xiami.project.genshinmodel.domain.entry.attributes";

    static{
        refresh();
    }

    public static void refresh(){
        propType2BonusMap.clear();
        File file = new File(PathUtil.getConfigDirectory() + attributeLevelPropTypeFile);
        try {
            if(file.exists()){
                try{
                    JSONObject jsonObject = JSON.parseObject(new String(FileUtil.readFile(file)));
                    jsonObject.forEach((key, val) -> {
                        if(val instanceof List){
                            String fullName = packageName +"."+key;
                            try{
                                Class<? extends AbstractAttribute> clazz = (Class<? extends AbstractAttribute>) ClassLoader.getSystemClassLoader().loadClass(fullName);
                                MapUtil.fillMap(propType2BonusMap, (List<String>) val, clazz);
                            }catch (Exception ignore) {}
                        }
                    });
                }catch (Exception e) {
                    log.error("init error", e);
                }
            }
        }catch (Exception e) {
            log.error("init error", e);
        }
    }

    public static AbstractAttribute property2Bonus(String name, Double value){
        Class<? extends AbstractAttribute> clazz = propType2BonusMap.get(name);
        if(clazz != null){
            try{
                AbstractAttribute bonus = clazz.newInstance();
                bonus.setValue(value);
                return bonus;
            }catch (Exception ignore) {}
        }
        return null;
    }
}
