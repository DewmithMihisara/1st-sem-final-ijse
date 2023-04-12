package lk.ijse.project_dkf.model;

import lk.ijse.project_dkf.dto.Buyer;
import lk.ijse.project_dkf.dto.Order;
import lk.ijse.project_dkf.util.CrudUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.*;


public class NewOrderModel {
    public static List<String> loadIds() throws SQLException {
        String sql="SELECT BuyerID FROM Buyer";
        ResultSet resultSet = CrudUtil.execute(sql);

        List<String>data=new ArrayList<>();

        while (resultSet.next()){
            data.add(resultSet.getString(1));
        }
        return data;
    }

    public static Buyer searchById(String id) throws SQLException {
        String sql="SELECT * FROM Buyer WHERE BuyerID=?";
        ResultSet resultSet = CrudUtil.execute(sql,id);

        if (resultSet.next()){
            return new Buyer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
        }
        return null;
    }

    public static boolean addOrder(Order order) throws SQLException, ParseException {

        String sql ="INSERT INTO Orders (OrderID,BuyerID,Dedline,TtlQty,DailyOutQty,PayTerm,OrderDate ) VALUES(?, ?, ?, ?, ?, ?, ?)";
        return CrudUtil.execute(
                sql,
                order.getOrderId(),
                order.getCompId(),
                order.getDline(),
                order.getTtlQty(),
                order.getDailyOut(),
                order.getPayment(),
                order.getOrderDate()
        );
    }

    public static void deleteAll(String text) throws SQLException {
        String sql="DELETE FROM Orders WHERE OrderID=?";
        CrudUtil.execute(sql,text);
    }

    public static boolean search(String text) throws SQLException {
        String sql="SELECT * FROM Orders WHERE OrderID=?";
        ResultSet resultSet = CrudUtil.execute(sql,text);

        return resultSet.next();
    }
}
