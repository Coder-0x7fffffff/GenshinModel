package space.xiami.project.genshinmodel.web.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import space.xiami.project.genshincommon.enums.LanguageEnum;
import space.xiami.project.genshindataviewer.domain.model.AddProperty;
import space.xiami.project.genshindataviewer.domain.model.EquipAffix;
import space.xiami.project.genshindataviewer.domain.model.LevelProperty;
import space.xiami.project.genshindataviewer.domain.model.Weapon;
import space.xiami.project.genshinmodel.client.CalculateService;
import space.xiami.project.genshinmodel.manager.WeaponManager;
import space.xiami.project.genshinmodel.rest.WeaponRestTemplate;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author Xiami
 */
@RestController
@RequestMapping("/debug")
public class DebugController {

    @Resource
    private CalculateService calculateService;

    @Resource
    private WeaponRestTemplate weaponRestTemplate;

    @Resource
    private WeaponManager weaponManager;

    @Value("${debug.password:coder0x7fffffff}")
    private String password;

    @RequestMapping("/property")
    public String property(String pw) {
        if(!password.equals(pw)){
            return "Password error";
        }
        // prop -> lang -> names
        Map<String, Set<String>> nameMap = new HashMap<>();
        Collection ids = weaponRestTemplate.list(LanguageEnum.EN.getCode()).values();
        LanguageEnum[] langValue = LanguageEnum.values();
        ids.forEach(id -> {
            System.out.println("ID: "+ id);
            Weapon weapon = weaponRestTemplate.getById(Long.valueOf((Integer)id), LanguageEnum.EN.getCode());
            for(LanguageEnum language : langValue){
                Weapon langWeapon = weaponRestTemplate.getById(Long.valueOf((Integer)id), language.getCode());
                // weaponProperties
                int levelPropLen = weapon.getWeaponProperties().size();
                for(int idx = 0; idx < levelPropLen; idx++){
                    LevelProperty ori = weapon.getWeaponProperties().get(idx);
                    LevelProperty lori = langWeapon.getWeaponProperties().get(idx);
                    int len = ori.getProperties().size();
                    for(int idx1 = 0; idx1 < len; idx1++){
                        String key = ori.getProperties().get(idx1).getPropType();
                        String val = lori.getProperties().get(idx1).getPropType();
                        nameMap.computeIfAbsent(key, v -> new HashSet<>())
                                .add(val);
                    }
                }
                int affixLen = weapon.getWeaponEquipAffixes().size();
                for(int idx = 0; idx < affixLen; idx++){
                    List<EquipAffix> ori = weapon.getWeaponEquipAffixes().get(idx).getEquipAffix();
                    List<EquipAffix> lori = langWeapon.getWeaponEquipAffixes().get(idx).getEquipAffix();
                    int len = ori.size();
                    for(int idx1 = 0; idx1 < len; idx1++){
                        List<AddProperty> oria = ori.get(idx1).getAddProperties();
                        List<AddProperty> loria = lori.get(idx1).getAddProperties();
                        int len1 = oria.size();
                        for(int idx2 = 0; idx2 < len1; idx2++){
                            String key = oria.get(idx2).getPropType();
                            String val = loria.get(idx2).getPropType();
                            nameMap.computeIfAbsent(key, v -> new HashSet<>())
                                    .add(val);
                        }
                    }
                }
            }
        });
        //TODO 圣遗物+天赋+技能
        String json = JSON.toJSONString(nameMap);
        System.out.println(json);
        return json;
    }

}
