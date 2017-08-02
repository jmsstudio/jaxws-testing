package br.com.jmsstudio.estoque.ws;

import br.com.jmsstudio.estoque.exception.AuthorizationException;
import br.com.jmsstudio.estoque.model.item.*;
import br.com.jmsstudio.estoque.model.usuario.TokenDao;
import br.com.jmsstudio.estoque.model.usuario.TokenUsuario;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import java.util.List;

@WebService
public class EstoqueWS {

    private ItemDao itemDao = new ItemDao();
    private TokenDao tokenDao = new TokenDao();

    @WebMethod(operationName = "listItens")
    @WebResult(name = "item")
    @ResponseWrapper(localName = "itens")
    @RequestWrapper(localName = "listaItens")
    public List<Item> list(@WebParam(name = "filtros") Filtros filtros) {
        System.out.println("Listando itens");

        List<Filtro> lista = filtros.getLista();

        return itemDao.todosItens(lista);
    }

    @WebMethod(operationName = "createItem")
    public Item creates(@WebParam(name = "token", header = true) TokenUsuario token, @WebParam(name = "item") Item item) throws AuthorizationException {
        System.out.println("Cadastrando item");

        if (this.tokenDao.ehValido(token)) {
            new ItemValidador(item).validate();

            this.itemDao.cadastrar(item);
        } else {
            throw new AuthorizationException("Token informado é inválido");
        }

        return item;
    }
}
