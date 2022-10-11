package space.xiami.project.genshinmodel.util.converter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import space.xiami.project.genshinmodel.domain.entry.bonus.AbstractBonus;
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
public class EquipPropTypeConverter {

    private static Logger log = LoggerFactory.getLogger(EquipPropTypeConverter.class);

    private static Map<String, Class<? extends AbstractBonus>> propType2BonusMap = new HashMap<>();

    private static String equipPropTypeFile = "equipPropType.json";

    private static String packageName = "space.xiami.project.genshinmodel.domain.entry.bonus";

    static{
        refresh();
    }

    public static void refresh(){
        propType2BonusMap.clear();
        File file = new File(PathUtil.getConfigDirectory() + equipPropTypeFile);
        try {
            if(file.exists()){
                try{
                    JSONObject jsonObject = JSON.parseObject(new String(FileUtil.readFile(file)));
                    jsonObject.forEach((key, val) -> {
                        if(val instanceof List){
                            String fullName = packageName +"."+key;
                            try{
                                Class<? extends AbstractBonus> clazz = (Class<? extends AbstractBonus>) ClassLoader.getSystemClassLoader().loadClass(fullName);
                                MapUtil.fillMap(propType2BonusMap, (List<String>) val, clazz);
                            }catch (Exception e) {
                                log.error("init error", e);
                            }
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
