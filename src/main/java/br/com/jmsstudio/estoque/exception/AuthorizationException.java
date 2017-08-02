package br.com.jmsstudio.estoque.exception;

import javax.xml.ws.WebFault;

@WebFault(name = "AuthorizationFault")
public class AuthorizationException extends Exception {
    public AuthorizationException(String message) {
        super(message);
    }

    public String getFaultInfo() {
        return "O token informado é inválido. Autorização não realizada.";
    }
}
