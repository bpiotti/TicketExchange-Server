package com.benpiotti.cs252lab6.Dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.benpiotti.cs252lab6.Entity.Login;
import static com.benpiotti.cs252lab6.util.Database.closeAll;
import com.benpiotti.cs252lab6.util.Database;

public class LoginDao {

    private static final Logger log = LoggerFactory.getLogger(LoginDao.class);
    public static final String DATABASE = "cs252lab6";

    public Response login(Login login) {
        log.info("Enter login");

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String query = "select * from auth where email = ?";
            con = Database.getConnection(DATABASE);
            stmt = con.prepareStatement(query);
            stmt.setString(1, login.getEmail());
            rs = stmt.executeQuery();

            if (rs.next() == false) {
                //Nothing returned from database, create an account
                log.debug("EMAIL NOT FOUND");
                String update  = "INSERT INTO auth (email, password) VALUES (?, ?);";
                stmt = con.prepareStatement(update);
                stmt.setString(1, login.getEmail());
                stmt.setString(2, login.getPassword());
                stmt.executeUpdate();

                return Response
                        .status(Response.Status.OK)
                        .build();
            }
            else {
                //Check if password is right
                log.debug("EMAIL FOUND");
                if (login.getPassword().equals(rs.getString("password"))) {
                    log.debug("password is correct");
                    return Response
                            .status(Response.Status.OK)
                            .build();
                }
                else {
                    log.debug("password is incorrect");
                    return Response
                            .status(Response.Status.CONFLICT)
                            .build();
                }
            }

        }
        catch(Exception e) {
            log.error("Error in login {}", e.getMessage());
        }
        finally {
            closeAll(rs, stmt, con);
        }

        return null;
    }

}
