package space.xiami.project.genshinmodel.service;

import org.springframework.stereotype.Service;
import space.xiami.project.genshinmodel.client.CalculateService;
import space.xiami.project.genshinmodel.domain.context.CalculateAttributeResult;
import space.xiami.project.genshinmodel.domain.avatar.Avatar;
import space.xiami.project.genshinmodel.manager.CalculateManager;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CalculateServiceImpl implements CalculateService {

    @Resource
    private CalculateManager calculateManager;

    @Override
    public CalculateAttributeResult calculateAttribute(List<Avatar> avatar) {
        return null;
    }
}
