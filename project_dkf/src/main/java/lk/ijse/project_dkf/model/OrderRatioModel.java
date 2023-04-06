package lk.ijse.project_dkf.model;

import lk.ijse.project_dkf.dto.OrderRatio;
import lk.ijse.project_dkf.util.CrudUtil;

import java.sql.SQLException;

public class OrderRatioModel {
    public static boolean addOrderRatio(OrderRatio orderRatio) throws SQLException {
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
}
