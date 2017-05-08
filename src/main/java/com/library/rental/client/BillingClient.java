package com.library.rental.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * @author Sandeep Konda
 * 
 * Client for Billing. This class will handle all the REST calls to Billing Management Service
 *
 */
public class BillingClient {
	
	//TODO dynamically get service IP address
	String host = "http://10.132.126.48:8080/IVM/webapi/billingManagement/";

	public void generateBill(String isbn, String userId, int numberOfDaysRented) {
		Client client = Client.create();

		WebResource webResource = client
		   .resource(host+"update");
		
		
		ClientResponse response = webResource.type("application/json")
                   .put(ClientResponse.class, "{\"status\":\"rent\"}");

		if (response.getStatus() != 200) {
		   throw new RuntimeException("Failed : HTTP error code : "
			+ response.getStatus());
		}

	}
	
}
