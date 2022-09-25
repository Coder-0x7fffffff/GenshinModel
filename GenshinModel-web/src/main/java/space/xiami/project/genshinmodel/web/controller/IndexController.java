package space.xiami.project.genshinmodel.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import space.xiami.project.genshinmodel.client.CalculateService;

import javax.annotation.Resource;

/**
 * @author Xiami
 */
@RestController
public class IndexController {

    @Resource
    private CalculateService calculateService;

    @RequestMapping("/index")
    public String index(){
        return "hello "+calculateService;
    }

}
