package lk.ijse.project_dkf.model;

import javafx.collections.ObservableList;
import lk.ijse.project_dkf.dto.Buyer;
import lk.ijse.project_dkf.dto.OrderRatio;
import lk.ijse.project_dkf.dto.tm.OrderRatioTM;
import lk.ijse.project_dkf.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class OrderRatioModel {
    public static List<OrderRatio> getAll(String oId) throws SQLException {
        String sql = "SELECT * FROM OrderRatio WHERE OrderID =?";
        ResultSet resultSet = CrudUtil.execute(sql,oId);

        ArrayList<OrderRatio> orderRatios=new ArrayList<>();
        while (resultSet.next()){
            String id= resultSet.getString(1);
            String desc=resultSet.getString(2);
            String clr=resultSet.getString(3);
            int s=resultSet.getInt(4);
            int m=resultSet.getInt(5);
            int l=resultSet.getInt(6);
            int xl=resultSet.getInt(7);
            int xxl=resultSet.getInt(8);

            OrderRatio orderRatio=new OrderRatio(id,desc,clr,s,m,l,xl,xxl);
            orderRatios.add(orderRatio);
        }
        return orderRatios;
    }
    public static boolean addRatio(OrderRatio orderRatio) throws SQLException {
        String sql ="INSERT INTO OrderRatio (OrderID,Disc,Colour,SQty,MQty,LQty,XLQty,XXLQty ) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        return CrudUtil.execute(
                sql,
                orderRatio.getOrderId(),
                orderRatio.getDisc(),
                orderRatio.getColour(),
                orderRatio.getSQty(),
                orderRatio.getMQty(),
                orderRatio.getLQty(),
                orderRatio.getXlQty(),
                orderRatio.getXxlty()
        );
    }

    public static boolean delete(String clr, String setOrderId) throws SQLException {
        String sql="DELETE FROM OrderRatio WHERE OrderID=? AND Colour=?";
        boolean result = CrudUtil.execute(
                sql,
                setOrderId,
                clr
        );
        return result;
    }

    public static boolean search(String text) throws SQLException {
        String sql="SELECT * FROM OrderRatio WHERE OrderID=?";
        ResultSet resultSet = CrudUtil.execute(sql,text);

        return resultSet.next();
    }

    public static void deleteAll(String text) throws SQLException {
        String sql="DELETE FROM OrderRatio WHERE OrderID=?";
        CrudUtil.execute(sql,text);
    }
}
