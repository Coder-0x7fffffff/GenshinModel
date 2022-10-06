package space.xiami.project.genshinmodel.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import space.xiami.project.genshincommon.enums.LanguageEnum;
import space.xiami.project.genshindataviewer.domain.model.*;
import space.xiami.project.genshinmodel.client.CalculateService;
import space.xiami.project.genshinmodel.manager.WeaponManager;
import space.xiami.project.genshinmodel.rest.AvatarRestTemplate;
import space.xiami.project.genshinmodel.rest.ReliquaryRestTemplate;
import space.xiami.project.genshinmodel.rest.WeaponRestTemplate;
import space.xiami.project.genshinmodel.util.FileUtil;
import space.xiami.project.genshinmodel.util.PathUtil;
import space.xiami.project.genshinmodel.util.converter.EquipPropTypeConverter;
import space.xiami.project.genshinmodel.util.converter.WeaponTypeConverter;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author Xiami
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    private Logger log = LoggerFactory.getLogger(AdminController.class);

    @Resource
    private CalculateService calculateService;

    @Resource
    private WeaponRestTemplate weaponRestTemplate;

    @Resource
    private ReliquaryRestTemplate reliquaryRestTemplate;

    @Resource
    private AvatarRestTemplate avatarRestTemplate;

    @Resource
    private WeaponManager weaponManager;

    @Value("${debug.password:coder0x7fffffff}")
    private String password;

    private static String equipPropTypeFile = "equipPropType.json";

    private static String weaponTypeFile = "weaponType.json";

    @RequestMapping("/refreshConfig")
    public String refreshConfig(
            String pw,
            @RequestParam(name = "type", defaultValue = "all") String type
    ) {
        if(!password.equals(pw)){
            return "Password error";
        }
        // prop -> names
        Map<String, Set<String>> equipPropType = new HashMap<>();
        Map<String, Set<String>> weaponType = new HashMap<>();
        LanguageEnum[] langValue = LanguageEnum.values();
        // Weapon
        if(type.equals("all") || type.equals("weapon")){
            Collection weaponIds = weaponRestTemplate.list(LanguageEnum.EN.getCode()).values();
            weaponIds.forEach(id -> {
                long start = System.currentTimeMillis();
                Weapon weapon = weaponRestTemplate.getById(Long.valueOf((Integer)id), LanguageEnum.EN.getCode());
                for(LanguageEnum language : langValue){
                    Weapon langWeapon = weaponRestTemplate.getById(Long.valueOf((Integer)id), language.getCode());
                    // weaponType
                    String weaponTypeKey = weapon.getWeaponType();
                    String weaponTypeVal = langWeapon.getWeaponType();
                    weaponType.computeIfAbsent(weaponTypeKey, v -> new HashSet<>()).add(weaponTypeVal);
                    // weaponProperties
                    int levelPropLen = weapon.getWeaponProperties().size();
                    for(int idx = 0; idx < levelPropLen; idx++){
                        LevelProperty ori = weapon.getWeaponProperties().get(idx);
                        LevelProperty lori = langWeapon.getWeaponProperties().get(idx);
                        int len = ori.getProperties().size();
                        for(int idx1 = 0; idx1 < len; idx1++){
                            String key = ori.getProperties().get(idx1).getPropType().replaceAll(" ", "");
                            String val = lori.getProperties().get(idx1).getPropType();
                            equipPropType.computeIfAbsent(key, v -> new HashSet<>())
                                    .add(val);
                        }
                    }
                    //weaponEquipAffixes
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
                                String key = oria.get(idx2).getPropType().replaceAll(" ", "");
                                String val = loria.get(idx2).getPropType();
                                equipPropType.computeIfAbsent(key, v -> new HashSet<>())
                                        .add(val);
                            }
                        }
                    }
                }
                log.info("weaponID: {}, time: {}ms", id, System.currentTimeMillis() - start);
            });
        }
        // Reliquary
        if(type.equals("all") || type.equals("reliquary")){
            Collection<List> reliquaryIds = reliquaryRestTemplate.list(LanguageEnum.EN.getCode()).values();
            Set<Long> setIds = new HashSet<>();
            reliquaryIds.forEach(ids -> {
                ids.forEach(id -> {
                    long start = System.currentTimeMillis();
                    Reliquary reliquary = reliquaryRestTemplate.getById(Long.valueOf((Integer) id), LanguageEnum.EN.getCode());
                    setIds.add(reliquary.getSetId());
                    log.info("reliquaryID: {}, time: {}ms", id, System.currentTimeMillis() - start);
                });
            });
            setIds.forEach(setId -> {
                if(setId == null){
                    return;
                }
                long start = System.currentTimeMillis();
                ReliquarySet reliquarySet = reliquaryRestTemplate.getSetById(setId, LanguageEnum.EN.getCode());
                if(reliquarySet.getGroupEquipAffix() == null){
                    return;
                }
                for(LanguageEnum language : langValue){
                    ReliquarySet langReliquarySet = reliquaryRestTemplate.getSetById(setId, language.getCode());
                    int affixLen = reliquarySet.getGroupEquipAffix().size();
                    for(int idx = 0; idx < affixLen; idx++){
                        List<EquipAffix> ori = new ArrayList<>(reliquarySet.getGroupEquipAffix().values());
                        List<EquipAffix> lori = new ArrayList<>(langReliquarySet.getGroupEquipAffix().values());
                        int len = ori.size();
                        for(int idx1 = 0; idx1 < len; idx1++){
                            List<AddProperty> oria = ori.get(idx1).getAddProperties();
                            List<AddProperty> loria = lori.get(idx1).getAddProperties();
                            int len1 = oria.size();
                            for(int idx2 = 0; idx2 < len1; idx2++){
                                String key = oria.get(idx2).getPropType().replaceAll(" ", "");
                                String val = loria.get(idx2).getPropType();
                                equipPropType.computeIfAbsent(key, v -> new HashSet<>())
                                        .add(val);
                            }
                        }
                    }
                }
                log.info("reliquarySetID: {}, time: {}ms", setId, System.currentTimeMillis() - start);
            });
        }
        // Avatar
        if(type.equals("all") || type.equals("avatar")){
            Collection avatarIds = avatarRestTemplate.list(LanguageEnum.EN.getCode()).values();
            avatarIds.forEach(id -> {
                long start = System.currentTimeMillis();
                Avatar avatar = avatarRestTemplate.getById(Long.valueOf((Integer) id), LanguageEnum.EN.getCode());
                for(LanguageEnum language : langValue){
                    Avatar langAvatar = avatarRestTemplate.getById(avatar.getId(), language.getCode());
                    // avatarProperty
                    int avatarPropLen = avatar.getAvatarProperties().size();
                    for(int idx = 0; idx<avatarPropLen; idx++){
                        LevelProperty avatarProp = avatar.getAvatarProperties().get(idx);
                        LevelProperty langAvatarProp = langAvatar.getAvatarProperties().get(idx);
                        int propLen = avatarProp.getProperties().size();
                        for(int idx1 = 0; idx1<propLen; idx1++){
                            String key = avatarProp.getProperties().get(idx1).getPropType().replaceAll(" ", "");
                            String val = langAvatarProp.getProperties().get(idx1).getPropType();
                            equipPropType.computeIfAbsent(key, v -> new HashSet<>())
                                    .add(val);
                        }
                    }
                    // talent
                    int talentLen = avatar.getTalents().size();
                    for(int idx = 0; idx<talentLen; idx++){
                        Avatar.Talent avatarTalent = avatar.getTalents().get(idx);
                        Avatar.Talent langAvatarTalent = langAvatar.getTalents().get(idx);
                        int addPropLen = avatarTalent.getAddProperties().size();
                        for(int idx1 = 0; idx1<addPropLen; idx1++){
                            String key = avatarTalent.getAddProperties().get(idx1).getPropType().replaceAll(" ", "");
                            String val = langAvatarTalent.getAddProperties().get(idx1).getPropType();
                            equipPropType.computeIfAbsent(key, v -> new HashSet<>())
                                    .add(val);
                        }
                    }
                }
                log.info("reliquaryID: {}, time: {}ms", id, System.currentTimeMillis() - start);
            });
        }
        // check repeat key
        Set<String> equipPropTypeValueSet = new HashSet<>();
        Set<String> weaponTypeValueSet = new HashSet<>();
        equipPropType.values().forEach(set -> {
            set.forEach(value -> {
                if(equipPropTypeValueSet.contains(value)){
                    log.warn("repeat value: {}", value);
                }
                equipPropTypeValueSet.add(value);
            });
        });
        weaponType.values().forEach(set -> {
            set.forEach(value -> {
                if(weaponTypeValueSet.contains(value)){
                    log.warn("repeat value: {}", value);
                }
                weaponTypeValueSet.add(value);
            });
        });
        // write
        try{
            // equipPropType
            FileUtil.writeFile(PathUtil.getConfigDirectory() + equipPropTypeFile,
                    JSON.toJSONString(equipPropType, SerializerFeature.PrettyFormat).getBytes(StandardCharsets.UTF_8));
            EquipPropTypeConverter.refresh();
            // weaponType
            FileUtil.writeFile(PathUtil.getConfigDirectory() + weaponTypeFile,
                    JSON.toJSONString(weaponType, SerializerFeature.PrettyFormat).getBytes(StandardCharsets.UTF_8));
            WeaponTypeConverter.refresh();
        }catch (Exception e){
            log.error("error", e);
        }
        return "刷新成功";
    }

    @RequestMapping("/getWeaponById")
    public space.xiami.project.genshinmodel.domain.equipment.weapon.Weapon getWeaponById(Long id, String level, Integer rr){
        return weaponManager.getById(id, level, rr);
    }

    @RequestMapping("/getWeaponByName")
    public space.xiami.project.genshinmodel.domain.equipment.weapon.Weapon getWeaponByName(String name, String level, Integer rr){
        return weaponManager.getByName(name, level, rr);
    }
}
