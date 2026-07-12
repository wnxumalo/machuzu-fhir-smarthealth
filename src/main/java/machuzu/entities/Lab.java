package machuzu.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Lab implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private boolean bloodGroupRh;
    private boolean fBC;
    private boolean malaria;
    private boolean coombsTest;
    private boolean eSR;
    private boolean dDimer;
    private boolean pTINR;
    private boolean hemoglobin;
    private boolean cD4;
    private boolean cD4CD8CD3;
    
    private boolean liquidProfile;
    private boolean lFT;
    private boolean kFT;
    private boolean cardiacProfile;
    private boolean aRTPRofile;
    private boolean uECAProfile;
    private boolean cSFAnalysis;

    private boolean bHCG;
    private boolean t4;
    private boolean tSH;
    private boolean cortisol;
    private boolean hBsAg;
    private boolean urine;
    private boolean blood;
    
    private boolean syphilisRPR;
    private boolean cragLFA;
    private boolean hIV12;
    private boolean rDT;
    private boolean pCR;
    private boolean viralLoad;
    
    private boolean ceruloplasmin,aLT,aST,albumin,aLP,ammonia,amylase,bilirubin,direct,
            total,calcium,cO2,phosphorus,cholesterol,cholinesterase,haptoglobin,creatinine,
            creatinineKinase,gGT,glucose,hDLCholesterol,lDLCholesterol,lipase,totalIronBindingCapacity,
            iron,lDH,chloride,potassium,sodium,magnesium,urineBiochemistry,drugAbuseToxicology,
            totalProtein,pSA,uricAcid,totslIgE,aldolase,triglycerides,hbA1c,cKMB,lactate,
            myoglobin,troponinI,ntProBNP,ferritin;
    
    private boolean cA125,cA153,cA199;
    
    private boolean cultureSense,bloodCulture,aFBMicroscopy,urineMicroscopy;
    
    private boolean dNAPaternityTest,dNAKinshipTest,dNASiblingshipTest,hIVDrugResistanceTest;
    
    private boolean abdomen,pelvis,renal,testicular,thyroid,vascular,
            obstetrics,lung,musculoskeletal,breast,cerebrovascular,orthopedic,cardiac,
            gynecology,prostrate,urology;
    
    private boolean CRP,rheumatoidFactor;
    
    private boolean carbamazepine,digoxin,phenobarbital,
            amikacin,vancomycin,gentamicin,phenytoin,theophylline;
    
    private boolean ordered, results;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date labDate;   
   
    @JoinColumn(name="ENCOUNTER_ID", nullable=true)
    private Long encounter;
    
    public Lab() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public boolean getHmBloodGroupRh() {
        return bloodGroupRh;
    }

    public void setHmBloodGroupRh(boolean bloodGroupRh) {
        this.bloodGroupRh = bloodGroupRh;
    }
    
    public boolean getHmFBC() {
        return fBC;
    }

    public void setHmFBC(boolean fBC) {
        this.fBC = fBC;
    }
    
    public boolean getHmMalaria() {
        return malaria;
    }

    public void setHmMalaria(boolean malaria) {
        this.malaria = malaria;
    }
    
    public boolean getHmCoombsTest() {
        return coombsTest;
    }

    public void setHmCoombsTest(boolean coombsTest) {
        this.coombsTest = coombsTest;
    }
    
    public boolean getHmESR() {
        return eSR;
    }

    public void setHmESR(boolean eSR) {
        this.eSR = eSR;
    }
    
    public boolean getHmDDimer() {
        return dDimer;
    }

    public void setHmDDimer(boolean dDimer) {
        this.dDimer = dDimer;
    }
    
    public boolean getHmPTINR() {
        return pTINR;
    }

    public void setHmPTINR(boolean pTINR) {
        this.pTINR = pTINR;
    }
    
    public boolean getHmHemoglobin() {
        return hemoglobin;
    }

    public void setHmHemoglobin(boolean hemoglobin) {
        this.hemoglobin = hemoglobin;
    }
    
    public boolean getHmCD4() {
        return cD4;
    }

    public void setHmCD4(boolean cD4) {
        this.cD4 = cD4;
    }
    
    public boolean getHmCD4CD8CD3() {
        return cD4CD8CD3;
    }

    public void setHmCD4CD8CD3(boolean cD4CD8CD3) {
        this.cD4CD8CD3 = cD4CD8CD3;
    }
    
    public Long getEncounter() {
        return encounter;
    }

    public void setEncounter(Long encounter) {
        this.encounter = encounter;
    }

    public boolean getCpLiquidProfile() {
        return liquidProfile;
    }

    public void setCpLiquidProfile(boolean liquidProfile) {
        this.liquidProfile = liquidProfile;
    }
    
    public boolean getCpLFT() {
        return lFT;
    }

    public void setCpLFT(boolean lFT) {
        this.lFT = lFT;
    }
    
    public boolean getCpKFT() {
        return kFT;
    }

    public void setCpKFT(boolean kFT) {
        this.kFT = kFT;
    }
    
    public boolean getCpCardiacProfile() {
        return cardiacProfile;
    }

    public void setCpCardiacProfile(boolean cardiacProfile) {
        this.cardiacProfile = cardiacProfile;
    }
    
    public boolean getCpARTPRofile() {
        return aRTPRofile;
    }

    public void setCpARTPRofile(boolean aRTPRofile) {
        this.aRTPRofile = aRTPRofile;
    }
    
    public boolean getCpUECAProfile() {
        return uECAProfile;
    }

    public void setCpUECAProfile(boolean uECAProfile) {
        this.uECAProfile = uECAProfile;
    }
    
    public boolean getCpCSFAnalysis() {
        return cSFAnalysis;
    }

    public void setCpCSFAnalysis(boolean cSFAnalysis) {
        this.cSFAnalysis = cSFAnalysis;
    }
    
    public boolean getEyBHCG() {
        return bHCG;
    }

    public void setEyBHCG(boolean bHCG) {
        this.bHCG = bHCG;
    }
    
    public boolean getEyT4() {
        return t4;
    }

    public void setEyT4(boolean t4) {
        this.t4 = t4;
    }
    
    public boolean getEyTSH() {
        return tSH;
    }

    public void setEyTSH(boolean tSH) {
        this.tSH = tSH;
    }
    
    public boolean getEyCortisol() {
        return cortisol;
    }

    public void setEyCortisol(boolean cortisol) {
        this.cortisol = cortisol;
    }
    
    public boolean getEyHBsAg() {
        return hBsAg;
    }

    public void setEyHBsAg(boolean hBsAg) {
        this.hBsAg = hBsAg;
    }
    
    public boolean getEyUrine() {
        return urine;
    }

    public void setEyUrine(boolean urine) {
        this.urine = urine;
    }
    
    public boolean getEyBlood() {
        return blood;
    }

    public void setEyBlood(boolean blood) {
        this.blood = blood;
    }
    
    public boolean getSyphilisRPR() {
        return syphilisRPR;
    }

    public void setSyphilisRPR(boolean syphilisRPR) {
        this.syphilisRPR = syphilisRPR;
    }
    
    public boolean getCragLFA() {
        return cragLFA;
    }

    public void setCragLFA(boolean cragLFA) {
        this.cragLFA = cragLFA;
    }
    
    public boolean getHIV12() {
        return hIV12;
    }

    public void setHIV12(boolean hIV12) {
        this.hIV12 = hIV12;
    }
    
    public boolean getRDT() {
        return rDT;
    }

    public void setRDT(boolean rDT) {
        this.rDT = rDT;
    }
    
    public boolean getPCR() {
        return pCR;
    }

    public void setPCR(boolean pCR) {
        this.pCR = pCR;
    }
    
    public boolean getViralLoad() {
        return viralLoad;
    }

    public void setViralLoad(boolean viralLoad) {
        this.viralLoad = viralLoad;
    }
        
    public boolean getCeruloplasmin() {
        return ceruloplasmin;
    }

    public void setCeruloplasmin(boolean ceruloplasmin) {
        this.ceruloplasmin = ceruloplasmin;
    }
    
    public boolean getALT() {
        return aLT;
    }

    public void setALT(boolean aLT) {
        this.aLT = aLT;
    }
    
    public boolean getAST() {
        return aST;
    }

    public void setAST(boolean aST) {
        this.aST = aST;
    }
    
    public boolean getAlbumin() {
        return albumin;
    }

    public void setAlbumin(boolean albumin) {
        this.albumin = albumin;
    }
    
    public boolean getALP() {
        return aLP;
    }

    public void setALP(boolean aLP) {
        this.aLP = aLP;
    }
    
    public boolean getAmmonia() {
        return ammonia;
    }

    public void setAmmonia(boolean ammonia) {
        this.ammonia = ammonia;
    }
    
    public boolean getAmylase() {
        return amylase;
    }

    public void setAmylase(boolean amylase) {
        this.amylase = amylase;
    }
    
    public boolean getBilirubin() {
        return bilirubin;
    }

    public void setBilirubin(boolean bilirubin) {
        this.bilirubin = bilirubin;
    }
    
    public boolean getDirect() {
        return direct;
    }

    public void setDirect(boolean direct) {
        this.direct = direct;
    }
    
    public boolean getTotal() {
        return total;
    }

    public void setTotal(boolean total) {
        this.total = total;
    }
    
    public boolean getCalcium() {
        return calcium;
    }

    public void setCalcium(boolean calcium) {
        this.calcium = calcium;
    }
    
    public boolean getCO2() {
        return cO2;
    }

    public void setCO2(boolean cO2) {
        this.cO2 = cO2;
    }
    
    public boolean getPhosphorus() {
        return phosphorus;
    }

    public void setPhosphorus(boolean phosphorus) {
        this.phosphorus = phosphorus;
    }
    
    public boolean getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(boolean cholesterol) {
        this.cholesterol = cholesterol;
    }
    
    public boolean getCholinesterase() {
        return cholinesterase;
    }

    public void setCholinesterase(boolean cholinesterase) {
        this.cholinesterase = cholinesterase;
    }
    
    public boolean getHaptoglobin() {
        return haptoglobin;
    }

    public void setHaptoglobin(boolean haptoglobin) {
        this.haptoglobin = haptoglobin;
    }
    
    public boolean getCreatinine() {
        return creatinine;
    }

    public void setCreatinine(boolean creatinine) {
        this.creatinine = creatinine;
    }
    
    public boolean getCreatinineKinase() {
        return creatinineKinase;
    }

    public void setCreatinineKinase(boolean creatinineKinase) {
        this.creatinineKinase = creatinineKinase;
    }
    
    public boolean getGGT() {
        return gGT;
    }

    public void setGGT(boolean gGT) {
        this.gGT = gGT;
    }
    
    public boolean getGlucose() {
        return glucose;
    }

    public void setGlucose(boolean glucose) {
        this.glucose = glucose;
    }
    
    public boolean getHDLCholesterol() {
        return hDLCholesterol;
    }

    public void setHDLCholesterol(boolean hDLCholesterol) {
        this.hDLCholesterol = hDLCholesterol;
    }
    
    public boolean getLDLCholesterol() {
        return lDLCholesterol;
    }

    public void setLDLCholesterol(boolean lDLCholesterol) {
        this.lDLCholesterol = lDLCholesterol;
    }
    
    public boolean getLipase() {
        return lipase;
    }

    public void setLipase(boolean lipase) {
        this.lipase = lipase;
    }
    
    public boolean getTotalIronBindingCapacity() {
        return totalIronBindingCapacity;
    }

    public void setTotalIronBindingCapacity(boolean totalIronBindingCapacity) {
        this.totalIronBindingCapacity = totalIronBindingCapacity;
    }
    
    public boolean getIron() {
        return iron;
    }

    public void setIron(boolean iron) {
        this.iron = iron;
    }
    
    public boolean getLDH() {
        return lDH;
    }

    public void setLDH(boolean lDH) {
        this.lDH = lDH;
    }
    
    public boolean getChloride() {
        return chloride;
    }

    public void setChloride(boolean chloride) {
        this.chloride = chloride;
    }
    
    public boolean getPotassium() {
        return potassium;
    }

    public void setPotassium(boolean potassium) {
        this.potassium = potassium;
    }
    
    public boolean getSodium() {
        return sodium;
    }

    public void setSodium(boolean sodium) {
        this.sodium = sodium;
    }
    
    public boolean getMagnesium() {
        return magnesium;
    }

    public void setMagnesium(boolean magnesium) {
        this.magnesium = magnesium;
    }
        
    public boolean getUrineBiochemistry() {
        return urineBiochemistry;
    }

    public void setUrineBiochemistry(boolean urineBiochemistry) {
        this.urineBiochemistry = urineBiochemistry;
    }
    
    public boolean getDrugAbuseToxicology() {
        return drugAbuseToxicology;
    }

    public void setDrugAbuseToxicology(boolean drugAbuseToxicology) {
        this.drugAbuseToxicology = drugAbuseToxicology;
    }
    
    public boolean getTotalProtein() {
        return totalProtein;
    }

    public void setTotalProtein(boolean totalProtein) {
        this.totalProtein = totalProtein;
    }
    
    public boolean getPSA() {
        return pSA;
    }

    public void setPSA(boolean pSA) {
        this.pSA = pSA;
    }
    
    public boolean getUricAcid() {
        return uricAcid;
    }

    public void setUricAcid(boolean uricAcid) {
        this.uricAcid = uricAcid;
    }
    
    public boolean getTotslIgE() {
        return totslIgE;
    }

    public void setTotslIgE(boolean totslIgE) {
        this.totslIgE = totslIgE;
    }
    
    public boolean getAldolase() {
        return aldolase;
    }

    public void setAldolase(boolean aldolase) {
        this.aldolase = aldolase;
    }
    
    public boolean getTriglycerides() {
        return triglycerides;
    }

    public void setTriglycerides(boolean triglycerides) {
        this.triglycerides = triglycerides;
    }
    
    public boolean getHbA1c() {
        return hbA1c;
    }

    public void setHbA1c(boolean hbA1c) {
        this.hbA1c = hbA1c;
    }
    
    public boolean getCKMB() {
        return cKMB;
    }

    public void setCKMB(boolean cKMB) {
        this.cKMB = cKMB;
    }
    
    public boolean getLactate() {
        return lactate;
    }

    public void setLactate(boolean lactate) {
        this.lactate = lactate;
    }
    public boolean getMyoglobin() {
        return myoglobin;
    }

    public void setMyoglobin(boolean myoglobin) {
        this.myoglobin = myoglobin;
    }
    
    public boolean getTroponinI() {
        return troponinI;
    }

    public void setTroponinI(boolean troponinI) {
        this.troponinI = troponinI;
    }
    
    public boolean getNtProBNP() {
        return ntProBNP;
    }

    public void setNtProBNP(boolean ntProBNP) {
        this.ntProBNP = ntProBNP;
    }
    
    public boolean getFerritin() {
        return ferritin;
    }

    public void setFerritin(boolean ferritin) {
        this.ferritin = ferritin;
    }
    
    public boolean getCA125() {
        return cA125;
    }

    public void setCA125(boolean cA125) {
        this.cA125 = cA125;
    }
    
    public boolean getCA153() {
        return cA153;
    }

    public void setCA153(boolean cA153) {
        this.cA153 = cA153;
    }
    
    public boolean getCA199() {
        return cA199;
    }

    public void setCA199(boolean cA199) {
        this.cA199 = cA199;
    }
    
    public boolean getCultureSense() {
        return cultureSense;
    }

    public void setCultureSense(boolean cultureSense) {
        this.cultureSense = cultureSense;
    }
    
    public boolean getBloodCulture() {
        return bloodCulture;
    }

    public void setBloodCulture(boolean bloodCulture) {
        this.bloodCulture = bloodCulture;
    }
    
    public boolean getAFBMicroscopy() {
        return aFBMicroscopy;
    }

    public void setAFBMicroscopy(boolean aFBMicroscopy) {
        this.aFBMicroscopy = aFBMicroscopy;
    }
    
    public boolean getUrineMicroscopy() {
        return urineMicroscopy;
    }

    public void setUrineMicroscopy(boolean urineMicroscopy) {
        this.urineMicroscopy = urineMicroscopy;
    }
    
    public boolean getDNAPaternityTest() {
        return dNAPaternityTest;
    }

    public void setDNAPaternityTest(boolean dNAPaternityTest) {
        this.dNAPaternityTest = dNAPaternityTest;
    }
    
    public boolean getDNAKinshipTest() {
        return dNAKinshipTest;
    }

    public void setDNAKinshipTest(boolean dNAKinshipTest) {
        this.dNAKinshipTest = dNAKinshipTest;
    }
    
    public boolean getDNASiblingshipTest() {
        return dNASiblingshipTest;
    }

    public void setDNASiblingshipTest(boolean dNASiblingshipTest) {
        this.dNASiblingshipTest = dNASiblingshipTest;
    }
    
    public boolean getHIVDrugResistanceTest() {
        return hIVDrugResistanceTest;
    }

    public void setHIVDrugResistanceTest(boolean hIVDrugResistanceTest) {
        this.hIVDrugResistanceTest = hIVDrugResistanceTest;
    }
    
    public boolean getAbdomen() {
        return abdomen;
    }

    public void setAbdomen(boolean abdomen) {
        this.abdomen = abdomen;
    }
    
    public boolean getPelvis() {
        return pelvis;
    }

    public void setPelvis(boolean pelvis) {
        this.pelvis = pelvis;
    }
    
    public boolean getRenal() {
        return renal;
    }

    public void setRenal(boolean renal) {
        this.renal = renal;
    }
    
    public boolean getTesticular() {
        return testicular;
    }

    public void setTesticular(boolean testicular) {
        this.testicular = testicular;
    }
    
    public boolean getThyroid() {
        return thyroid;
    }

    public void setThyroid(boolean thyroid) {
        this.thyroid = thyroid;
    }
    
    public boolean getVascular() {
        return vascular;
    }

    public void setVascular(boolean vascular) {
        this.vascular = vascular;
    }
    
    public boolean getobstetrics() {
        return obstetrics;
    }
    
    public void setObstetrics(boolean obstetrics) {
        this.obstetrics = obstetrics;
    }

    public void setLung(boolean lung) {
        this.lung = lung;
    }
    
    public boolean getLung() {
        return lung;
    }

    public void setMusculoskeletal(boolean musculoskeletal) {
        this.musculoskeletal = musculoskeletal;
    }
    
    public boolean getMusculoskeletal() {
        return musculoskeletal;
    }

    public void setBreast(boolean breast) {
        this.breast = breast;
    }
    
    public boolean getBreast() {
        return breast;
    }

    public void setCerebrovascular(boolean cerebrovascular) {
        this.cerebrovascular = cerebrovascular;
    }
    
    public boolean getCerebrovascular() {
        return cerebrovascular;
    }
    
    public void setOrthopedic(boolean orthopedic) {
        this.orthopedic = orthopedic;
    }
    
    public boolean getOrthopedic() {
        return orthopedic;
    }
    
    public void setCardiac(boolean cardiac) {
        this.cardiac = cardiac;
    }
    
    public boolean getCardiac() {
        return cardiac;
    }
    
    public void setGynecology(boolean gynecology) {
        this.gynecology = gynecology;
    }
    
    public boolean getGynecology() {
        return gynecology;
    }
    
    public void setProstrate(boolean prostrate) {
        this.prostrate = prostrate;
    }
    
    public boolean getProstrate() {
        return prostrate;
    }
    
    public void setUrology(boolean urology) {
        this.urology = urology;
    }
    
    public boolean getUrology() {
        return urology;
    }
    
    public void setCRP(boolean CRP) {
        this.CRP = CRP;
    }
    
    public boolean getCRP() {
        return CRP;
    }
    
    public void setRheumatoidFactor(boolean rheumatoidFactor) {
        this.rheumatoidFactor = rheumatoidFactor;
    }
    
    public boolean getRheumatoidFactor() {
        return rheumatoidFactor;
    }

    public void setCarbamazepine(boolean carbamazepine) {
        this.carbamazepine = carbamazepine;
    }
    
    public boolean getCarbamazepine() {
        return carbamazepine;
    }
    
    public void setDigoxin(boolean digoxin) {
        this.digoxin = digoxin;
    }
    
    public boolean getDigoxin() {
        return digoxin;
    }
    
    public void setPhenobarbital(boolean phenobarbital) {
        this.phenobarbital = phenobarbital;
    }
    
    public boolean getPhenobarbital() {
        return phenobarbital;
    }
    
    public void setAmikacin(boolean amikacin) {
        this.amikacin = amikacin;
    }
    
    public boolean getAmikacin() {
        return amikacin;
    }
    
    public void setVancomycin(boolean vancomycin) {
        this.vancomycin = vancomycin;
    }
    
    public boolean getVancomycin() {
        return vancomycin;
    }
    
    public void setGentamicin(boolean gentamicin) {
        this.gentamicin = gentamicin;
    }
    
    public boolean getGentamicin() {
        return gentamicin;
    }
    
    public void setPhenytoin(boolean phenytoin) {
        this.phenytoin = phenytoin;
    }
    
    public boolean getPhenytoin() {
        return phenytoin;
    }
    
    public void setTheophylline(boolean theophylline) {
        this.theophylline = theophylline;
    }
    
    public boolean getTheophylline() {
        return theophylline;
    }
    
    public void setOrdered(boolean ordered) {
        this.ordered = ordered;
    }

    public boolean getOrdered() {        
        return ordered;
    }

    public void setResults(boolean results) {
        this.results = results;
    }

    public boolean getResults() {        
        return results;
    }
    
    public void setLabDate(Date labDate) {
        this.labDate = labDate;
    }

    public Date getLabDate() {        
        return labDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lab)) {
            return false;
        }
        Lab other = (Lab) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "machuzu.entities.Lab[ id=" + id + " ]";
    }
}