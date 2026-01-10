public class Main {

    public static void main(String[] args) {
        System.out.println("=== SYSTÈME DE GESTION MÉDICALE - TEST COMPLET ===\n");
        
        
        Medecin med1 = new Medecin("Mustapha", "Mountassir", 20, "Cardiologie", 5000);
        Medecin med2 = new Medecin("youness", "essabri", 20, "Pédiatrie", 100);
        Medecin med3 = new Medecin("flan", "Ahmed", 52, "Générale", 300);
        
        Patient patient1 = new Patient("Tazi", "Mohammed", 35);
        Patient patient2 = new Patient("Idrissi", "Amina", 28);
        Patient patient3 = new Patient("Benjelloun", "Karim", 42);
        
        Medicament med_doliprane = new Medicament("Doliprane", 25, 10);
        Medicament med_amoxicilline = new Medicament("Amoxicilline", 45, 50);
        Medicament med_paracetamol = new Medicament("Paracétamol", 15, 200);
        Medicament med_ibuprofene = new Medicament("Ibuprofène", 30, 75);
        Medicament med_aspegic = new Medicament("Aspégic", 20, 150);

        
        //le patiant demande un rendezvous
        patient1.prenerRendezVous(med1);
        patient2.prenerRendezVous(med2);
        patient3.prenerRendezVous(med3);
        RendezVous rv1 = patient1.getRendezVous();
        RendezVous rv2 = patient2.getRendezVous();
        RendezVous rv3 = patient3.getRendezVous();
        
        //le medcin confirme le rendezvous
        rv1.confirme();
        rv2.confirme();
        rv3.annuler();
        
        //lorsque le patiant est chez le medcin,le dernier declare une consultation
        Consultation consult1 = new Consultation(patient1, med1);
        Consultation consult2 = new Consultation(patient2, med2);
        
        //des que la consultation est terminer le medcin peut generer une ordonnance avec les medicamment prescrit
        Ordonnance ord1 = new Ordonnance(consult1);
        try {
        ord1.ajouterMedicament(med_doliprane, 2);
        ord1.ajouterMedicament(med_amoxicilline, 3);
        ord1.ajouterMedicament(med_paracetamol, 1);
        ord1.ajouterMedicament(med_doliprane, 20);
        }
        catch (QuantiteInsuffisanteException e) {
        	System.out.println(e.getMessage());
        }
        

        Ordonnance ord2 = new Ordonnance(consult2);
      
        try {
        	  ord2.ajouterMedicament(med_ibuprofene, 2);
              ord2.ajouterMedicament(med_aspegic, 1);
            }
            catch (QuantiteInsuffisanteException e) {
            	System.out.println(e.getMessage());
            }
     //en fin on ajoute l"ordonnance a l"historiqie des ordonnance du patient
        patient1.ajouterOrdonnance(ord1);
        patient2.ajouterOrdonnance(ord2);
        //on peut visualiser l'ordonnance dans un interface graphique
         ord1.printOrdannance();
        // de plus l'ordonnance est stocke dans un ficier
    }


}
