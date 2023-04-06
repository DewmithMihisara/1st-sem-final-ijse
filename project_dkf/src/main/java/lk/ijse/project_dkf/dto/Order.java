package lk.ijse.project_dkf.dto;

import lombok.*;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Order {
    private String orderId;
    private String compId;
    private String payment;
    private Date orderDate;
    private int ttlQty;
    private int dailyOut;
    private Date dline;
}
