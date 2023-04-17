package lk.ijse.project_dkf.model;

import lk.ijse.project_dkf.dto.Material;
import lk.ijse.project_dkf.dto.Output;
import lk.ijse.project_dkf.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MaterialModel {
    public static boolean add(Material material) throws SQLException {
        String sql ="INSERT INTO Material (MaterialID, Type, MaterialQty, Date ) VALUES(?, ?, ?, ?)";
        return CrudUtil.execute(
                sql,
                material.getId(),
                material.getType(),
                material.getQty(),
                material.getDate()
        );
    }

    public static boolean delete(Material material) throws SQLException {
        String sql="DELETE FROM Material WHERE MaterialID=? AND Type=? AND MaterialQty=? AND Date=? ";
        boolean result = CrudUtil.execute(
                sql,
                material.getId(),
                material.getType(),
                material.getQty(),
                material.getDate()
        );
        return result;
    }

    public static List<Material> getAll(String id) throws SQLException {
        String sql = "SELECT * FROM Material WHERE MaterialID =?";
        ResultSet resultSet = CrudUtil.execute(sql,id);

        ArrayList<Material> materials=new ArrayList<>();
        while (resultSet.next()){
            String materialID= resultSet.getString(1);
            String type=resultSet.getString(2);
            int qty=resultSet.getInt(3);
            Date date=resultSet.getDate(4);

            Material material=new Material(materialID,type,qty,date);
            materials.add(material);
        }
        return materials;
    }
}
