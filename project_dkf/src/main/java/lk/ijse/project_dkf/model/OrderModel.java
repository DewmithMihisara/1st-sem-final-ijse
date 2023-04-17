package lk.ijse.project_dkf.model;

import lk.ijse.project_dkf.db.DBConnection;
import lk.ijse.project_dkf.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderModel {
    public static boolean declairOrder(String text) throws SQLException {
        Connection con =null;
        try {
            con= DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            boolean isNewOrderHave=NewOrderModel.search(text);
            boolean isOrderRatioHave= OrderRatioModel.search(text);
            boolean isTrimCardHave=TrimCardModel.search(text);

            if (isNewOrderHave){
                NewOrderModel.deleteAll(text);
            }
            if (isOrderRatioHave){
                OrderRatioModel.deleteAll(text);
            }
            if (isTrimCardHave){
                TrimCardModel.deleteAll(text);
            }

            if (isOrderRatioHave || isNewOrderHave || isTrimCardHave){
                con.commit();
                return true;
            }
            return false;
        } catch (SQLException e) {
            con.rollback();
            return false;
        }finally {
            con.setAutoCommit(true);
        }

    }

    public static boolean placeOrder(String text) throws SQLException {
        boolean isNewOrderHave=NewOrderModel.search(text);
        boolean isOrderRatioHave= OrderRatioModel.search(text);
        boolean isTrimCardHave=TrimCardModel.search(text);

        return isNewOrderHave && isOrderRatioHave && isTrimCardHave;
    }

    public static boolean delete(String selectedItem) throws SQLException {
        String sql="DELETE FROM Orders WHERE OrderID=?";
        boolean result = CrudUtil.execute(sql,selectedItem);
        return result;
    }

    public static String getNextOrderID() throws SQLException {
        String sql="SELECT BuyerID FROM Orders ORDER BY OrderID DESC LIMIT 1";
        ResultSet resultSet = CrudUtil.execute(sql);

        if (resultSet.next()) {
            return splitOrderId(resultSet.getString(1));
        }
        return splitOrderId(null);
    }

    private static String splitOrderId(String currentId) {
        if(currentId != null) {
            String[] strings = currentId.split("o");
            int id = Integer.parseInt(strings[1]);
            id++;
            return "o" + id;
        }
        return "o10000";    }
}
