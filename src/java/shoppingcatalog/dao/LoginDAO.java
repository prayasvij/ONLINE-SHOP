/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingcatalog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import shoppingcatalog.dbutil.DBConnection;
import shoppingcatalog.dto.UserDTO;

/**
 *
 * @author Vijayvargiya
 */
public class LoginDAO {

    private static PreparedStatement ps;
//   private static  String query;

    static {
        try {
//            query = "Select username from member where username=?&&membertype=?&&password=?";
            ps = DBConnection.getConnection().prepareStatement("select * from members where username=? AND password=? AND membertype=?");
        } catch (SQLException e) {
            System.out.println("exception in prepared statment LOFIN DAo" + e);
        }
    }

    public static boolean validateUser(UserDTO user) throws SQLException {
        System.out.println( "in dao "+user.getUsername()+ user.getUsertype()+ user.getPassword());
        ps.setString(1, user.getUsername().trim());
        ps.setString(3, user.getUsertype().trim());
        ps.setString(2, user.getPassword().trim());
        ResultSet rs = ps.executeQuery();
        //System.out.println(rs.next());
        return rs.next();
    }
}
