package com.benpiotti.cs252lab6.Dao;

import com.benpiotti.cs252lab6.Entity.Ticket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import static com.benpiotti.cs252lab6.util.Database.closeAll;
import com.benpiotti.cs252lab6.util.Database;

import javax.ws.rs.core.Response;

public class TicketDao {

    private static final Logger log = LoggerFactory.getLogger(TicketDao.class);
    public static final String DATABASE = "cs252lab6_prd";

    //get array list of all tickets
    public ArrayList<Ticket> getAllTickets() {
        String query = "select * from ticket where sold = false order by date DESC";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Ticket> tickets = new ArrayList<>();
        try {
            con = Database.getConnection(DATABASE);
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();

            while (rs.next()) {
                tickets.add(new Ticket(rs.getInt("ticketid"), rs.getString("sellerfirst"),
                        rs.getString("sellerlast"), rs.getDouble("price"), rs.getString("date"),
                        rs.getBoolean("sold"), rs.getString("gametime"),
                        rs.getString("buyerfirst"), rs.getString("buyerlast"),
                        rs.getString("event"), rs.getString("description"), rs.getString("email"), rs.getString("phone")));
            }

        } catch (Exception e) {
            log.error("Error in getAllTickets {}", e.getMessage());
        }
        finally {
            closeAll(rs, stmt, con);
        }
        log.info("exit /getAllTickets");
        return tickets;
    }

    //get array list of all tickets sorted by an event
    public ArrayList<Ticket> getAllTicketsSort(String event) {
        String query = "select * from ticket where sold = false and event = ? order by date DESC";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Ticket> tickets = new ArrayList<>();
        try {
            con = Database.getConnection(DATABASE);
            stmt = con.prepareStatement(query);
            stmt.setString(1, event);
            rs = stmt.executeQuery();

            while (rs.next()) {
                tickets.add(new Ticket(rs.getInt("ticketid"), rs.getString("sellerfirst"),
                        rs.getString("sellerlast"), rs.getDouble("price"), rs.getString("date"),
                        rs.getBoolean("sold"), rs.getString("gametime"),
                        rs.getString("buyerfirst"), rs.getString("buyerlast"),
                        rs.getString("event"), rs.getString("description"), rs.getString("email"), rs.getString("phone")));
            }

        } catch (Exception e) {
            log.error("Error in getAllTicketsSort {}", e.getMessage());
        }
        finally {
            closeAll(rs, stmt, con);
        }
        log.info("exit /getAllTicketsSort");
        return tickets;
    }

    //Add ticket resource to data base
    public Response addTicket(Ticket ticket) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String query = "SELECT COUNT(*) FROM ticket;";
            con = Database.getConnection(DATABASE);
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();
            rs.next();
            ticket.setTicketid(rs.getInt("count") + 1);

            query = "INSERT INTO ticket\n" +
                    "\t(ticketid, sellerfirst, sellerlast, price, \"date\", sold, gametime, buyerfirst, buyerlast, event, description, email, phone)\n" +
                    "VALUES \n" +
                    "\t(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            stmt = con.prepareStatement(query);

            stmt.setInt(1, ticket.getTicketid());
            stmt.setString(2, ticket.getSellerfirst());
            stmt.setString(3, ticket.getSellerlast());
            stmt.setDouble(4, ticket.getPrice());
            stmt.setString(5, ticket.getDate());
            stmt.setBoolean(6, ticket.isSold());
            stmt.setString(7, ticket.getGametime());
            stmt.setString(8, ticket.getBuyerFirst());
            stmt.setString(9, ticket.getBuyerLast());
            stmt.setString(10, ticket.getEvent());
            stmt.setString(11, ticket.getDescription());
            stmt.setString(12, ticket.getEmail());
            stmt.setString(13, ticket.getPhone());

            int result = stmt.executeUpdate();
            if (result == 1) {
                return Response
                        .status(Response.Status.OK)
                        .build();
            }
        }
        catch(Exception e) {
            log.error("Error in addTicket {}", e.getMessage());
        }
        finally {
            closeAll(con, stmt, rs);
        }

        log.info("exit /addTicket");
        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .build();
    }

    //get array list of all tickets listed by a user
    public ArrayList<Ticket> getAllTicketsEmail(String email) {
        String query = "select * from ticket where sold = false and email = ? order by date DESC";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Ticket> tickets = new ArrayList<>();
        try {
            con = Database.getConnection(DATABASE);
            stmt = con.prepareStatement(query);
            stmt.setString(1, email);
            rs = stmt.executeQuery();

            while (rs.next()) {
                tickets.add(new Ticket(rs.getInt("ticketid"), rs.getString("sellerfirst"),
                        rs.getString("sellerlast"), rs.getDouble("price"), rs.getString("date"),
                        rs.getBoolean("sold"), rs.getString("gametime"),
                        rs.getString("buyerfirst"), rs.getString("buyerlast"),
                        rs.getString("event"), rs.getString("description"), rs.getString("email"), rs.getString("phone")));
            }

        } catch (Exception e) {
            log.error("Error in getAllTicketsEmail {}", e.getMessage());
        }
        finally {
            closeAll(rs, stmt, con);
        }
        log.info("exit /getAllTicketsEmail");
        return tickets;
    }

}
