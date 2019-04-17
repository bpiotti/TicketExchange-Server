package com.benpiotti.cs252lab6.Dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
            }
            else {
                //Check if password is right
                log.debug("EMAIL FOUND");
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
