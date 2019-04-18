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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getAllTickets")
    public ArrayList<Ticket> getAllTickets() {
        log.info("Enter: /getAllTickets");
        TicketDao ticketDao =  new TicketDao();
        return ticketDao.getAllTickets();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getAllTicketsSort")
    public ArrayList<Ticket> getAllTicketsSort(@QueryParam("sort") String event) {
        log.info("Enter: /getAllTicketsSort");
        TicketDao ticketDao =  new TicketDao();
        return ticketDao.getAllTicketsSort(event);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("addTicket")
    public Response login(Ticket ticket) {
        log.info("Enter: /addTicket");
        log.debug("Payload: {}", ticket.toString());
        TicketDao ticketDao = new TicketDao();
        return ticketDao.addTicket(ticket);
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
