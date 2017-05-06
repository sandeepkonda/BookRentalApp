package com.library.rental.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class BillingClient {
	
	String host = "http://10.132.126.48:8080/IVM/webapi/inventoryManagement/";

	public void generateBill(String isbn, int numberOfDaysRented) {
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
