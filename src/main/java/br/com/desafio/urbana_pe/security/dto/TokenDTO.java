package br.com.desafio.urbana_pe.security.dto;

public class TokenDTO {
    private String token;
    private String type;
    private String issuer;

    public TokenDTO(String token, String type, String issuer) {
        this.token = token;
        this.type = type;
        this.issuer = issuer;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String toke) {
        this.token = toke;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }
}
