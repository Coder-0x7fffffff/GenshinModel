package space.xiami.project.genshinmodel.client;

import space.xiami.project.genshinmodel.domain.avatar.Avatar;
import space.xiami.project.genshinmodel.domain.context.CalculateAttributeResult;

import java.util.List;

public interface CalculateService {

    CalculateAttributeResult calculateAttribute(List<Avatar> avatar);
}
