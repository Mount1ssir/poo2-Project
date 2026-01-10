public class Medicament {
	private String nom;
	private int prix;
	private int quantite;
	private static int codeMedicaments=0;
	private int code;
	public Medicament() {
		nom=" ";
		prix=0;
		quantite=0;
		code=++codeMedicaments;
	}
	public Medicament(String nom,int prix,int quantite) {
		this.nom=nom;
		this.prix=prix;
		this.quantite=quantite;
		code=++codeMedicaments;
	}
	public String getNom() {
		return this.nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getPrix() {
		return this.prix;
	}
	public void setPrix(int prix) {
		this.prix = prix;
	}
	public int getCode() {
		return this.code;
	}
	public int getQuantite(){
		return this.quantite;
	}
	public void diminuerQuantite(int quantite) {
		this.quantite=this.quantite-quantite;
	}
	public void augmenterQuantite(int quantite) {
		this.quantite=this.quantite+quantite;
	}
	public boolean verifierDisponiblilite(int quantiteNecessaire) {
		return this.quantite>=quantiteNecessaire;
	}
	public boolean estDisponible() {
		return this.quantite>0;
	}
	public String toString(){
		return "nom : "+this.nom+" ;code : "+this.code+" ;quantite : "+this.quantite+" ;prix unitaure :"+this.prix+" Dirhames";
	}
}
