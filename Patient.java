import java.util.List;
import java.util.ArrayList;
public class Patient extends Personne {
	private RendezVous rendezVous; // un seul rendez-vous a la fois !!?
	static int counterPatient=0;
	private int code;
    private List<Ordonnance> historiqueOrdonnances;

	public Patient() {
		super();
        rendezVous = null;
        code=++counterPatient;
        historiqueOrdonnances = new ArrayList<>();
	}
	public Patient(String nom, String prenom,int age,RendezVous rv) {
		super(nom,prenom,age);
		code=++counterPatient;
        this.rendezVous=rv;
        historiqueOrdonnances = new ArrayList<>();
	}
    public Patient(String nom, String prenom,int age) {
        super(nom,prenom,age);
        code=++counterPatient;
        historiqueOrdonnances = new ArrayList<>();
    }
	public int getCode() {
		return this.code;
	}
    public RendezVous getRendezVous(){
        return this.rendezVous;
    }
    public void prenerRendezVous(Medecin med){
        this.rendezVous= new RendezVous(this,med);
    }
    public void annulerRenderVous(){
        this.rendezVous=null;
    }
	public void ajouterOrdonnance(Ordonnance con){
        historiqueOrdonnances.add(con);
    }
	public List<Ordonnance> Historique() {
		return historiqueOrdonnances;
	}
    public List<Consultation> obtenirConsultations(){
        List<Consultation> list = new ArrayList<>();
        for (Ordonnance con : historiqueOrdonnances){
            if (con.getConsultation()!=null){
                list.add(con.getConsultation());
            }
        }
        return list;
    }
    
    public String toString(){
        String st = 
        "==patient info==" + "\n"
        + super.toString()+ "\n"
        + "code : " + this.code + "\n"
        + "Historique des ordonnances  : " + "\n";
        for (Ordonnance ord : historiqueOrdonnances){
            st += "ordonnance " + ord.getCode() + ",";
        }
        return st;
    }


}
