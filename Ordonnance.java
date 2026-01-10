import java.util.HashMap;
import java.util.Map;
public class Ordonnance {
	private int code;
	private static int codeOrdanance = 0;
	private Map<Medicament,Integer> medicamentsQuantite ;
	private Consultation consultation;
	private boolean estPayee;

	public Ordonnance(Consultation consultation) {
		this.code=++codeOrdanance;
		this.medicamentsQuantite = new HashMap<>();
		this.consultation=consultation;
		consultation.terminerConsultation();// la creation de l'ordonnance implique la fin de la consultation 
	}
	public int getCode(){
		return this.code;
	}

	public void ajouterMedicament(Medicament e,int quantite){
		medicamentsQuantite.put(e,quantite);
	}
	public void supprimerMedicament(Medicament e){
        medicamentsQuantite.remove(e);
	}
	public void getMedicament() {
		StringBuilder medi= new StringBuilder(" ");
		for (Medicament key : medicamentsQuantite.keySet()){
			medi.append(",").append(key);
		}
		System.out.println("["+medi+"]");
	}
	public Map<Medicament,Integer> getMedicamentsQuantiteMap(){
		return this.medicamentsQuantite;
	}
	public Consultation getConsultation(){
		return this.consultation;
	}
	
	
	public int calculePrixTotale(){
		int total=0;
		total+=consultation.calculerCout();
		for (Medicament med : medicamentsQuantite.keySet()){
			int prix = medicamentsQuantite.get(med)*med.getPrix();
			total += prix;
		}
		return total;
	}
	public void getMedicamentsQuantite(){
		for ( Medicament med : medicamentsQuantite.keySet()){
			System.out.print(med.getNom()+"   ");
		}
	}
	public void getQuantite(){
		for (Map.Entry<Medicament, Integer> val : medicamentsQuantite.entrySet()){
			System.out.println(val.getKey()+" : "+val.getValue()+" elements");
		}
	}
	public boolean estVide(){
		return medicamentsQuantite.isEmpty();
	}

	
	public String toString(){
		String st="====detailles de l'ordonnance #"+code+"===="+"\n";
		st += "Medecin : "+consultation.getMedecine().getNom()+" "+consultation.getMedecine().getPrenom()+"\n";
		st += "Patient : "+consultation.getPatient().getNom()+" "+consultation.getPatient().getPrenom()+"\n";
		st += "Date de la consultation : "+consultation.getDateDebut()+"\n";
		st += "Duree de la consultation : "+consultation.dureeTotal()+" heures"+"\n";
		st += "Cout de la consultation : "+consultation.calculerCout()+" DH"+"\n";
		st += "Medicaments prescrits : "+"\n"+"   ";
		st += "     STATUS DE PAIEMENT  : "+estPayee+"\n"+"  ";
		st += ((estPayee)?"MERCIE! a bonne santée ":"khlass a sga3");
		for (Map.Entry<Medicament,Integer> val : medicamentsQuantite.entrySet()){
			st=st+val.getKey().getNom() +" : "+val.getValue()+" ("+val.getKey().getPrix()+" DH par unité)\n"+"   ";
		}
		st+="    prix total : "+calculePrixTotale()+" DH"+"\n";
		return st;
	}
	 public void getStatusPayement(){
        if( estPayee){
            System.out.println("le paiement #"+code+" est terminee !");
        }else{
            System.out.println("le paiement #"+code+" n'est pas encore terminee !");
        }
    }
	public void effectuerPaiment(int payment) {
		try{
			checkPayment(payment);
		}catch(Myexceptions e){
			System.out.println("Error :"+ e.getMessage());
		}
	}
	private void checkPayment(int montant) throws Myexceptions{
        long tarif = calculePrixTotale();
		if (montant < tarif) {
			throw new Myexceptions("Montant insuffisant! requis : " + tarif + " Dirhames, Fourni: " + montant + " Dirhames");
		}else {
			this.estPayee=true;
            int change = montant - (int) tarif;
            if (change == 0){
                System.out.println("payment terminee !");
            }else{
                System.out.println("payment terminee !"); // fichier les meds les prix et tous
                System.out.println("your change :"+ change+" Dirhames");
            }
		}
	}
	public void printOrdannance() {
		OrdonnanceGUI.afficherOrdonnance(this);
	}
}



