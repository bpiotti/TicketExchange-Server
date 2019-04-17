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

public class TicketDao {

    private static final Logger log = LoggerFactory.getLogger(TicketDao.class);
    public static final String DATABASE = "cs252lab6";

    //get array list of all tickets
    public ArrayList<Ticket> getAllTickets() {
        String query = "select * from ticket";
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
                        rs.getString("sellerlast"), rs.getDouble("price"), rs.getTimestamp("date"),
                        rs.getBoolean("sold"), rs.getTime("gametime"),
                        rs.getString("buyerfirst"), rs.getString("buyerlast"),
                        rs.getString("event")));
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
}
