package post;

import com.sun.jersey.api.client.ClientResponse;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EjemploRequestPostTest {

    EjemploRequestPost requester;

    @Before
    public void setUp() throws Exception {
        this.requester = new EjemploRequestPost();
    }

    @Test
    public void crearUnComentario() throws Exception {
        //Se solicita todos los datos de un libro por su isbn.
        ClientResponse response = this.requester.createComment("Test comment");
        assertEquals(response.getStatus(), 201);
        String json = response.getEntity(String.class);
        assertTrue(json.contains("id"));
    }

}