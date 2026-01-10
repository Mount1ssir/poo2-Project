public abstract class Personne {
	private String nom;
	private String prenom;
	private int age;
	public Personne() {
		nom=" ";
		prenom=" ";
		age=0;
	}
	public Personne(String nom, String prenom,int age) {
		this.nom=nom;
		this.prenom=prenom;
		this.age=age;
	}
	public String getNom() {
		return this.nom;
	}
	public void setNom(String nom) {
		this.nom=nom;
	}
	public void setPrenom(String prenom) {
		this.prenom=prenom;
	}
	public String getPrenom() {
		return this.prenom;
	}
	public int getAge() {
		return this.age;
	}
	public void setAge(int age) {
		this.age=age;
	}
	public String toString(){
		return this.nom +" "+ this.prenom+" ("+this.age+" ans )";
	}
}
