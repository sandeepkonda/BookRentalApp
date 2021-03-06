package com.library.rental.service;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.library.rental.manager.BookRentalManager;
import com.library.rental.manager.PopularBooksManager;
import com.library.rental.object.BookRental;


@Path("/bookrental")
public class BookRentalService {

	@Path("/popularBooks")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response popularBooks() {
		PopularBooksManager popularBooksManager = new PopularBooksManager();

		List<String> response = popularBooksManager.getPopularBook();

		return Response.ok(response, MediaType.APPLICATION_JSON).build();
	}

	@Path("/users/{userId}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getUserInfo(@PathParam("userId") String userId) {
		BookRentalManager bookRentalManager = new BookRentalManager();
		
		List<String> response = bookRentalManager.getBookRentalInfoOfUser(userId);
		
		return Response.ok(response, MediaType.APPLICATION_JSON).build();
	}

	//TODO
	/*@Path("/users")
	@GET
	//@Produces(MediaType.APPLICATION_JSON)
	public Response getUserInfo() {
		return getUserInfo(null); 
	}*/

	@Path("/rent")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response rentBook(String input) throws Exception{
		BookRental bookRental = null;
		try {
			bookRental = new ObjectMapper().readValue(input, BookRental.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		BookRentalManager bookRentalManager = new BookRentalManager();

		try {
			bookRentalManager.rentBook(bookRental);
		}
		catch (Exception e) {
			return Response.serverError().entity(e.getMessage()).build();
		}

		return Response.status(201).build(); 
	}

	@Path("/return")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response returnBook(String input) throws Exception{
		BookRental bookRental = null;
		try {
			bookRental = new ObjectMapper().readValue(input, BookRental.class);

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		BookRentalManager bookRentalManager = new BookRentalManager();

		try {
			bookRentalManager.returnBook(bookRental);
		}
		catch (Exception e) {
			return Response.serverError().entity(e.getMessage()).build();
		}

		return Response.status(201).build(); 
	}

} 