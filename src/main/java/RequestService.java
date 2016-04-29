import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.MediaType;

/* Para el ejemplo se usara el siguiente recurso
*  https://www.googleapis.com/books/v1/volumes/?q=isbn:9706434526&fields=totalItems,items(id,volumeInfo(title,publishedDate))
* */

public class RequestService {
    private Client client;
    private static final String API_GOOGLE = "https://www.googleapis.com/books/v1";
    private static final String RESOURCE = "volumes";

    public RequestService() {
        this.client = Client.create();
        //En la documentacion se puede ver como al cliente agregarle un ClientConfig
        //para agregarle filtros en las respuestas (por ejemplo, para loguear).
    }

    public ClientResponse getBookByFilter(String filter, String value){
        WebResource recurso = this.client.resource(API_GOOGLE).path(RESOURCE);
        WebResource recursoConParametros = recurso.queryParam("q",filter + ":" + value);
        WebResource.Builder builder = recursoConParametros.accept(MediaType.APPLICATION_JSON);
        ClientResponse response = builder.get(ClientResponse.class);
        return response;
    }

    public ClientResponse getBookByFilter(String filter, String value, String fields){
        ClientResponse response = this.client.resource(API_GOOGLE).path(RESOURCE)
                .queryParam("q",filter + ":" + value).queryParam("fields",fields)
                .accept(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);
        return response;
    }

    public ClientResponse getBookAndSendHeader(String filter, String value, String header){
        ClientResponse response = this.client.resource(API_GOOGLE).path(RESOURCE)
                .queryParam("q",filter + ":" + value).header("Test","TestValue")
                .accept(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);
        return response;
    }
}
