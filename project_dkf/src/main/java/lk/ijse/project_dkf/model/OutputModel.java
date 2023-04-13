package lk.ijse.project_dkf.model;

import lk.ijse.project_dkf.dto.OrderRatio;
import lk.ijse.project_dkf.dto.Output;
import lk.ijse.project_dkf.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OutputModel {
    public static boolean add(Output output) throws SQLException {
        String sql ="INSERT INTO Output (OutputID, Day, Colour, size, DailyOut ) VALUES(?, ?, ?, ?, ?)";
        return CrudUtil.execute(
                sql,
                output.getOId(),
                output.getDate(),
                output.getClr(),
                output.getSize(),
                output.getOut()
        );
    }

    public static List<Output> getAll(String selectedItem) throws SQLException {
        String sql = "SELECT * FROM Output WHERE OutputID =?";
        ResultSet resultSet = CrudUtil.execute(sql,selectedItem);

        ArrayList<Output> outputs=new ArrayList<>();
        while (resultSet.next()){
            String id= resultSet.getString(1);
            Date Date=resultSet.getDate(2);
            String clr=resultSet.getString(3);
            String size=resultSet.getString(4);
            int out=resultSet.getInt(5);

            Output output=new Output(id,Date,clr,size,out);
            outputs.add(output);
        }
        return outputs;
    }

    public static boolean delete(Output output) throws SQLException {
        String sql="DELETE FROM Output WHERE OutputID=? AND Day=? AND Colour=? AND Size=?";
        boolean result = CrudUtil.execute(
                sql,
                output.getOId(),
                output.getDate(),
                output.getClr(),
                output.getSize()
        );
        return result;
    }
}
