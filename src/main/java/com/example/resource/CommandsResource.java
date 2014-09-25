package com.example.resource;

import java.util.logging.Logger;

import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

@Path("commands")
public class CommandsResource {
	/** Logger */
    private static final Logger logger = Logger.getLogger(CommandsResource.class.getCanonicalName());
    
    @Context
    protected UriInfo uriInfo;

    public CommandsResource(){
    	
    }
    
    
}
