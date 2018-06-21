package rha.jwt.model.security;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "USER")
public class User {

    @Id
    @Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "USERNAME", length = 100, unique = true)
    @NotNull(message = "Introduzca un username")
    @Size(min = 4, max = 100, message = "el tamaño tiene que estar entre 4 y 100")
    private String username;

    @Column(name = "PASSWORD", length = 100)
    @NotNull(message = "Introduzca una contraseña")
    @Size(min = 4, max = 100, message = "el tamaño tiene que estar entre 4 y 100")
    private String password;

    @Column(name = "FIRSTNAME", length = 50)
    @NotNull(message = "Introduzca un nombre")
    @Size(min = 3, max = 50, message = "el tamaño tiene que estar entre 3 y 50")
    private String firstname;

    @Column(name = "LASTNAME", length = 100)
    @NotNull(message = "Introduzca los apellidos")
    @Size(min = 3, max = 100, message = "el tamaño tiene que estar entre 3 y 100")
    private String lastname;

    @Column(name = "EMAIL", length = 100, unique = true)
    @NotNull(message = "Introduzca un email")
    @Size(min = 4, max = 100, message = "el tamaño tiene que estar entre 4 y 100")
    private String email;

    @Column(name = "ENABLED")
    @NotNull(message = "Diga si está habilitado o no")
    private Boolean enabled;

    @Column(name = "LASTPASSWORDRESETDATE")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull(message = "Introduzca una fecha")
    private Date lastPasswordResetDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "USER_AUTHORITY",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "ID")})
    private List<Authority> authorities;
    
    private ArrayList<Boolean> permisos = new ArrayList <>(3);
    
    public User() {
		super();
	}

	public User(@NotNull @Size(min = 4, max = 50) String username,
			@NotNull @Size(min = 4, max = 100) String password, @NotNull @Size(min = 4, max = 50) String firstname,
			@NotNull @Size(min = 4, max = 50) String lastname, @NotNull @Size(min = 4, max = 50) String email,
			@NotNull Boolean enabled, @NotNull Date lastPasswordResetDate, List<Authority> authorities) {
		super();
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.enabled = enabled;
		this.lastPasswordResetDate = lastPasswordResetDate;
		this.authorities = authorities;
	}
	
	

	public User(@NotNull @Size(min = 4, max = 50) String username, @NotNull @Size(min = 4, max = 100) String password,
			@NotNull @Size(min = 4, max = 50) String firstname, @NotNull @Size(min = 4, max = 50) String lastname,
			@NotNull @Size(min = 4, max = 50) String email, @NotNull Boolean enabled,
			@NotNull Date lastPasswordResetDate, List<Authority> authorities, ArrayList<Boolean> permisos) {
		super();
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.enabled = enabled;
		this.lastPasswordResetDate = lastPasswordResetDate;
		this.authorities = authorities;
		this.permisos = permisos;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    
    public String getFullName() {
    	return firstname + " " + lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Date lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", firstname=" + firstname
				+ ", lastname=" + lastname + ", email=" + email + ", enabled=" + enabled + ", lastPasswordResetDate="
				+ lastPasswordResetDate + ", authorities=" + authorities + "]";
	}

	public ArrayList<Boolean> getPermisos() {
		return permisos;
	}

	public void setPermisos(ArrayList<Boolean> permisos) {
		this.permisos = permisos;
	}
    
    
}
