package lk.ijse.project_dkf.model;

import lk.ijse.project_dkf.dto.User;
import lk.ijse.project_dkf.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NewACModel {
    public static  boolean isDuplicate(User user) throws SQLException {
        String sql="INSERT INTO User(UserName,UserEmail,UserContact, Password ) VALUES(?, ?, ?, ?)";
        return CrudUtil.execute(
                sql,
                user.getUserName(),
                user.getUserEmail(),
                user.getContactUser(),
                user.getPassword());
    }
}
