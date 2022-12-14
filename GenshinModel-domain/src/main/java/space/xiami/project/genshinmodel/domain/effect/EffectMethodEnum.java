package space.xiami.project.genshinmodel.domain.effect;

import space.xiami.project.genshinmodel.domain.context.CalculateAttributeContext;

import java.lang.reflect.Method;

/**
 * @author Xiami
 */

public enum EffectMethodEnum {

    CALCULATE_ATTRIBUTE("CalculateAttribute", getMethod("onCalculateAttribute", CalculateAttributeContext.class));

    private String name;
    private Method method;

    EffectMethodEnum(String name, Method method){
        this.name = name;
        this.method = method;
    }

    private static Method getMethod(String name, Class<?>... parameterTypes){
        try {
            return Effect.class.getMethod(name, parameterTypes);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
