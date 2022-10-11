package space.xiami.project.genshinmodel.domain.entry.attributes;

import space.xiami.project.genshincommon.exception.EntryException;
import space.xiami.project.genshinmodel.domain.entry.Entry;

import java.lang.reflect.Field;

/**
 * @author Xiami
 */
public class Attributes {

    // 基础属性
    /**
     * 生命上限
     */
    private BaseHP baseHP = new BaseHP();

    /**
     * 攻击力
     */
    private BaseATK baseATK = new BaseATK();

    /**
     * 防御力
     */
    private BaseDEF baseDEF = new BaseDEF();

    /**
     * 元素精通
     */
    private ElementalMastery elementalMastery = new ElementalMastery();

    /**
     * 体力上限
     */
    private MaxStamina maxStamina = new MaxStamina();

    //进阶属性
    /**
     * 暴击率
     */
    private CRITRate critRate = new CRITRate();

    /**
     * 暴击伤害
     */
    private CRITDMG critDmg = new CRITDMG();

    /**
     * 治疗加成
     */
    private HealingBonus healingBonus = new HealingBonus();

    /**
     * 受治疗加成
     */
    private IncomingHealingBonus incomingHealingBonus = new IncomingHealingBonus();

    /**
     * 元素充能效率
     */
    private EnergyRecharge energyRecharge = new EnergyRecharge();

    /**
     * 冷却缩减
     */
    private CDReduction cdReduction = new CDReduction();

    /**
     * 护盾强效
     */
    private ShieldStrength shieldStrength = new ShieldStrength();

    //元素属性
    /**
     * 火元素伤害加成
     */
    private PyroDMGBonus pyroDMGBonus = new PyroDMGBonus();

    /**
     * 火元素抗性
     */
    private PyroRES pyroRES = new PyroRES();

    /**
     * 水元素伤害加成
     */
    private HydroDMGBonus hydroDMGBonus = new HydroDMGBonus();

    /**
     * 水元素抗性
     */
    private HydroRES hydroRES = new HydroRES();

    /**
     * 草元素伤害加成
     */
    private DendroDMGBonus dendroDMGBonus = new DendroDMGBonus();

    /**
     * 草元素抗性
     */
    private DendroRES dendroRES = new DendroRES();

    /**
     * 雷元素伤害加成
     */
    private ElectroDMGBonus electroDMGBonus = new ElectroDMGBonus();

    /**
     * 雷元素抗性
     */
    private ElectroRES electroRES = new ElectroRES();

    /**
     * 风元素伤害加成
     */
    private AnemoDMGBonus anemoDMGBonus = new AnemoDMGBonus();

    /**
     * 风元素抗性
     */
    private AnemoRES anemoRES = new AnemoRES();

    /**
     * 冰元素伤害加成
     */
    private CryoDMGBonus cryoDMGBonus = new CryoDMGBonus();

    /**
     * 冰元素抗性
     */
    private CryoRES cryoRES = new CryoRES();

    /**
     * 岩元素伤害加成
     */
    private GeoDMGBonus geoDMGBonus = new GeoDMGBonus();

    /**
     * 岩元素抗性
     */
    private GeoRES geoRES = new GeoRES();

    /**
     * 物理伤害加成
     */
    private PhysicalDMGBonus physicalDMGBonus = new PhysicalDMGBonus();

    /**
     * 物理抗性
     */
    private PhysicalRES physicalRES = new PhysicalRES();
    
    public Entry getEntry(String name) throws EntryException {
        try{
            Field[] fields = this.getClass().getDeclaredFields();
            for(Field field : fields){
                if(field.getName().equalsIgnoreCase(name)){
                    boolean oldAccess = field.isAccessible();
                    field.setAccessible(true);
                    Entry res = (Entry) field.get(this);
                    field.setAccessible(oldAccess);
                    return res;
                }
            }
        }catch (Exception ignore){
            throw new EntryException();
        }
        return null;
    }

    public void setEntry(String name, Entry entry) throws EntryException {
        try{
            Field[] fields = this.getClass().getDeclaredFields();
            for(Field field : fields){
                String simpleName = field.getType().getSimpleName();
                if(simpleName.equalsIgnoreCase(name)){
                    boolean oldAccess = field.isAccessible();
                    field.setAccessible(true);
                    field.set(this, entry);
                    field.setAccessible(oldAccess);
                    return;
                }
            }
        }catch (Exception ignore){
            throw new EntryException();
        }
    }

    public double getEntryValue(String name) throws EntryException {
        try{
            Field[] fields = this.getClass().getDeclaredFields();
            for(Field field : fields){
                if(field.getType().getSimpleName().equalsIgnoreCase(name)){
                    boolean oldAccess = field.isAccessible();
                    field.setAccessible(true);
                    double ret =  ((Entry) field.get(this)).getValue();
                    field.setAccessible(oldAccess);
                    return ret;
                }
            }
        }catch (Exception ignore){
            throw new EntryException();
        }
        return 0;
    }

    public void setEntryValue(String name, double value) throws EntryException {
        try{
            Field[] fields = this.getClass().getDeclaredFields();
            for(Field field : fields){
                if(field.getType().getSimpleName().equalsIgnoreCase(name)){
                    boolean oldAccess = field.isAccessible();
                    field.setAccessible(true);
                    ((Entry) field.get(this)).setValue(value);
                    field.setAccessible(oldAccess);
                    return;
                }
            }
        }catch (Exception ignore){
            throw new EntryException();
        }
    }

    public BaseHP getBaseHP() {
        return baseHP;
    }

    public void setBaseHP(BaseHP baseHP) {
        this.baseHP = baseHP;
    }

    public BaseATK getBaseATK() {
        return baseATK;
    }

    public void setBaseATK(BaseATK baseATK) {
        this.baseATK = baseATK;
    }

    public BaseDEF getBaseDEF() {
        return baseDEF;
    }

    public void setBaseDEF(BaseDEF baseDEF) {
        this.baseDEF = baseDEF;
    }

    public ElementalMastery getElementalMastery() {
        return elementalMastery;
    }

    public void setElementalMastery(ElementalMastery elementalMastery) {
        this.elementalMastery = elementalMastery;
    }

    public MaxStamina getMaxStamina() {
        return maxStamina;
    }

    public void setMaxStamina(MaxStamina maxStamina) {
        this.maxStamina = maxStamina;
    }

    public CRITRate getCritRate() {
        return critRate;
    }

    public void setCritRate(CRITRate critRate) {
        this.critRate = critRate;
    }

    public CRITDMG getCritDmg() {
        return critDmg;
    }

    public void setCritDmg(CRITDMG critDmg) {
        this.critDmg = critDmg;
    }

    public HealingBonus getHealingBonus() {
        return healingBonus;
    }

    public void setHealingBonus(HealingBonus healingBonus) {
        this.healingBonus = healingBonus;
    }

    public IncomingHealingBonus getIncomingHealingBonus() {
        return incomingHealingBonus;
    }

    public void setIncomingHealingBonus(IncomingHealingBonus incomingHealingBonus) {
        this.incomingHealingBonus = incomingHealingBonus;
    }

    public EnergyRecharge getEnergyRecharge() {
        return energyRecharge;
    }

    public void setEnergyRecharge(EnergyRecharge energyRecharge) {
        this.energyRecharge = energyRecharge;
    }

    public CDReduction getCdReduction() {
        return cdReduction;
    }

    public void setCdReduction(CDReduction cdReduction) {
        this.cdReduction = cdReduction;
    }

    public ShieldStrength getShieldStrength() {
        return shieldStrength;
    }

    public void setShieldStrength(ShieldStrength shieldStrength) {
        this.shieldStrength = shieldStrength;
    }

    public PyroDMGBonus getPyroDMGBonus() {
        return pyroDMGBonus;
    }

    public void setPyroDMGBonus(PyroDMGBonus pyroDMGBonus) {
        this.pyroDMGBonus = pyroDMGBonus;
    }

    public PyroRES getPyroRES() {
        return pyroRES;
    }

    public void setPyroRES(PyroRES pyroRES) {
        this.pyroRES = pyroRES;
    }

    public HydroDMGBonus getHydroDMGBonus() {
        return hydroDMGBonus;
    }

    public void setHydroDMGBonus(HydroDMGBonus hydroDMGBonus) {
        this.hydroDMGBonus = hydroDMGBonus;
    }

    public HydroRES getHydroRES() {
        return hydroRES;
    }

    public void setHydroRES(HydroRES hydroRES) {
        this.hydroRES = hydroRES;
    }

    public DendroDMGBonus getDendroDMGBonus() {
        return dendroDMGBonus;
    }

    public void setDendroDMGBonus(DendroDMGBonus dendroDMGBonus) {
        this.dendroDMGBonus = dendroDMGBonus;
    }

    public DendroRES getDendroRES() {
        return dendroRES;
    }

    public void setDendroRES(DendroRES dendroRES) {
        this.dendroRES = dendroRES;
    }

    public ElectroDMGBonus getElectroDMGBonus() {
        return electroDMGBonus;
    }

    public void setElectroDMGBonus(ElectroDMGBonus electroDMGBonus) {
        this.electroDMGBonus = electroDMGBonus;
    }

    public ElectroRES getElectroRES() {
        return electroRES;
    }

    public void setElectroRES(ElectroRES electroRES) {
        this.electroRES = electroRES;
    }

    public AnemoDMGBonus getAnemoDMGBonus() {
        return anemoDMGBonus;
    }

    public void setAnemoDMGBonus(AnemoDMGBonus anemoDMGBonus) {
        this.anemoDMGBonus = anemoDMGBonus;
    }

    public AnemoRES getAnemoRES() {
        return anemoRES;
    }

    public void setAnemoRES(AnemoRES anemoRES) {
        this.anemoRES = anemoRES;
    }

    public CryoDMGBonus getCryoDMGBonus() {
        return cryoDMGBonus;
    }

    public void setCryoDMGBonus(CryoDMGBonus cryoDMGBonus) {
        this.cryoDMGBonus = cryoDMGBonus;
    }

    public CryoRES getCryoRES() {
        return cryoRES;
    }

    public void setCryoRES(CryoRES cryoRES) {
        this.cryoRES = cryoRES;
    }

    public GeoDMGBonus getGeoDMGBonus() {
        return geoDMGBonus;
    }

    public void setGeoDMGBonus(GeoDMGBonus geoDMGBonus) {
        this.geoDMGBonus = geoDMGBonus;
    }

    public GeoRES getGeoRES() {
        return geoRES;
    }

    public void setGeoRES(GeoRES geoRES) {
        this.geoRES = geoRES;
    }

    public PhysicalDMGBonus getPhysicalDMGBonus() {
        return physicalDMGBonus;
    }

    public void setPhysicalDMGBonus(PhysicalDMGBonus physicalDMGBonus) {
        this.physicalDMGBonus = physicalDMGBonus;
    }

    public PhysicalRES getPhysicalRES() {
        return physicalRES;
    }

    public void setPhysicalRES(PhysicalRES physicalRES) {
        this.physicalRES = physicalRES;
    }
}
