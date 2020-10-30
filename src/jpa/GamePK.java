package jpa;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the game database table.
 * 
 */
@Embeddable
public class GamePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int idgame;

	@Column(name="users_username1", insertable=false, updatable=false)
	private String usersUsername1;

	public GamePK() {
	}
	public int getIdgame() {
		return this.idgame;
	}
	public void setIdgame(int idgame) {
		this.idgame = idgame;
	}
	public String getUsersUsername1() {
		return this.usersUsername1;
	}
	public void setUsersUsername1(String usersUsername1) {
		this.usersUsername1 = usersUsername1;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof GamePK)) {
			return false;
		}
		GamePK castOther = (GamePK)other;
		return 
			(this.idgame == castOther.idgame)
			&& this.usersUsername1.equals(castOther.usersUsername1);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idgame;
		hash = hash * prime + this.usersUsername1.hashCode();
		
		return hash;
	}
}