package space.xiami.project.genshinmodel.util;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;
import java.util.Set;

/**
 * @author Xiami
 */
public class ConfigUtil {

    private static Logger log = LoggerFactory.getLogger(ConfigUtil.class);

    private static String configFile = "config.json";

    private static Config config;

    static{
        refresh();
    }

    public static void refresh(){
        File file = new File(PathUtil.getConfigDirectory() + configFile);
        try {
            if(file.exists()){
                try{
                    config = JSON.parseObject(new String(FileUtil.readFile(file)), Config.class);
                }catch (Exception e) {
                    log.error("init error", e);
                }
            }
        }catch (Exception e) {
            log.error("init error", e);
        }
    }

    public static Config getConfig() {
        return config;
    }

    public static class Config{

        private Set<String> avatarBonusToAttribute;

        public Set<String> getAvatarBonusToAttribute() {
            return avatarBonusToAttribute;
        }

        public void setAvatarBonusToAttribute(Set<String> avatarBonusToAttribute) {
            this.avatarBonusToAttribute = avatarBonusToAttribute;
        }
    }
}
