package space.xiami.project.genshinmodel.manager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import space.xiami.project.genshindataviewer.common.enums.LanguageEnum;

/**
 * @author Xiami
 */
@Component
public class ConstantManager {


    @Value("${data.server.url:http://localhost/}")
    private String dataServerServerUrl;

    @Value("${data.language:CHS}")
    private String language;
    private Byte languageCode;

    public String getDataServerServerUrl(){
        return dataServerServerUrl;
    }

    public Byte getLanguageCode(){
        if(languageCode == null){
            languageCode = LanguageEnum.valueOf(language).getCode();
        }
        return languageCode;
    }
}
