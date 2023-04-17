package lk.ijse.project_dkf.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CutTM {
    private Date date;
    private String oID;
    private String clr;
    private String size;
    private String type;
    private int qty;
}
