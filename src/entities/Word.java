package entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the words database table.
 * 
 */
@Entity
@Table(name="words")
@NamedQuery(name="Word.findAll", query="SELECT w FROM Word w")
public class Word implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="idword")
	private String idword;

	public Word() {
	}
	
	public Word(String idword) {
		this.idword = idword;
	}

	public String getIdword() {
		return this.idword;
	}

	public void setIdword(String idword) {
		this.idword = idword;
	}
	
	@Override
    public String toString() {
        return this.idword;
    }

}