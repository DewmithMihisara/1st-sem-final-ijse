package lk.ijse.project_dkf.model;

import lk.ijse.project_dkf.dto.Cut;
import lk.ijse.project_dkf.dto.Pack;
import lk.ijse.project_dkf.dto.tm.CutTM;
import lk.ijse.project_dkf.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CutModel {
    public static List<Cut> getAll(String id) throws SQLException {
        String sql = "SELECT * FROM Cut WHERE CutID =?";
        ResultSet resultSet = CrudUtil.execute(sql,id);

        ArrayList<Cut> cuts=new ArrayList<>();
        while (resultSet.next()){
            String cutID = resultSet.getString(1);
            Date date=resultSet.getDate(2);
            int qty=resultSet.getInt(3);
            String type= resultSet.getString(4);
            String size=resultSet.getString(5);
            String clr=resultSet.getString(6);

            Cut cut=new Cut(cutID, date, qty, type, size, clr);
            cuts.add(cut);
        }
        return cuts;
    }

    public static boolean add(Cut cut) throws SQLException {
        String sql ="INSERT INTO Cut (CutID, Date, CutQty, Type, Size, Colour ) VALUES(?, ?, ?, ?, ?, ?)";
        return CrudUtil.execute(
                sql,
                cut.getCutID(),
                cut.getDate(),
                cut.getCutQty(),
                cut.getType(),
                cut.getSize(),
                cut.getClr()
        );
    }

    public static boolean delete(CutTM cutTM, String selectedItem) throws SQLException {
        String sql="DELETE FROM Cut WHERE CutID=? AND Date=? AND CutQty=? AND Type=? AND Size=? AND Colour= ?";
        boolean result = CrudUtil.execute(
                sql,
                selectedItem,
                cutTM.getDate(),
                cutTM.getQty(),
                cutTM.getType(),
                cutTM.getSize(),
                cutTM.getClr()
        );
        return result;
    }
}
