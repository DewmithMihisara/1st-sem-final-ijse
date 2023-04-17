package lk.ijse.project_dkf.model;

import lk.ijse.project_dkf.dto.Buyer;
import lk.ijse.project_dkf.dto.Pack;
import lk.ijse.project_dkf.dto.TrimCard;
import lk.ijse.project_dkf.dto.tm.PackingTM;
import lk.ijse.project_dkf.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PackingModel {
    public static List<Pack> getAll(String packId) throws SQLException {
        String sql = "SELECT * FROM Packing WHERE PackID =?";
        ResultSet resultSet = CrudUtil.execute(sql,packId);

        ArrayList<Pack> packs=new ArrayList<>();
        while (resultSet.next()){
            String id = resultSet.getString(1);
            Date date=resultSet.getDate(2);
            String clr=resultSet.getString(3);
            String size=resultSet.getString(4);
            int qty=resultSet.getInt(5);

            Pack pack=new Pack(id, date, clr,size, qty);
            packs.add(pack);
        }
        return packs;
    }

    public static boolean add(Pack pack) throws SQLException {
        String sql ="INSERT INTO Packing (PackID, Date, Colour, Size, PackQty ) VALUES(?, ?, ?, ?, ?)";
        return CrudUtil.execute(
                sql,
                pack.getPackID(),
                pack.getDate(),
                pack.getClr(),
                pack.getSize(),
                pack.getPackQty()
        );
    }

    public static boolean delete(PackingTM packingTM , String id) throws SQLException {
        String sql="DELETE FROM Packing WHERE PackID=? AND Date=? AND Colour=? AND Size=? AND PackQty=?";
        boolean result = CrudUtil.execute(
                sql,
                id,
                packingTM.getDate(),
                packingTM.getClr(),
                packingTM.getSize(),
                packingTM.getQty()
        );
        return result;
    }
}
