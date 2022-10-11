package space.xiami.project.genshinmodel.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author Xiami
 */
public class CollectionUtils {

    @SafeVarargs
    public static <T> void addAllList(Collection<T> to, Collection<? extends T> ...from){
        if(from == null || to == null){
            return;
        }
        Arrays.stream(from).forEach(f -> {
            if(f!=null){
                to.addAll(f);
            }
        });
    }

    @SafeVarargs
    public static <T> List<T> flatList(Collection<? extends T> ...from){
        List<T> to = new ArrayList<>();
        Arrays.stream(from).forEach(f -> {
            if(f!=null){
                to.addAll(f);
            }
        });
        return to;
    }
}
