package com.library.rental.client;

import com.library.rental.object.BookRental;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class InventoryClient {
	
	String host = "http://10.132.126.48:8080/IVM/webapi/inventoryManagement/";
	
	public String checkInventory(String isbn) {
		Client client = Client.create();

		WebResource webResource = client
		   .resource(host+"search/"+isbn);

		ClientResponse response = webResource.accept("application/json")
                   .get(ClientResponse.class);

		if (response.getStatus() != 200) {
		   throw new RuntimeException("Failed : HTTP error code : "
			+ response.getStatus());
		}

		String output = response.getEntity(String.class);

		return output;
	}

	public void decrementInventory(String isbn) {
		Client client = Client.create();

		WebResource webResource = client
		   .resource(host+"update/"+isbn);
		

		ClientResponse response = webResource.type("application/json")
                   .put(ClientResponse.class, "{\"status\":\"book\"}");

		if (response.getStatus() != 200) {
		   throw new RuntimeException("Failed : HTTP error code : "
			+ response.getStatus());
		}
		
	}

}