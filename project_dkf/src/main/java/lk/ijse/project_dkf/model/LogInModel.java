package lk.ijse.project_dkf.model;

import lk.ijse.project_dkf.dto.User;
import lk.ijse.project_dkf.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LogInModel {
    public static User isCorrect(String usrName) throws SQLException {

        String sql="SELECT * FROM User WHERE UserName = ?";
        ResultSet resultSet = CrudUtil.execute(sql,usrName);

        if (resultSet.next()){
            return new User(
                    resultSet.getString(1),
                    resultSet.getString(2)
            );

        }
        return null;
    }

}