package space.xiami.project.genshinmodel.client.util;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class FileUtils {

    private static Logger log = LoggerFactory.getLogger(FileUtils.class);

    public static byte[] downloadSmallFileToBytes(String urlStr){
        try {
            URL url = new URL(urlStr);

            URLConnection conn = url.openConnection();
            InputStream is = conn.getInputStream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] bytes = new byte[4096];
            int len;
            while((len = is.read(bytes)) > 0){
                outputStream.write(bytes, 0, len);
            }
            return outputStream.toByteArray();
        } catch (IOException e) {
            log.error("logerror", e);
        }

    }

}
