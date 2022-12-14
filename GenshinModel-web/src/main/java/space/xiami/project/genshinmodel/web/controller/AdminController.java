package space.xiami.project.genshinmodel.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import space.xiami.project.genshincommon.NumberRange;
import space.xiami.project.genshincommon.enums.LanguageEnum;
import space.xiami.project.genshindataviewer.domain.model.*;
import space.xiami.project.genshinmodel.client.CalculateService;
import space.xiami.project.genshinmodel.domain.context.CalculateAttributeContext;
import space.xiami.project.genshinmodel.domain.entry.bonus.AbstractBonus;
import space.xiami.project.genshinmodel.factory.AvatarFactory;
import space.xiami.project.genshinmodel.factory.ReliquaryFactory;
import space.xiami.project.genshinmodel.factory.WeaponFactory;
import space.xiami.project.genshinmodel.manager.CalculateManager;
import space.xiami.project.genshinmodel.rest.AvatarRestTemplate;
import space.xiami.project.genshinmodel.rest.ReliquaryRestTemplate;
import space.xiami.project.genshinmodel.rest.WeaponRestTemplate;
import space.xiami.project.genshinmodel.util.ConfigUtil;
import space.xiami.project.genshinmodel.util.FileUtil;
import space.xiami.project.genshinmodel.util.PathUtil;
import space.xiami.project.genshinmodel.util.converter.AttributeLevelPropTypeConverter;
import space.xiami.project.genshinmodel.util.converter.EquipPropTypeConverter;
import space.xiami.project.genshinmodel.util.converter.ReliquaryTypeConverter;
import space.xiami.project.genshinmodel.util.converter.WeaponTypeConverter;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

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
    private WeaponFactory weaponFactory;

    @Resource
    private AvatarFactory avatarFactory;

    @Resource
    private ReliquaryFactory reliquaryFactory;

    @Resource
    private CalculateManager calculateManager;

    @Value("${debug.password:coder0x7fffffff}")
    private String password;

    private static String attributeLevelPropTypeFile = "attributeLevelPropType.json";

    private static String equipPropTypeFile = "equipPropType.json";

    private static String weaponTypeFile = "weaponType.json";

    private static String reliquaryTypeFile = "reliquaryType.json";

    private static String elementalTypeFile = "elementalType.json";

    @RequestMapping(value = "/refreshConfig", produces = "text/html;charset=UTF-8")
    public String refreshConfig(
            String pw,
            @RequestParam(name = "type", defaultValue = "all") String type
    ) {
        if(!password.equals(pw)){
            return "Password error";
        }
        // prop -> names
        Map<String, Set<String>> attributeLevelPropType = new HashMap<>();
        Map<String, Set<String>> equipPropType = new HashMap<>();
        Map<String, Set<String>> weaponType = new HashMap<>();
        Map<String, Set<String>> reliquaryType = new HashMap<>();
        Map<String, Set<String>> elementalType = new HashMap<>();
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
            Collection reliquaryId = reliquaryRestTemplate.list(LanguageEnum.EN.getCode()).values();
            Set<Long> setIds = new HashSet<>();
            reliquaryId.forEach(id -> {
                long start = System.currentTimeMillis();
                Reliquary reliquary = reliquaryRestTemplate.getById(Long.valueOf((Integer) id), LanguageEnum.EN.getCode());
                for(LanguageEnum language : langValue) {
                    Reliquary langReliquary = reliquaryRestTemplate.getById(Long.valueOf((Integer) id), language.getCode());
                    // weaponType
                    String reliquaryTypeKey = reliquary.getEquipType();
                    String reliquaryTypeVal = langReliquary.getEquipType();
                    reliquaryType.computeIfAbsent(reliquaryTypeKey, v -> new HashSet<>())
                            .add(reliquaryTypeVal);
                }
                setIds.add(reliquary.getSetId());
                log.info("reliquaryID: {}, time: {}ms", id, System.currentTimeMillis() - start);
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
                Avatar avatar = avatarRestTemplate.getById(Long.valueOf((Integer) id), null, LanguageEnum.EN.getCode());
                for(LanguageEnum language : langValue){
                    Avatar langAvatar = avatarRestTemplate.getById(avatar.getId(), null, language.getCode());
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
                            attributeLevelPropType.computeIfAbsent(key, v -> new HashSet<>())
                                    .add(val);
                        }
                    }
                    // active skill
                    int skillDepotLen = avatar.getSkillActive().size();
                    for(int idx = 0; idx<skillDepotLen; idx++){
                        List<Avatar.ActiveSkill> activeSkills = avatar.getSkillActive().values().stream().flatMap(Collection::stream).collect(Collectors.toList());
                        List<Avatar.ActiveSkill> langActiveSkills = langAvatar.getSkillActive().values().stream().flatMap(Collection::stream).collect(Collectors.toList());
                        int activeSkillLen = activeSkills.size();
                        for(int idx2 = 0; idx2 < activeSkillLen; idx2++){
                            String key = activeSkills.get(idx2).getCostElemType();
                            String val = langActiveSkills.get(idx2).getCostElemType();
                            if(key != null && val != null){
                                elementalType.computeIfAbsent(key, v -> new HashSet<>())
                                        .add(val);
                            }
                        }
                    }
                    // talent
                    int talentLen = avatar.getTalents().size();
                    for(int idx = 0; idx<talentLen; idx++){
                        avatar.getTalents().forEach((k, v) ->{
                            int tLen = avatar.getTalents().get(k).size();
                            for(int idx2 = 0; idx2<tLen; idx2++){
                                Avatar.Talent avatarTalent = avatar.getTalents().get(k).get(idx2);
                                Avatar.Talent langAvatarTalent = langAvatar.getTalents().get(k).get(idx2);
                                int addPropLen = avatarTalent.getAddProperties().size();
                                for(int idx1 = 0; idx1<addPropLen; idx1++){
                                    String key = avatarTalent.getAddProperties().get(idx1).getPropType().replaceAll(" ", "");
                                    String val = langAvatarTalent.getAddProperties().get(idx1).getPropType();
                                    equipPropType.computeIfAbsent(key, g -> new HashSet<>())
                                            .add(val);
                                }
                            }
                        });
                    }
                }
                log.info("reliquaryID: {}, time: {}ms", id, System.currentTimeMillis() - start);
            });
        }
        // check repeat key
        Set<String> attributeLevelPropTypeValueSet = new HashSet<>();
        Set<String> equipPropTypeValueSet = new HashSet<>();
        Set<String> weaponTypeValueSet = new HashSet<>();
        Set<String> reliquaryTypeValueSet = new HashSet<>();
        Set<String> elementalTypeValueSet = new HashSet<>();
        attributeLevelPropType.values().forEach(set -> {
            set.forEach(value -> {
                if(attributeLevelPropTypeValueSet.contains(value)){
                    log.warn("repeat value: {}", value);
                }
                attributeLevelPropTypeValueSet.add(value);
            });
        });
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
        reliquaryType.values().forEach(set -> {
            set.forEach(value -> {
                if(reliquaryTypeValueSet.contains(value)){
                    log.warn("repeat value: {}", value);
                }
                reliquaryTypeValueSet.add(value);
            });
        });
        elementalType.values().forEach(set -> {
            set.forEach(value -> {
                if(elementalTypeValueSet.contains(value)){
                    log.warn("repeat value: {}", value);
                }
                elementalTypeValueSet.add(value);
            });
        });
        // write
        try{
            ConfigUtil.refresh();
            // attributeLevelPropType
            FileUtil.writeFile(PathUtil.getConfigDirectory() + attributeLevelPropTypeFile,
                    JSON.toJSONString(attributeLevelPropType, SerializerFeature.PrettyFormat).getBytes(StandardCharsets.UTF_8));
            AttributeLevelPropTypeConverter.refresh();
            // equipPropType
            FileUtil.writeFile(PathUtil.getConfigDirectory() + equipPropTypeFile,
                    JSON.toJSONString(equipPropType, SerializerFeature.PrettyFormat).getBytes(StandardCharsets.UTF_8));
            EquipPropTypeConverter.refresh();
            // weaponType
            FileUtil.writeFile(PathUtil.getConfigDirectory() + weaponTypeFile,
                    JSON.toJSONString(weaponType, SerializerFeature.PrettyFormat).getBytes(StandardCharsets.UTF_8));
            WeaponTypeConverter.refresh();
            // reliquaryType
            FileUtil.writeFile(PathUtil.getConfigDirectory() + reliquaryTypeFile,
                    JSON.toJSONString(reliquaryType, SerializerFeature.PrettyFormat).getBytes(StandardCharsets.UTF_8));
            ReliquaryTypeConverter.refresh();
            // elementalType
            FileUtil.writeFile(PathUtil.getConfigDirectory() + elementalTypeFile,
                    JSON.toJSONString(elementalType, SerializerFeature.PrettyFormat).getBytes(StandardCharsets.UTF_8));
            ReliquaryTypeConverter.refresh();
        }catch (Exception e){
            log.error("error", e);
        }
        return "????????????";
    }

    @RequestMapping("/getWeaponById")
    public space.xiami.project.genshinmodel.domain.equipment.weapon.Weapon getWeaponById(Long id, String level, Integer refinementRank){
        return weaponFactory.getById(id, level, refinementRank);
    }

    @RequestMapping("/getWeaponByName")
    public space.xiami.project.genshinmodel.domain.equipment.weapon.Weapon getWeaponByName(String name, String level, Integer refinementRank){
        return weaponFactory.getByName(name, level, refinementRank);
    }

    @RequestMapping("/getAvatarById")
    public space.xiami.project.genshinmodel.domain.avatar.Avatar getAvatarById(Long id, Byte elementalType, String level, Integer talentLevel, String skillLevel){
        Map<String, NumberRange<Integer>> skillLevelRange = avatarFactory.getSkillLevelRangeById(id);
        return avatarFactory.getById(id, elementalType, level, fillSkillLevelMap(skillLevel, skillLevelRange), talentLevel);
    }

    @RequestMapping("/getAvatarByName")
    public space.xiami.project.genshinmodel.domain.avatar.Avatar getAvatarByName(String name, Byte elementalType, String level, Integer talentLevel, String skillLevel){
        Map<String, NumberRange<Integer>> skillLevelRange = avatarFactory.getSkillLevelRangeByName(name);
        return avatarFactory.getByName(name, elementalType, level, fillSkillLevelMap(skillLevel, skillLevelRange), talentLevel);
    }

    @RequestMapping("/getAvatarSkillLevelRangeById")
    public Map<String, NumberRange<Integer>> getAvatarSkillLevelRangeById(Long id){
        return avatarFactory.getSkillLevelRangeById(id);
    }

    @RequestMapping("/getAvatarSkillLevelRangeByName")
    public Map<String, NumberRange<Integer>> getAvatarSkillLevelRangeByName(String name){
        return avatarFactory.getSkillLevelRangeByName(name);
    }

    @RequestMapping("/getReliquaryById")
    public space.xiami.project.genshinmodel.domain.equipment.reliquary.Reliquary getReliquaryById(Long id, Integer level, String bonuses) {
        return reliquaryFactory.getById(id, level, convertBonus(bonuses));
    }

    @RequestMapping("/getReliquaryByName")
    public space.xiami.project.genshinmodel.domain.equipment.reliquary.Reliquary getReliquaryByName(String name, Integer level, String bonuses) {
        return reliquaryFactory.getByName(name, level, convertBonus(bonuses));
    }

    @RequestMapping("/listReliquary")
    public Map list(){
        return reliquaryFactory.list();
    }

    @RequestMapping("/getReliquarySetById")
    public space.xiami.project.genshinmodel.domain.equipment.reliquary.ReliquarySet getReliquarySetById(Long id){
        return reliquaryFactory.getSetById(id);
    }

    @RequestMapping("/listReliquarySet")
    public Map listSet(){
        return reliquaryFactory.listSet();
    }

    @RequestMapping("/createReliquarySet")
    public List<space.xiami.project.genshinmodel.domain.equipment.reliquary.ReliquarySet> createReliquarySet(String reliquaries){
        // ????????????reliquaries=72340@20@?????????-24~72320@20@?????????-24~71350@20@?????????-24~71340@20@?????????-24~72320@20@?????????-24
        return reliquaryFactory.createReliquarySet(convertReliquaries(reliquaries));
    }

    @RequestMapping("/test")
    public void test(){
        List<space.xiami.project.genshinmodel.domain.avatar.Avatar> avatars = new ArrayList<>();
        Map<String, NumberRange<Integer>> range = avatarFactory.getSkillLevelRangeByName("?????????");
        Map<String, Integer> skillLevel = new HashMap<>();
        range.keySet().forEach(name -> {
            skillLevel.put(name, 1);
        });
        space.xiami.project.genshinmodel.domain.avatar.Avatar avatar0 = avatarFactory.getByName(
                "?????????",
                null,
                "20",
                skillLevel,
                0
        );
        space.xiami.project.genshinmodel.domain.equipment.weapon.Weapon weapon0 = weaponFactory.getByName(
                "????????????",
                "80",
                1
        );
        avatar0.setWeapons(Collections.singletonList(weapon0));
        avatars.add(avatar0);
        CalculateAttributeContext context = new CalculateAttributeContext(avatars);
        calculateManager.calculateAttribute(context);
    }

    private List<space.xiami.project.genshinmodel.domain.equipment.reliquary.Reliquary> convertReliquaries(String reliquaries){
        String[] reliquaryStrArr = reliquaries.split("~");
        return Arrays.stream(reliquaryStrArr).map(reliquaryStr -> {
            String[] param = reliquaryStr.split("@");
            return getReliquaryById(Long.valueOf(
                    param[0]),
                    Integer.valueOf(param[1]),
                    param[2]
            );
        }).collect(Collectors.toList());
    }

    private static List<AbstractBonus> convertBonus(String bonuses){
        if(!StringUtils.hasLength(bonuses)){
            return null;
        }
        String[] bonusStrArr = bonuses.split("_");
        List<AbstractBonus> bonusList = new ArrayList<>();
        Arrays.stream(bonusStrArr).forEach(str -> {
            String[] strings = str.split("-");
            if(strings.length == 2){
                bonusList.add(EquipPropTypeConverter.property2Bonus(strings[0], Double.valueOf(strings[1]))
                );
            }
        });
        return bonusList;
    }

    private static Map<String, Integer> fillSkillLevelMap(String skillLevelStr, Map<String, NumberRange<Integer>> range){
        String[] skillLevelStrArr = skillLevelStr.split("_");
        Map<String, Integer> skillLevel = new HashMap<>();
        List<String> keys = new ArrayList<>(range.keySet());
        for(int idx = 0; idx<keys.size();idx++){
            int level = skillLevelStrArr.length > idx ?
                    Integer.parseInt(skillLevelStrArr[idx]) : 1;
            skillLevel.put(keys.get(idx), level);
        }
        return skillLevel;
    }
}
