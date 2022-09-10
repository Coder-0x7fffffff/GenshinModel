package space.xiami.project.genshinmodel.service;

import space.xiami.project.genshinmodel.domain.character.Character;
import space.xiami.project.genshinmodel.domain.context.CalculateAttributeResult;

import java.util.List;

public interface CalculateService {

    CalculateAttributeResult calculateAttribute(List<Character> character);
}
