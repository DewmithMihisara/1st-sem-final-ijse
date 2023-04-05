package lk.ijse.project_dkf.model;

import lk.ijse.project_dkf.dto.Buyer;
import lk.ijse.project_dkf.dto.User;
import lk.ijse.project_dkf.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BuyerModel {
    public static boolean addBuyer(Buyer buyer) throws SQLException {
        String sql ="INSERT INTO Buyer (BuyerID,BuyerName,BuyerCN,BuyerAddress ) VALUES(?, ?, ?, ?)";
        return CrudUtil.execute(
                sql,
                buyer.getBuyerId(),
                buyer.getBuyerName(),
                buyer.getBuyerCn(),
                buyer.getBuyerAddress()
        );
    }
    public static List<Buyer> getAll() throws SQLException {
        String sql = "SELECT * FROM Buyer";
        ResultSet resultSet = CrudUtil.execute(sql);

        ArrayList<Buyer> buyers=new ArrayList<>();
        while (resultSet.next()){
            String id= resultSet.getString(1);
            String name=resultSet.getString(2);
            String cn=resultSet.getString(3);
            String address=resultSet.getString(4);

            Buyer buyer=new Buyer(id,name,cn,address);
            buyers.add(buyer);
        }
        return buyers;
    }

    public static Buyer search(Buyer buyer) throws SQLException {
        String sql = "SELECT * FROM Buyer WHERE BuyerID=?";
        ResultSet resultSet = CrudUtil.execute(sql,buyer.getBuyerId());
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

    public static boolean update(Buyer buyer) throws SQLException {
        String sql = "UPDATE Buyer SET BuyerName = ?, BuyerCN = ?, BuyerAddress = ? WHERE BuyerID = ?";
        boolean result = CrudUtil.execute(sql,buyer.getBuyerName(),buyer.getBuyerCn(),buyer.getBuyerAddress(),buyer.getBuyerId());
        return result;
    }

    public static boolean delete(Buyer buyer) throws SQLException {
        String sql="DELETE FROM Buyer WHERE BuyerID=?";
        boolean result = CrudUtil.execute(sql,buyer.getBuyerId());
        return result;
    }
}
