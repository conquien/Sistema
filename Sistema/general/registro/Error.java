package registro;

import java.sql.Time;
import java.util.Date;

import baseDeDatos.BDD;

public class Error {
	int user;
	Exception e;
	
	public Error(int user, Exception e){
		this.user = user;
		this.e = e;
	}
	
	public void guardarError(int user, Exception e, Date fecha, Time hora){
		Error err = new Error(user, e);
		BDD bdd = new BDD();
		
	}
}
