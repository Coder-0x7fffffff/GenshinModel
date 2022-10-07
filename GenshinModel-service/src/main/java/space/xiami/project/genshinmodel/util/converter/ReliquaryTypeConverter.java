package space.xiami.project.genshinmodel.util.converter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import space.xiami.project.genshincommon.enums.ReliquaryTypeEnum;
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
public class ReliquaryTypeConverter {

    private static Logger log = LoggerFactory.getLogger(ReliquaryTypeConverter.class);

    private static final Map<String, ReliquaryTypeEnum> veMap = new HashMap<>();
    private static final Map<ReliquaryTypeEnum, String> evMap = new HashMap<>();
    private static final Map<Byte, String> bvMap = new HashMap<>();

    private static String reliquaryTypeFile = "reliquaryType.json";

    static{
        refresh();
    }

    public static void refresh(){
        veMap.clear();
        evMap.clear();
        bvMap.clear();
        File file = new File(PathUtil.getConfigDirectory() + reliquaryTypeFile);
        try {
            if(file.exists()){
                try{
                    JSONObject jsonObject = JSON.parseObject(new String(FileUtil.readFile(file)));
                    jsonObject.forEach((key, val) -> {
                        Arrays.stream(ReliquaryTypeEnum.values()).forEach(e -> {
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

    public static ReliquaryTypeEnum string2Enum(String v){
        return veMap.get(v);
    }

    public static String enum2String(ReliquaryTypeEnum e){
        return evMap.get(e);
    }

    public static Byte string2Byte(String v){
        ReliquaryTypeEnum e = veMap.get(v);
        return e != null ? e.getCode() : null;
    }

    public static String byte2String(Byte v){
        return bvMap.get(v);
    }

}
