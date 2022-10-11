package space.xiami.project.genshinmodel.util.converter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import space.xiami.project.genshincommon.enums.WeaponTypeEnum;
import space.xiami.project.genshinmodel.util.FileUtil;
import space.xiami.project.genshinmodel.util.MapUtil;
import space.xiami.project.genshinmodel.util.PathUtil;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Xiami
 */
public class WeaponTypeConverter {

    private static Logger log = LoggerFactory.getLogger(WeaponTypeConverter.class);

    private static final Map<String, WeaponTypeEnum> veMap = new HashMap<>();
    private static final Map<WeaponTypeEnum, String> evMap = new HashMap<>();
    private static final Map<Byte, String> bvMap = new HashMap<>();

    private static String weaponTypeFile = "weaponType.json";

    static{
        refresh();
    }

    public static void refresh(){
        veMap.clear();
        evMap.clear();
        bvMap.clear();
        File file = new File(PathUtil.getConfigDirectory() + weaponTypeFile);
        try {
            if(file.exists()){
                try{
                    JSONObject jsonObject = JSON.parseObject(new String(FileUtil.readFile(file)));
                    jsonObject.forEach((key, val) -> {
                        Arrays.stream(WeaponTypeEnum.values()).forEach(e -> {
                            if(e.getDesc().equals(key)){
                                MapUtil.fillMap(veMap, (List<String>) val, e);
                            }
                        });
                    });
                }catch (Exception e){
                    log.error("init error", e);
                }
            }
        }catch (Exception e){
            log.error("init error", e);
        }

        veMap.forEach((k, v) -> {
            evMap.put(v, k);
            bvMap.put(v.getCode(), k);
        });
    }

    public static WeaponTypeEnum string2Enum(String v){
        return veMap.get(v);
    }

    public static String enum2String(WeaponTypeEnum e){
        return evMap.get(e);
    }

    public static Byte string2Byte(String v){
        WeaponTypeEnum e = veMap.get(v);
        return e != null ? e.getCode() : null;
    }

    public static String byte2String(Byte v){
        return bvMap.get(v);
    }

}
