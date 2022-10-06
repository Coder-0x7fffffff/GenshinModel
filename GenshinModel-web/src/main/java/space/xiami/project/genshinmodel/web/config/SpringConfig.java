package space.xiami.project.genshinmodel.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Xiami
 */
@Configuration
public class SpringConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
