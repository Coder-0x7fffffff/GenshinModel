package space.xiami.project.genshinmodel.factory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JSONFactory{

    public static String serialize(Object object){
        return JSON.toJSONString(object, SerializerFeature.WriteClassName);
    }

    public static Object deserialize(String json) {
        try{
            JSONObject jsonObject = JSON.parseObject(json);
            String className = jsonObject.getString("@type");
            return JSON.parseObject(json, ClassLoader.getSystemClassLoader().loadClass(className));
        }catch (Exception e){
            return null;
        }
    }
}
