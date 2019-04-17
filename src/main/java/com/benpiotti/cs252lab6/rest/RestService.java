package com.benpiotti.cs252lab6.rest;

import com.benpiotti.cs252lab6.Dao.LoginDao;
import com.benpiotti.cs252lab6.Dao.TicketDao;
import com.benpiotti.cs252lab6.Entity.Login;
import java.util.ArrayList;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.benpiotti.cs252lab6.Entity.Ticket;

@Path("api")
public class RestService {

    private static final Logger log = LoggerFactory.getLogger(RestService.class);
    public static final String DATABASE = "cs252lab6";

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getAllTickets")
    public ArrayList<Ticket> getAllTickets() {
        log.info("Enter: /getAllTickets");
        TicketDao ticketDao =  new TicketDao();
        return ticketDao.getAllTickets();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("login")
    public Response login(Login login) {
        log.info("Enter: /login");
        LoginDao loginDao = new LoginDao();
        return loginDao.login(login);
    }
}
