package com.benpiotti.cs252lab6.rest;

import com.benpiotti.cs252lab6.Dao.LoginDao;
import com.benpiotti.cs252lab6.Entity.Login;
import com.benpiotti.cs252lab6.util.Database;
import com.benpiotti.cs252lab6.util.Database.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public class Person {
        String firstName;
        String lastName;

        public Person () {

        }

        public Person(String first, String last) {
            this.firstName = first;
            this.lastName = last;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("test")
    public Ticket testService() {
        log.info("Enter: /test");
        Person ben = new Person("ben", "Piotti");

        String query = "select * from ticket where ticketid = 1";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Ticket ticket = null;
        try {
            con = Database.getConnection(DATABASE);
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();

            rs.next();
            ticket = new Ticket(rs.getInt("ticketid"), rs.getString("sellerfirst"),
                    rs.getString("sellerlast"), rs.getDouble("price"), rs.getTimestamp("date"),
                    rs.getBoolean("sold"), rs.getTime("gametime"),
                    rs.getString("buyerfirst"), rs.getString("buyerlast"),
                    rs.getString("event"));

        } catch (Exception e) {
//            throw new RestServiceException(ErrorMessages.REST_SERVICE_ERROR.getErrorMessage(), e);
        }
        finally {
            Database.closeAll(rs, stmt, con);
        }

        log.info("Exit: /test");
        return ticket;
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
