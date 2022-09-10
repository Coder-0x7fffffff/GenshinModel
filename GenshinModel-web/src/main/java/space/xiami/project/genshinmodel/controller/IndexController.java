package space.xiami.project.genshinmodel.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import space.xiami.project.genshinmodel.service.CalculateService;

import javax.annotation.Resource;

@RestController
public class IndexController {

    @Resource
    private CalculateService calculateService;

    @RequestMapping("/index")
    public String index(){
        return "hello "+calculateService;
    }

}
