package lk.ijse.project_dkf.model;

import lk.ijse.project_dkf.dto.Cut;
import lk.ijse.project_dkf.dto.OrderRatio;
import lk.ijse.project_dkf.dto.Pack;
import lk.ijse.project_dkf.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoadSizesModel {

    public static OrderRatio ratio(String oId, String clr) throws SQLException {
        String sql="SELECT * FROM OrderRatio WHERE OrderID=? AND Colour=?";
        ResultSet resultSet = CrudUtil.execute(sql,oId,clr);
        if (resultSet.next()){
            return new OrderRatio(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getInt(5),
                    resultSet.getInt(6),
                    resultSet.getInt(7),
                    resultSet.getInt(8)
            );
        }
        return null;
    }
}
