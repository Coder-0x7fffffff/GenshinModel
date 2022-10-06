package space.xiami.project.genshinmodel.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileUtil {
    public static boolean isExists(String path){
        return new File(path).exists();
    }

    public static byte[] readFileOnce(String path) throws IOException {
        File file = new File(path);
        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int len;
        while((len = fis.read(buffer)) > 0){
            outputStream.write(buffer, 0, len);
        }
        return outputStream.toByteArray();
    }

    public static byte[] readFileOnce(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int len;
        while((len = fis.read(buffer)) > 0){
            outputStream.write(buffer, 0, len);
        }
        return outputStream.toByteArray();
    }
}
