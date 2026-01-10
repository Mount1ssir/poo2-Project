import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;

public class OrdonnanceGUI extends JFrame {
    private Ordonnance ordonnance;
    
    public OrdonnanceGUI(Ordonnance ordonnance) {
        this.ordonnance = ordonnance;
        initializeUI();
        generateFile();
    }
    
    private void initializeUI() {
        setTitle("Ordonnance #" + ordonnance.getCode());
        setSize(600, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Main panel with padding
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);
        
        // Title
        JLabel titleLabel = new JLabel("ORDONNANCE MÉDICALE");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setForeground(new Color(41, 128, 185));
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        JLabel codeLabel = new JLabel("N° " + ordonnance.getCode());
        codeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        codeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(codeLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Separator
        JSeparator separator1 = new JSeparator();
        separator1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        mainPanel.add(separator1);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Doctor info
        JPanel doctorPanel = createInfoPanel("MÉDECIN", 
            ordonnance.getConsultation().getMedecine().getNom() + " " + 
            ordonnance.getConsultation().getMedecine().getPrenom(),
            "Spécialité: " + ordonnance.getConsultation().getMedecine().getSpicialite());
        mainPanel.add(doctorPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        
        // Patient info
        JPanel patientPanel = createInfoPanel("PATIENT", 
            ordonnance.getConsultation().getPatient().getNom() + " " + 
            ordonnance.getConsultation().getPatient().getPrenom(),
            "Âge: " + ordonnance.getConsultation().getPatient().getAge() + " ans");
        mainPanel.add(patientPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        
        // Consultation info
        JPanel consultationPanel = createInfoPanel("CONSULTATION", 
            "Date: " + ordonnance.getConsultation().getDateDebut(),
            "Durée: " + ordonnance.getConsultation().dureeTotal() + " heures - Coût: " + 
            ordonnance.getConsultation().calculerCout() + " DH");
        mainPanel.add(consultationPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Separator
        JSeparator separator2 = new JSeparator();
        separator2.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        mainPanel.add(separator2);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Medications section
        JLabel medLabel = new JLabel("MÉDICAMENTS PRESCRITS");
        medLabel.setFont(new Font("Arial", Font.BOLD, 16));
        medLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(medLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        // Medications list with scrollpane
        JPanel medsPanel = new JPanel();
        medsPanel.setLayout(new BoxLayout(medsPanel, BoxLayout.Y_AXIS));
        medsPanel.setBackground(new Color(236, 240, 241));
        medsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        for (var entry : ordonnance.getMedicamentsQuantiteMap().entrySet()) {
            JPanel medItem = new JPanel();
            medItem.setLayout(new BorderLayout());
            medItem.setBackground(Color.WHITE);
            medItem.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199)),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
            ));
            medItem.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
            
            JLabel medName = new JLabel("• " + entry.getKey().getNom());
            medName.setFont(new Font("Arial", Font.BOLD, 14));
            
            JLabel medDetails = new JLabel("Quantité: " + entry.getValue() + 
                " - Prix unitaire: " + entry.getKey().getPrix() + " DH");
            medDetails.setFont(new Font("Arial", Font.PLAIN, 12));
            medDetails.setForeground(new Color(127, 140, 141));
            
            JPanel textPanel = new JPanel();
            textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
            textPanel.setBackground(Color.WHITE);
            textPanel.add(medName);
            textPanel.add(medDetails);
            
            medItem.add(textPanel, BorderLayout.CENTER);
            medsPanel.add(medItem);
            medsPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        }
        
        JScrollPane scrollPane = new JScrollPane(medsPanel);
        scrollPane.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(189, 195, 199)));
        mainPanel.add(scrollPane);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Total price
        JPanel totalPanel = new JPanel();
        totalPanel.setLayout(new BorderLayout());
        totalPanel.setBackground(new Color(46, 204, 113));
        totalPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        totalPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        
        JLabel totalLabel = new JLabel("PRIX TOTAL");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        totalLabel.setForeground(Color.WHITE);
        
        JLabel totalAmount = new JLabel(ordonnance.calculePrixTotale() + " DH");
        totalAmount.setFont(new Font("Arial", Font.BOLD, 20));
        totalAmount.setForeground(Color.WHITE);
        
        totalPanel.add(totalLabel, BorderLayout.WEST);
        totalPanel.add(totalAmount, BorderLayout.EAST);
        mainPanel.add(totalPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        
        // Close button
        JButton closeButton = new JButton("Fermer");
        closeButton.setFont(new Font("Arial", Font.BOLD, 14));
        closeButton.setBackground(new Color(52, 73, 94));
        closeButton.setForeground(Color.WHITE);
        closeButton.setFocusPainted(false);
        closeButton.setBorderPainted(false);
        closeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        closeButton.setPreferredSize(new Dimension(200, 40));
        closeButton.setMaximumSize(new Dimension(200, 40));
        closeButton.addActionListener(e -> dispose());
        mainPanel.add(closeButton);
        
        // Add main panel to scrollpane for long ordonnances
        JScrollPane mainScrollPane = new JScrollPane(mainPanel);
        mainScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(mainScrollPane);
    }
    
    private JPanel createInfoPanel(String title, String line1, String line2) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(236, 240, 241));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199)),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 12));
        titleLabel.setForeground(new Color(127, 140, 141));
        
        JLabel line1Label = new JLabel(line1);
        line1Label.setFont(new Font("Arial", Font.BOLD, 14));
        
        JLabel line2Label = new JLabel(line2);
        line2Label.setFont(new Font("Arial", Font.PLAIN, 12));
        line2Label.setForeground(new Color(127, 140, 141));
        
        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(line1Label);
        panel.add(line2Label);
        
        return panel;
    }
    
    private void generateFile() {
        String textContent = ordonnance.toString();
        
        try (FileWriter writer = new FileWriter("Ordonnance" + ordonnance.getCode() + ".txt")) {
            writer.write(textContent);
            System.out.println("Ordonnance générée avec succès: Ordonnance" + ordonnance.getCode() + ".txt");
        } catch (IOException e) {
            System.out.println("Erreur lors de la génération de l'ordonnance: " + e.getMessage());
            JOptionPane.showMessageDialog(this, 
                "Erreur lors de la génération du fichier: " + e.getMessage(),
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void afficherOrdonnance(Ordonnance ordonnance) {
        SwingUtilities.invokeLater(() -> {
            OrdonnanceGUI gui = new OrdonnanceGUI(ordonnance);
            gui.setVisible(true);
        });
    }
}