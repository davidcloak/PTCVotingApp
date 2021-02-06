package com.votesystem.voteserver.User;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private String connectionString = "jdbc:sqlserver://voteshield.database.windows.net;databaseName=voteShield;user=Nate;password=Ghost123";

    // Made By David Please to test UserPassing for android please leave alone for now
    @RequestMapping(value = "/getUser", method = RequestMethod.POST)
    public List<User> GetUser(@RequestParam(defaultValue = "*") String email) {
        List<User> response = new ArrayList<User>();

        User aUser = new User();
        String SQL = "";

        if (email.equals("*")) {
            // fail
            aUser.setId(null);
            aUser.setUserName("No email sent");
            aUser.setStatus("404");
            response.add(aUser);
        } else {
            SQL = String.format("select * from AspNetUsers where Email = '%s'", email);

            try {
                Connection con = DriverManager.getConnection(connectionString);
                Statement stmt = con.createStatement();
                ResultSet result = stmt.executeQuery(SQL);
                if (result.next()) {
                    User v = new User();
                    v.setId(result.getString("Id"));
                    v.setUserName(result.getString("UserName"));
                    v.setNormalizedUserName(result.getString("NormalizedUserName"));
                    v.setEmail(result.getString("Email"));
                    v.setNormalizedEmail(result.getString("NormalizedEmail"));
                    v.setEmailConfirmed(result.getString("EmailConfirmed"));
                    v.setPasswordHash(result.getString("PasswordHash"));
                    v.setSecurityStamp(result.getString("SecurityStamp"));
                    v.setConcurrencyStamp(result.getString("ConcurrencyStamp"));
                    v.setPhoneNumber(result.getString("PhoneNumber"));
                    v.setPhoneNumberConfirmed(result.getString("PhoneNumberConfirmed"));
                    v.setTwoFactorEnabled(result.getString("TwoFactorEnabled"));
                    v.setLockoutEnd(result.getString("LockoutEnd"));
                    v.setLockoutEnabled(result.getString("LockoutEnabled"));
                    v.setAccessFailedCount(result.getString("AccessFailedCount"));
                    v.setStatus("200");
                    response.add(v);
                }
                else{
                    aUser.setId(null);
                    aUser.setUserName("No User To Be found");
                    aUser.setStatus("404");
                    response.add(aUser);
                }
                con.close();
            } catch (SQLException e) {
                aUser.setId(e.getMessage());
                aUser.setUserName(SQL);
                aUser.setStatus("500");
                response.add(aUser);
            }
        }
        return response;
    }
}
