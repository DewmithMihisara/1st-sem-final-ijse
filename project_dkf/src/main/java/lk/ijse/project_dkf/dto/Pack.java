package lk.ijse.project_dkf.dto;

import lombok.*;

import java.util.Date;
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pack {
    private String packID;
    private Date date;
    private String clr;
    private String size;
    private int packQty;
}
