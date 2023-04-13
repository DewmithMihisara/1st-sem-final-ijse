package lk.ijse.project_dkf.dto;

import lombok.*;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Output {
    private String oId;
    private Date date;
    private String clr;
    private String size;
    private int out;
}
