package space.xiami.project.genshinmodel.util.converter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import space.xiami.project.genshincommon.enums.ElementalTypeEnum;
import space.xiami.project.genshinmodel.util.FileUtil;
import space.xiami.project.genshinmodel.util.MapUtil;
import space.xiami.project.genshinmodel.util.PathUtil;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ElementalTypeConverter {

    private static Logger log = LoggerFactory.getLogger(ElementalTypeConverter.class);

    private static final Map<String, ElementalTypeEnum> veMap = new HashMap<>();

    private static String elementalTypeFile = "elementalType.json";

    static{
        refresh();
    }

    public static void refresh(){
        veMap.clear();
        File file = new File(PathUtil.getConfigDirectory() + elementalTypeFile);
        try {
            if(file.exists()){
                try{
                    JSONObject jsonObject = JSON.parseObject(new String(FileUtil.readFile(file)));
                    jsonObject.forEach((key, val) -> {
                        Arrays.stream(ElementalTypeEnum.values()).forEach(e -> {
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
    }

    public static ElementalTypeEnum string2Enum(String v){
        return veMap.get(v);
    }

    public static Byte string2Byte(String v){
        ElementalTypeEnum e = veMap.get(v);
        return e != null ? e.getCode() : null;
    }
}
