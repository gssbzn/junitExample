package com.example.resource;

import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.example.model.Transaction;

@Path("sales")
public class SalesResource {
	/** Logger */
    private static final Logger logger = Logger.getLogger(SalesResource.class.getCanonicalName());
    
    @Context
    protected UriInfo uriInfo;

    public SalesResource(){
    	
    }
    
    @GET    
    @Path("/waiting/")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Transaction> getWaitingSales(){
    	logger.info("VENTA-ESPERA");
        return null;
    }
    
    @POST    
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response create(Transaction entity) {
    	logger.info("CREATE VENTA-ESPERA");
        return null;
    }
    
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response releaseWaitingSale(@PathParam("id") Long id) {
    	logger.info("UPDATE VENTA-ESPERA");
    	return null;
    }
}
