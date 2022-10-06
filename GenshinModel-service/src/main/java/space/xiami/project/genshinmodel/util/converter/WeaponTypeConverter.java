package space.xiami.project.genshinmodel.util.converter;

import space.xiami.project.genshincommon.enums.WeaponTypeEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Xiami
 */
public class WeaponTypeConverter {

    private static final Map<String, WeaponTypeEnum> veMap = new HashMap<>();
    private static final Map<WeaponTypeEnum, String> evMap = new HashMap<>();
    private static final Map<Byte, String> bvMap = new HashMap<>();

    static {
        veMap.put("WEAPON_SWORD_ONE_HAND", WeaponTypeEnum.SWORD);
        veMap.put("WEAPON_CLAYMORE", WeaponTypeEnum.CLAYMORE);
        veMap.put("WEAPON_BOW", WeaponTypeEnum.BOW);
        veMap.put("WEAPON_CATALYST", WeaponTypeEnum.CATALYST);
        veMap.put("WEAPON_POLE", WeaponTypeEnum.POLEARM);
        veMap.forEach((k, v) -> {
            evMap.put(v, k);
            bvMap.put(v.getCode(), k);
        });
    }

    public static WeaponTypeEnum string2Enum(String v){
        return veMap.get(v);
    }

    public static String enum2String(WeaponTypeEnum e){
        return evMap.get(e);
    }

    public static Byte string2Byte(String v){
        WeaponTypeEnum e = veMap.get(v);
        return e != null ? e.getCode() : null;
    }

    public static String byte2String(Byte v){
        return bvMap.get(v);
    }

}
