import java.util.Date;
public class Consultation {
	private Date dateDebut;
	private Date dateFin;
	private Patient patient;
    private Medecin medecin;
	private static int codeConsultation=0;
	private int code;
    private boolean estTermine=false;

	public Consultation() {
		patient=null;
		code=++codeConsultation;
		medecin = null;
		dateDebut =new Date();
	}
	public Consultation(Patient patient,Medecin medecin) {  
		this.patient=patient;
		this.medecin=medecin;
		code=++codeConsultation;
		dateDebut =new Date();
	}
	
	public int getCode() {
		return this.code;
	}
	public Patient getPatient() {
		return this.patient;
	}
	public void setPatient(Patient patient) {
		this.patient=patient;
	}
	public Medecin getMedecine() {
		return this.medecin;
	}
	public void setMedecin(Medecin medecin) {
		this.medecin=medecin;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
		
	public Date getDateDebut() {
		return this.dateDebut;
	}
	public Date getDateFin() {
        return this.dateFin;
	}
    public String estTermine(){
        return (estTermine?"consultation terminne":"consultation n'a pas encore terminnee");

    }
	public void terminerConsultation() {
		if (!estTermine) {
			estTermine=true;
				dateFin=new Date();
		}
	}
	public long calculerCout(){
        if (!estTermine || dateFin ==null){
            return 0;
        }
        else{
            return this.medecin.getTarifParHeure()*this.dureeTotal();
        }
	}
	public long dureeTotal() { //calculer nbr d'heurs de la consultation
        if (dateFin == null) {
            return 0;
        }
        long y = (this.dateFin.getTime() - this.dateDebut.getTime()) / (1000 * 60);
        if (y < 60) {
            return 1;
        } else if (y % 60 < 30) {
            return y / 60;
        } else {
            return y / 60 + 1;
        }
    }
    public String toString(){
        return " code : "+this.code +",patien : Mr. "+this.patient.getNom()+",medecin : Dr. "+this.medecin.getNom() + "\n etat :" +(estTermine? "termine " : "en cours");
    }
}
