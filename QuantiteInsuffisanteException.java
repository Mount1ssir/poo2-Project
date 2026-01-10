public class QuantiteInsuffisanteException extends Exception{
	public QuantiteInsuffisanteException(String text){
		super(text);
	}
	public QuantiteInsuffisanteException(String text , Throwable cause ){
		super(text,cause);
	}
}
