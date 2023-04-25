package lk.ijse.project_dkf.model;

import lk.ijse.project_dkf.dto.LogHistory;
import lk.ijse.project_dkf.util.CrudUtil;

import java.sql.SQLException;

public class LogHistoryModel {
    public static void save(LogHistory logHistory) throws SQLException {
        String sql ="INSERT INTO LogHistory (UserName, LogIn, logOut) VALUES(?, ?, ?)";
        CrudUtil.execute(
                sql,
                logHistory.getUsrName(),
                logHistory.getLogIn(),
                logHistory.getLogOut()
        );
    }
}
