package post;

/* Para el ejemplo se usara el siguiente recurso
 *  https://my-json-server.typicode.com/typicode/demo/comments
 * */

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.MediaType;

public class PostRequestService {

    private final Client client;
    private static final String API_TYPICODE = "https://my-json-server.typicode.com/typicode/demo";
    private static final String RESOURCE = "comments";

    //Inicializacion del cliente.
    public PostRequestService() {
        this.client = Client.create();
        //En la documentacion se puede ver como al cliente agregarle un ClientConfig
        //para agregarle filtros en las respuestas (por ejemplo, para loguear).
    }

    //Prueba de concepto para crear un nuevo "comentario" en la API.
    public ClientResponse createComment(String comment){
        WebResource recurso = this.client.resource(API_TYPICODE).path(RESOURCE);
        WebResource.Builder builder = recurso.accept(MediaType.APPLICATION_JSON);

        // El endpoint acepta Requests de tipo JSON porque es una API REST.
        // Por lo general, los métodos HTTP POST aceptan datos que son enviados utilizando un "Body".
        // Hay muchas formas de trabajar con estos "Body", esta es una muy muy simple donde se lo crea con un String.
        String jsonBody = "{ \"body\": \"" + comment + "\" }";

        return builder.post(ClientResponse.class, jsonBody);
    }

}
