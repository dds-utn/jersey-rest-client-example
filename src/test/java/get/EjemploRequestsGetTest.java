package get;

import com.sun.jersey.api.client.ClientResponse;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by niko118 on 5/1/16.
 */
public class EjemploRequestsGetTest {

    EjemploRequestsGet requester;

    @Before
    public void setUp() throws Exception {
        this.requester = new EjemploRequestsGet();
    }

    @Test
    public void obtenerConUnFiltro() throws Exception {
        //Se solicita todos los datos de un libro por su isbn.
        ClientResponse response = this.requester.getBookByFilter("isbn","9706434526");
        assertEquals(response.getStatus(), 200);
        String json = response.getEntity(String.class);
        assertTrue(json.contains("items"));
        assertTrue(json.contains("Tío Tom"));
    }

    @Test
    public void obtenerConDosFiltros() throws Exception {
        //Se filtra y devuelve solo el campo titulo.
        ClientResponse response = this.requester.getBookByFilter("isbn","9706434526","items(volumeInfo(title))");
        assertEquals(response.getStatus(), 200);
        String json = response.getEntity(String.class);
        assertFalse(json.contains("totalItems"));
        assertTrue(json.contains("title"));
        assertFalse(json.contains("id"));
    }

    @Test
    public void obtenerConDosFiltrosConError() throws Exception {
        //Se ingresa un valor erroneo para el filtrado de campos
        ClientResponse response = this.requester.getBookByFilter("isbn","9706434526","valorErroneo");
        assertNotEquals(response.getStatus(), 200);
        String json = response.getEntity(String.class);
        assertTrue(json.contains("invalidParameter"));
    }

    @Test
    public void testGetBookAndSendHeader() throws Exception {
        //Se pide un libro por su isbn, pero con un valor de cabecera.
        ClientResponse response = this.requester.getBookAndSendHeader("isbn","9706434526","If-Match","TestValue");
        assertEquals(response.getStatus(),200);
        String json = response.getEntity(String.class);
        assertTrue(json.contains("totalItems"));
        assertTrue(json.contains("items"));
    }
}