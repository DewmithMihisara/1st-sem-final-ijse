package lk.ijse.project_dkf.model;

import lk.ijse.project_dkf.dto.OrderRatio;
import lk.ijse.project_dkf.dto.TrimCard;
import lk.ijse.project_dkf.dto.tm.TrimCardTM;
import lk.ijse.project_dkf.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrimCardModel {
    public static boolean addTrimCard(TrimCard trimCard) throws SQLException {
        String sql ="INSERT INTO TrimCard (OrderID,type,Colour,ReqQty ) VALUES(?, ?, ?, ?)";
        return CrudUtil.execute(
                sql,
                trimCard.getId(),
                trimCard.getTyp(),
                trimCard.getClr(),
                trimCard.getQty()
        );
    }

    public static boolean delete(TrimCardTM selectedItem, String setOrderId) throws SQLException {
        String sql="DELETE FROM OrderRatio WHERE OrderID=? AND Colour=? And type =?";
        boolean result = CrudUtil.execute(
                sql,
                setOrderId,
                selectedItem.getClr(),
                selectedItem.getType()
        );
        return result;
    }

    public static List<TrimCard> getAll(String setOrderId) throws SQLException {
        String sql = "SELECT * FROM TrimCard WHERE OrderID =?";
        ResultSet resultSet = CrudUtil.execute(sql,setOrderId);

        ArrayList<TrimCard> trimCards=new ArrayList<>();
        while (resultSet.next()){
            String id = resultSet.getString(1);
            String type=resultSet.getString(2);
            String clr=resultSet.getString(3);
            int qty=resultSet.getInt(4);

            TrimCard trimCard=new TrimCard(id, type, clr, qty);
            trimCards.add(trimCard);
        }
        return trimCards;
    }

    public static boolean search(String text) throws SQLException {
        String sql="SELECT * FROM TrimCard WHERE OrderID=?";
        ResultSet resultSet = CrudUtil.execute(sql,text);

        return resultSet.next();
    }

    public static void deleteAll(String text) throws SQLException {
        String sql="DELETE FROM TrimCard WHERE OrderID=?";
        CrudUtil.execute(sql,text);
    }
}
