
import java.util.Date;
public class RendezVous {
	private Patient patient;
	private Medecin medecin;
	private Date dateRV;
	static int codeRV=0;
	private int code;
    private boolean estConfirme;
    private boolean estAnnule;
	public RendezVous() {
	    patient = null; 
	    medecin = null;
	    dateRV = null;
	    code =++RendezVous.codeRV;
	}
	public RendezVous(Patient patient, Medecin medecin) { //pour un nouveau patien
	    this.patient = patient;
	    this.medecin = medecin;
	    this.dateRV = new Date();
	    this.code = ++RendezVous.codeRV;
	}
    public RendezVous(Patient patient, Medecin medecin,Date rv) {//pour un patien aui a deja un rendez-vous
        this.patient = patient;
        this.medecin = medecin;
        this.dateRV = rv;
        this.code = ++RendezVous.codeRV;
    }
	public int getCode(){
		return code;
	}
	public Patient getPatient(){
		return this.patient;
	}
	public void setPatient(Patient p){
		this.patient=p;
	}
	public void setMedecin(Medecin medecin) {
		this.medecin=medecin;
	}
	public Medecin getMedecin() {
		return this.medecin ;
	}
	public Date getDateRV() {
		return this.dateRV ;
	}
	public boolean estConfirme(){
		return estConfirme;
	}
	public boolean estAnnule(){
		return estAnnule;
	}
	public void confirme(){
		if (!estAnnule){
			this.estConfirme=true;
			System.out.println("le rendez-vous #"+code+" est confirme .");
		}
		else {
			System.out.println("vous ne pouvez pas confirme un rendez vous deja annuller");
		}
	}
	public void annuler(){
		this.estAnnule=true;
		this.estConfirme=false;
		System.out.println("le rendez-vous #"+code+" est annuler .");
	}
	public void modifierDate(Date dt){
		if(!estAnnule){
			this.dateRV=dt;
			System.out.println("le nouveau date de le redez-vous #"+code+" est "+dateRV);
		}
		else{
			System.out.println("te peut pas changer la date d'un rendez-vous annulee.");
		}
	}
	public String toString(){
		return "le rendez-vous sous le code #"+code+" concern Mr"+this.patient.getNom()+" "+this.patient.getPrenom()+
				"avec Dr. "+this.medecin.getNom() + this.medecin.getPrenom() +" a "+dateRV;
	}
}












