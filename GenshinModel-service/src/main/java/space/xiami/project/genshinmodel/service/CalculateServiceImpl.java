package space.xiami.project.genshinmodel.service;

import org.springframework.stereotype.Service;
import space.xiami.project.genshinmodel.domain.context.CalculateAttributeResult;
import space.xiami.project.genshinmodel.domain.character.Character;
import space.xiami.project.genshinmodel.client.manager.CalculateManager;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CalculateServiceImpl implements CalculateService{

    @Resource
    private CalculateManager calculateManager;

    @Override
    public CalculateAttributeResult calculateAttribute(List<Character> character) {
        return null;
    }
}
