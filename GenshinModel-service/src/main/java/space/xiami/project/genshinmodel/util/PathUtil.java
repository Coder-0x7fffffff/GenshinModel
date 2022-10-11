package space.xiami.project.genshinmodel.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Xiami
 */
public class PathUtil {

    /**
     * 本地资源路径
     */
    private static final String resourceDirectory = "resources/";

    /**
     * 配置
     */
    private static final String configDirectory = "config/";

    /**
     * 获取本地资源路径
     * @return 本地资源路径
     */
    public static String getLocalDataDirectory(){
        return resourceDirectory;
    }

    /**
     * 获取配置路径
     * @return 配置路径
     */
    public static String getConfigDirectory(){
        return getLocalDataDirectory() + configDirectory;
    }

    /**
     * 获取文件列表
     * @param path 目录
     * @return 结果
     */
    public static List<String> listFilePaths(String path){
        File dir = new File(path);
        if(!dir.exists()){
            return new ArrayList<>();
        }
        if(dir.isDirectory()){
            File[] files = dir.listFiles();
            if(files!=null){
                List<String> paths = new ArrayList<>(files.length);
                for(File file : files){
                    if(file.isDirectory()){
                        paths.addAll(listFilePaths(file.getPath()));
                    }else{
                        paths.add(file.getPath());
                    }
                }
                return paths;
            }
            return new ArrayList<>();
        }
        return Collections.singletonList(path);
    }
}
