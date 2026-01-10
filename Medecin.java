public class Medecin extends Personne {
    private String spicialite;
    private int tarifParHeure;
    private int code;
    private static int medecinCode=0;
    public Medecin(){
        super();
        spicialite="Générale";
        tarifParHeure=100;
        code=++medecinCode;
    }
    public Medecin(String nom, String prenom,int age,String spe,int tar){
        super(nom,prenom,age);
        spicialite=spe;
        tarifParHeure=tar;
        code=++medecinCode;
    }
    public int getCode(){
        return this.code;
    }
    public int getTarifParHeure(){
        return this.tarifParHeure;
    }
    public void setTarifParHeure(int tar){
        this.tarifParHeure=tar;
    }
    public void getSpicialite(String spicialite){
        this.spicialite=spicialite;
    }
    public String getSpicialite(){
        return this.spicialite;
    }
    public String toString(){
        return "Dr. "+super.toString() + " - "+this.spicialite+" : "+this.tarifParHeure+" par heure;" ;
    }

}