package space.xiami.project.genshinmodel.util;

import java.io.*;

/**
 * @author Xiami
 */
public class FileUtil {
    public static boolean isExists(String path){
        return new File(path).exists();
    }

    public static byte[] readFile(String path) throws IOException {
        return readFile(new File(path));
    }

    public static byte[] readFile(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int len;
        while((len = fis.read(buffer)) > 0){
            outputStream.write(buffer, 0, len);
        }
        return outputStream.toByteArray();
    }

    public static void writeFile(String path, byte[] bytes) throws IOException {
        writeFile(new File(path), bytes);
    }

    public static void writeFile(File file, byte[] bytes) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(bytes);
        fos.close();
    }
}
