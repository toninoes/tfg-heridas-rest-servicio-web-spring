package rha.jwt.model.security;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CambiarPassword {


	@NotNull(message = "Introduzca una contraseña")
    @Size(min = 4, max = 100, message = "el tamaño tiene que estar entre 4 y 100")
	protected String password;
	
	@NotNull(message = "Introduzca una contraseña nueva")
    @Size(min = 4, max = 100, message = "el tamaño tiene que estar entre 4 y 100")
	protected String password_nueva;
	
	protected String email;
	
	public CambiarPassword() {
		super();
	}

	public CambiarPassword(String password, String password_nueva) {
		super();
		this.password = password;
		this.password_nueva = password_nueva;
	}
	
	public CambiarPassword(
			@NotNull(message = "Introduzca una contraseña") @Size(min = 4, max = 100, message = "el tamaño tiene que estar entre 4 y 100") String password,
			@NotNull(message = "Introduzca una contraseña nueva") @Size(min = 4, max = 100, message = "el tamaño tiene que estar entre 4 y 100") String password_nueva,
			String email) {
		super();
		this.password = password;
		this.password_nueva = password_nueva;
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword_nueva() {
		return password_nueva;
	}

	public void setPassword_nueva(String password_nueva) {
		this.password_nueva = password_nueva;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
   
    
}
