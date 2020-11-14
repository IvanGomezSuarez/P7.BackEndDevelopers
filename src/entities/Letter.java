package entities;
import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the letters database table.
 * 
 */
@Entity
@Table(name="letters")
@NamedQuery(name="Letter.findAll", query="SELECT l FROM Letter l")
public class Letter implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="idletter")
	private String idletter;

	public Letter() {		
	}
	
	public Letter(String idletter) {
		this.idletter = idletter;
	}

	public String getIdletter() {
		return this.idletter;
	}

	public void setIdletter(String idletter) {
		this.idletter = idletter;
	}

}