package com.library.rental.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * @author Sandeep Konda
 * 
 * Client for Inventory. This class will handle all the REST calls to Inventory Management Service
 *
 */
public class InventoryClient {
	
	String host = "http://10.132.126.48:8080/IVM/webapi/inventoryManagement/";
	
	public String checkInventory(String isbn) {
		Client client = Client.create();

		WebResource webResource = client
		   .resource(host+"search/"+isbn);

		/*
		 * Calling PUT on inventoryManagement/search API, to get inventory response
		 * Response will contain, isbn, quantity, sku etc
		 */
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
		
		/*
		 * Calling PUT on inventoryManagement/update API, with status 'rent' in order to decrement inventory
		 */
		ClientResponse response = webResource.type("application/json")
                   .put(ClientResponse.class, "{\"status\":\"rent\"}");

		if (response.getStatus() != 200) {
		   throw new RuntimeException("Failed : HTTP error code : "
			+ response.getStatus());
		}
		
	}
	
	public void incrementInventory(String isbn) {
		Client client = Client.create();

		WebResource webResource = client
		   .resource(host+"update/"+isbn);
		
		/*
		 * Calling PUT on inventoryManagement/update API, with status 'return' in order to increment inventory
		 */
		ClientResponse response = webResource.type("application/json")
                   .put(ClientResponse.class, "{\"status\":\"return\"}");

		if (response.getStatus() != 200) {
		   throw new RuntimeException("Failed : HTTP error code : "
			+ response.getStatus());
		}
		
	}

}
