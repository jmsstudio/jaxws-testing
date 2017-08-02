package br.com.jmsstudio.estoque.ws;

import javax.xml.ws.Endpoint;

public class PublishEstoqueWS {

    public static void main(String[] args) {
        EstoqueWS estoqueWS = new EstoqueWS();
        final String URL = "http://localhost:8080/estoquews";

        System.out.println("Rodando estoquews: " + URL);

        Endpoint.publish(URL, estoqueWS);
    }

}
