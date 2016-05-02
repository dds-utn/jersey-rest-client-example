import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by niko118 on 5/1/16.
 */
public class RequestServiceTest {

    RequestService requester;

    @Before
    public void setUp() throws Exception {
        this.requester = new RequestService();
    }

    @Test
    public void obtenerConUnFiltro() throws Exception {
        ClientResponse response = this.requester.getBookByFilter("isbn","9706434526");
        assertTrue(response.getEntity(String.class).contains("La Cabana Del Tio Tom"));
    }

    @Test
    public void obtenerConDosFiltros() throws Exception {
        ClientResponse response = this.requester.getBookByFilter("isbn","9706434526","items");
        assertFalse(response.getEntity(String.class).contains("totalItems"));
    }

    @Test
    public void obtenerConDosFiltrosConError() throws Exception {
        ClientResponse response = this.requester.getBookByFilter("isbn","9706434526","valorErroneo");
        assertNotEquals(response.getStatus(),200);
    }

    @Test
    public void testGetBookAndSendHeader() throws Exception {
        ClientResponse response = this.requester.getBookAndSendHeader("isbn","9706434526","TestValue");
        assertEquals(response.getStatus(),200);
    }
}