
package shoppingcatalog.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import shoppingcatalog.dbutil.DBConnection;
import shoppingcatalog.dto.UserDTO;
public class RegistrationDAO {

    private static PreparedStatement ps1, ps2;
    static {
        try {

            ps1 = DBConnection.getConnection().prepareStatement("select * from members where username=? AND membertype='CUSTOMER'");
            ps2 = DBConnection.getConnection().prepareStatement("insert into members values(?,?,?)");
        } catch (SQLException e) {
            System.out.println("exception in prepared statment registraion Dao" + e);
        }
    }

    public static boolean searchUser(String Username) throws SQLException {
        ps1.setString(1, Username);
        ResultSet rs = ps1.executeQuery();
        return rs.next();
    }

    public static boolean registerUser(UserDTO user) throws SQLException {
        boolean done = false;
        ps2.setString(1, user.getUsername());
        ps2.setString(2, user.getPassword());
        ps2.setString(3, user.getUsertype());
        int ans = ps2.executeUpdate();
       if (ans != 0) {
            done = true;
        }
        return done;
    }
}
