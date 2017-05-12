/* Para el ejemplo se usara el siguiente recurso
*  https://www.googleapis.com/books/v1/volumes/?q=isbn:9706434526&fields=totalItems,items(id,volumeInfo(title,publishedDate))
* */

public class RequestService {
    private static final String RESOURCE = "volumes";

    //Inicializacion del cliente.
    public RequestService() {
    }

    //Prueba de concepto de un parametro y los mensajes por separado para identificar los tipos de datos.
    public String getBookByFilter(String filter, String value){
    	return new RequestBuilder()
    		.fromGoogleBooks(RESOURCE)
    		.withQueryParam("q", filter + ":" + value)
    		.rest()
    		.build();
    }

}
