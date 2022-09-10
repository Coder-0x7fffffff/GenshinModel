package space.xiami.project.genshinmodel.util;

public class StringUtils {

    public static boolean isEmpty(CharSequence cs){
        return cs == null || cs.equals("");
    }

    public static boolean isNotEmpty(CharSequence cs){
        return !isEmpty(cs);
    }
}
