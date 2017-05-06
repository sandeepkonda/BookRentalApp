package com.library.rental.service;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.library.rental.dao.BookRentalDAO;
import com.library.rental.dao.PopularBooksDAO;
import com.library.rental.manager.BookRentalManager;
import com.library.rental.object.BookRental;
import com.library.rental.object.PopularBooks;


@Path("/bookrental")
public class BookRentalService {

	@Path("/popularBooks")
	@GET
    //@Produces(MediaType.APPLICATION_JSON)
    public String popularBooks() {
        return "TODO: Get popular Books"; 
	}
	
	@Path("/users/{userId}")
	@GET
    //@Produces(MediaType.APPLICATION_JSON)
    public String getUserInfo(@PathParam("userId") String userId) {
        return "TODO: Get User info"+userId; 
	}
	
	@Path("/users")
	@GET
    //@Produces(MediaType.APPLICATION_JSON)
    public String getUserInfo() {
        return getUserInfo(null); 
	}
	
	@Path("/rent")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response rentBook(String input){
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
		
		bookRentalManager.rentBook(bookRental);
        
        return Response.status(201).entity("response: "+bookRental.getIsbn()).build(); 
    }
	
	@Path("/return")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response returnBook(String input){
		BookRental bookRental = null;
		try {
			bookRental = new ObjectMapper().readValue(input, BookRental.class);
			
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BookRentalDAO dao = new BookRentalDAO();
        //dao.addBookRental(bookRental);
        
        return Response.status(201).entity("response: "+bookRental.getIsbn()).build(); 
    }

} 