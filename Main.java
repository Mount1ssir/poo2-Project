public class Main {

    public static void main(String[] args) {
        System.out.println("=== SYSTÈME DE GESTION MÉDICALE - TEST COMPLET ===\n");
        
        // 1. Création des médecins
        System.out.println("--- 1. CRÉATION DES MÉDECINS ---");
        Medecin med1 = new Medecin("Mustapha", "Mountassir", 20, "Cardiologie", 5000);
        Medecin med2 = new Medecin("youness", "essabri", 20, "Pédiatrie", 100);
        Medecin med3 = new Medecin("flan", "Ahmed", 52, "Générale", 300);
        System.out.println(med1);
        System.out.println(med2);
        System.out.println(med3);
        System.out.println();
        
        // 2. Création des patients
        System.out.println("--- 2. CRÉATION DES PATIENTS ---");
        Patient patient1 = new Patient("Tazi", "Mohammed", 35);
        Patient patient2 = new Patient("Idrissi", "Amina", 28);
        Patient patient3 = new Patient("Benjelloun", "Karim", 42);
        System.out.println(patient1);
        System.out.println(patient2);
        System.out.println(patient3);
        System.out.println();
        
        // 3. Prise de rendez-vous
        System.out.println("--- 3. PRISE DE RENDEZ-VOUS ---");
        patient1.prenerRendezVous(med1);
        patient2.prenerRendezVous(med2);
        patient3.prenerRendezVous(med3);
        
        RendezVous rv1 = patient1.getRendezVous();
        RendezVous rv2 = patient2.getRendezVous();
        RendezVous rv3 = patient3.getRendezVous();
        
        System.out.println(rv1);
        System.out.println(rv2);
        System.out.println(rv3);
        System.out.println();
        
        // 4. Confirmation et annulation de rendez-vous
        System.out.println("--- 4. GESTION DES RENDEZ-VOUS ---");
        rv1.confirme();
        rv2.confirme();
        rv3.annuler();
        System.out.println("RV1 confirmé: " + rv1.estConfirme());
        System.out.println("RV2 confirmé: " + rv2.estConfirme());
        System.out.println("RV3 annulé: " + rv3.estAnnule());
        System.out.println();
        
        // 5. Création des consultations
        System.out.println("--- 5. CRÉATION DES CONSULTATIONS ---");
        Consultation consult1 = new Consultation(patient1, med1);
        Consultation consult2 = new Consultation(patient2, med2);
        
        // Simulation d'attente (pour tester la durée)
        try {
            System.out.println("Consultation en cours...");
            Thread.sleep(2000); // 2 secondes
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println(consult1);
        System.out.println(consult2);
        System.out.println();
        
        // 6. Création des médicaments
        System.out.println("--- 6. CRÉATION DES MÉDICAMENTS ---");
        Medicament med_doliprane = new Medicament("Doliprane", 25, 100);
        Medicament med_amoxicilline = new Medicament("Amoxicilline", 45, 50);
        Medicament med_paracetamol = new Medicament("Paracétamol", 15, 200);
        Medicament med_ibuprofene = new Medicament("Ibuprofène", 30, 75);
        Medicament med_aspegic = new Medicament("Aspégic", 20, 150);
        
        System.out.println(med_doliprane);
        System.out.println(med_amoxicilline);
        System.out.println(med_paracetamol);
        System.out.println(med_ibuprofene);
        System.out.println(med_aspegic);
        System.out.println();
        
        // 7. Vérification de disponibilité des médicaments
        System.out.println("--- 7. VÉRIFICATION DISPONIBILITÉ ---");
        System.out.println("Doliprane disponible (30 unités): " + 
            med_doliprane.verifierDisponiblilite(30));
        System.out.println("Amoxicilline disponible (60 unités): " + 
            med_amoxicilline.verifierDisponiblilite(60));
        System.out.println();
        
        // 8. Création des ordonnances
        System.out.println("--- 8. CRÉATION DES ORDONNANCES ---");
        Ordonnance ord1 = new Ordonnance(consult1);
        ord1.ajouterMedicament(med_doliprane, 2);
        ord1.ajouterMedicament(med_amoxicilline, 3);
        ord1.ajouterMedicament(med_paracetamol, 1);
        
        Ordonnance ord2 = new Ordonnance(consult2);
        ord2.ajouterMedicament(med_ibuprofene, 2);
        ord2.ajouterMedicament(med_aspegic, 1);
        
        System.out.println("Ordonnance 1 créée avec " + 
            ord1.getMedicamentsQuantiteMap().size() + " médicaments");
        System.out.println("Ordonnance 2 créée avec " + 
            ord2.getMedicamentsQuantiteMap().size() + " médicaments");
        System.out.println();
        
        // 9. Affichage des ordonnances
        System.out.println("--- 9. DÉTAILS DES ORDONNANCES ---");
        System.out.println(ord1);
        System.out.println();
        System.out.println(ord2);
        System.out.println();
        
        // 10. Calcul des prix
        System.out.println("--- 10. CALCUL DES PRIX ---");
        System.out.println("Prix total ordonnance 1: " + ord1.calculePrixTotale() + " DH");
        System.out.println("Prix total ordonnance 2: " + ord2.calculePrixTotale() + " DH");
        System.out.println();
        
        // 11. Test des paiements
        System.out.println("--- 11. TEST DES PAIEMENTS ---");
        System.out.println("\nTest 1: Paiement insuffisant");
        ord1.effectuerPaiment(100); // Montant insuffisant
        
        System.out.println("\nTest 2: Paiement exact");
        ord1.effectuerPaiment(ord1.calculePrixTotale());
        ord1.getStatusPayement();
        
        System.out.println("\nTest 3: Paiement avec monnaie");
        ord2.effectuerPaiment(1000);
        ord2.getStatusPayement();
        System.out.println();
        
        // 12. Ajout des ordonnances à l'historique des patients
        System.out.println("--- 12. HISTORIQUE DES PATIENTS ---");
        patient1.ajouterOrdonnance(ord1);
        patient2.ajouterOrdonnance(ord2);
        
        System.out.println("Historique patient 1:");
        for (Ordonnance ord : patient1.Historique()) {
            System.out.println("  - Ordonnance #" + ord.getCode());
        }
        
        System.out.println("Historique patient 2:");
        for (Ordonnance ord : patient2.Historique()) {
            System.out.println("  - Ordonnance #" + ord.getCode());
        }
        System.out.println();
        
        // 13. Test des consultations du patient
        System.out.println("--- 13. CONSULTATIONS DES PATIENTS ---");
        System.out.println("Consultations de " + patient1.getNom() + ":");
        for (Consultation c : patient1.obtenirConsultations()) {
            System.out.println("  - " + c);
        }
        System.out.println();
        
        // 14. Modification et suppression de médicaments
        System.out.println("--- 14. MODIFICATION D'ORDONNANCE ---");
        System.out.println("Avant suppression:");
        ord1.getMedicamentsQuantite();
        System.out.println();
        
        ord1.supprimerMedicament(med_paracetamol);
        System.out.println("\nAprès suppression du Paracétamol:");
        ord1.getMedicamentsQuantite();
        System.out.println();
        System.out.println("Nouveau prix total: " + ord1.calculePrixTotale() + " DH");
        System.out.println();
        
        // 15. Test des quantités de médicaments
        System.out.println("--- 15. GESTION DES STOCKS ---");
        System.out.println("Stock initial Doliprane: " + med_doliprane.getQuantite());
        med_doliprane.diminuerQuantite(10);
        System.out.println("Après vente de 10: " + med_doliprane.getQuantite());
        med_doliprane.augmenterQuantite(50);
        System.out.println("Après réapprovisionnement de 50: " + med_doliprane.getQuantite());
        System.out.println();
        
        // 16. Affichage GUI des ordonnances
        System.out.println("--- 16. AFFICHAGE GRAPHIQUE ---");
        System.out.println("Génération de l'interface graphique pour l'ordonnance 1...");
        ord1.printOrdannance();
        
        System.out.println("\nGénération de l'interface graphique pour l'ordonnance 2...");
        ord2.printOrdannance();
        
        System.out.println("\n=== FIN DES TESTS ===");
        System.out.println("Les fichiers texte des ordonnances ont été générés.");
        System.out.println("Les fenêtres GUI sont ouvertes (fermez-les pour terminer le programme).");
    }
}