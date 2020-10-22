package modelo;

public class Words {
	private String idWord;

	public Words(String idWord2) {
		super();
		this.setIdWord(idWord2);
	}

	public String getIdWord() {
		return idWord;
	}

	public void setIdWord(String idWord2) {
		this.idWord = idWord2;
	}

	@Override
	public String toString() {
		return "Words [idWord=" + idWord + "]";
	}
	
	

	

}
