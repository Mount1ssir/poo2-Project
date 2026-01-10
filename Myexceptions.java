public class Myexceptions extends Exception {
	public Myexceptions(String text){
		super(text);
	}
	public Myexceptions(String text , Throwable cause ){
		super(text,cause);
	}
}
