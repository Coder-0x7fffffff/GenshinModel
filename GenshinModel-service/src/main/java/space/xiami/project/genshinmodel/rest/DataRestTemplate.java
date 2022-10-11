package space.xiami.project.genshinmodel.rest;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import space.xiami.project.genshincommon.exception.DataRestTemplateException;
import space.xiami.project.genshindataviewer.domain.ResultVO;
import space.xiami.project.genshinmodel.manager.ConstantManager;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Xiami
 */
@Component
public class DataRestTemplate {

    private static final Integer OK_STATUS = 200;

    private static final String SPLASH = "/";

    private static final String URL_PARAM_BOUNDARY = "?";

    private static final String PARAMS_BOUNDARY = "&";

    @Resource
    private ConstantManager constantManager;

    @Resource
    private RestTemplate restTemplate;

    public <T> T get(String url, Map<String, Object> urlParams, Class<T> resultType) throws DataRestTemplateException {
        // 构建完整来链接
        if(!constantManager.getDataServerServerUrl().endsWith(SPLASH) && !url.startsWith(SPLASH)){
            url = SPLASH + url;
        }
        // 填充参数
        if(!url.contains(URL_PARAM_BOUNDARY)){
            url += URL_PARAM_BOUNDARY + urlParams.keySet().stream().map(key -> key + "={" + key + "}").collect(Collectors.joining(PARAMS_BOUNDARY));
        }
        // 构建返回类型ptr
        ParameterizedTypeReference<ResultVO<T>> reference = ParameterizedTypeReference.forType(
                new ParameterizedType() {
                    private final Type[] actualTypeArguments = new Type[]{resultType};
                    @Override
                    public Type[] getActualTypeArguments() {
                        return this.actualTypeArguments.clone();
                    }

                    @Override
                    public Type getRawType() {
                        return ResultVO.class;
                    }

                    @Override
                    public Type getOwnerType() {
                        return ResultVO.class.getDeclaringClass();
                    }
                }
        );
        // 远程调用
        ResponseEntity<ResultVO<T>> entity = restTemplate.exchange(constantManager.getDataServerServerUrl() + url, HttpMethod.GET,null, reference, urlParams);
        ResultVO<T> result = entity.getBody();
        if(result == null){
            throw new DataRestTemplateException("Http request fault.");
        }
        if(OK_STATUS.equals(result.getStatus())){
            if(result.getError() == null){
                return (T) result.getModal();
            }
            throw new DataRestTemplateException("Data server response error, error="+result.getError());
        }
        throw new DataRestTemplateException("Data server error, status="+result.getStatus()+", error="+ result.getError());
    }

}
