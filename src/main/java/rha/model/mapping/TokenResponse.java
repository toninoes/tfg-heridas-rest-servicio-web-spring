package rha.model.mapping;



import java.io.Serializable;

public class TokenResponse implements Serializable
{


    private String token;
    private final static long serialVersionUID = 8022889269386527992L;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
