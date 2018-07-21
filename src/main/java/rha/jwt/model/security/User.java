package rha.jwt.model.security;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import rha.model.UserCentro;

@Entity
@Table(name = "USER")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("us")
public class User {

    @Id
    @Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "USERNAME", length = 100, unique = true)
    @NotNull(message = "Introduzca un username")
    @Size(min = 4, max = 100, message = "el tamaño tiene que estar entre 4 y 100")
	protected String username;

    @Column(name = "PASSWORD", length = 100)
    @NotNull(message = "Introduzca una contraseña")
    @Size(min = 4, max = 100, message = "el tamaño tiene que estar entre 4 y 100")
	protected String password;

    @Column(name = "FIRSTNAME", length = 50)
    @NotNull(message = "Introduzca un nombre")
    @Size(min = 3, max = 50, message = "el tamaño tiene que estar entre 3 y 50")
	protected String firstname;

    @Column(name = "LASTNAME", length = 100)
    @NotNull(message = "Introduzca los apellidos")
    @Size(min = 3, max = 100, message = "el tamaño tiene que estar entre 3 y 100")
	protected String lastname;

    @Column(name = "EMAIL", length = 100, unique = true)
    @NotNull(message = "Introduzca un email")
    @Size(min = 4, max = 100, message = "el tamaño tiene que estar entre 4 y 100")
	protected String email;

    @Column(name = "ENABLED")
    //@NotNull(message = "Diga si está habilitado o no")
	protected Boolean enabled;

    @Column(name = "LASTPASSWORDRESETDATE")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
	protected Date lastPasswordResetDate;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "USER_AUTHORITY",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "ID")})
	protected List<Authority> authorities;
    
    protected ArrayList<Boolean> permisos = new ArrayList <>(3);
    
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Set<UserCentro> userCentros = new HashSet<UserCentro>();
    
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
	
	public User(
			@NotNull(message = "Introduzca un username") @Size(min = 4, max = 100, message = "el tamaño tiene que estar entre 4 y 100") String username,
			@NotNull(message = "Introduzca una contraseña") @Size(min = 4, max = 100, message = "el tamaño tiene que estar entre 4 y 100") String password,
			@NotNull(message = "Introduzca un nombre") @Size(min = 3, max = 50, message = "el tamaño tiene que estar entre 3 y 50") String firstname,
			@NotNull(message = "Introduzca los apellidos") @Size(min = 3, max = 100, message = "el tamaño tiene que estar entre 3 y 100") String lastname,
			@NotNull(message = "Introduzca un email") @Size(min = 4, max = 100, message = "el tamaño tiene que estar entre 4 y 100") String email,
			@NotNull(message = "Diga si está habilitado o no") Boolean enabled, Date lastPasswordResetDate,
			List<Authority> authorities, ArrayList<Boolean> permisos) {
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

	public long getId() {
        return id;
    }

    public void setId(long id) {
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
    
    @JsonIgnore
    public String getFullName() {
    	return firstname + " " + lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean isEnabled() {
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
    
    public void addAuthority(Authority authority) {
    	this.authorities.add(authority);
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

	public Set<UserCentro> getUserCentros() {
		return userCentros;
	}

	public void setUserCentros(Set<UserCentro> userCentros) {
		this.userCentros = userCentros;
	}
	
	public void addUserCentro(UserCentro userCentro) {
        this.userCentros.add(userCentro);
    }  
    
    
}
