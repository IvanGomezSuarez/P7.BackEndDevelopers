package modelo;

public class Letters {
	private String idLetter;
	
	

	public Letters(String idLetter) {
		super();
		this.idLetter = idLetter;
	}

	public String getIdLetter() {
		return idLetter;
	}

	public void setIdLetter(String idLetter) {
		this.idLetter = idLetter;
	}

	@Override
	public String toString() {
		return "Letters [idLetter=" + idLetter + "]";
	}
	
	


}
