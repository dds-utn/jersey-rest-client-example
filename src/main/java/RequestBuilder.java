import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class RequestBuilder {

    private static final String API_GOOGLE = "https://www.googleapis.com/books/v1";
	private List<Param> params = new ArrayList<>();
	private boolean json = false;
	private WebResource webResource;

	public RequestBuilder() {
		super();
	}

	public RequestBuilder withQueryParam(String param, String value) {
		this.params.add(new Param(param, value));
		return this;
	}

	public RequestBuilder rest() {
		json = true;
		return this;
	}

	public String build() {
		if (json) {
			webResource.accept(MediaType.APPLICATION_JSON);
		}
		params.stream().forEach((param) -> 
			webResource = webResource.queryParam(param.getName(), param.getValue()
		));
		return webResource.get(ClientResponse.class).getEntity(String.class);
	}

	public RequestBuilder fromGoogleBooks(String resource) {
		webResource = Client.create().resource(API_GOOGLE).path(resource);
		return this;
	}


}
