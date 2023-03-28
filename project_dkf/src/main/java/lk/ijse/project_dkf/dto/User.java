package lk.ijse.project_dkf.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class User {
    private String userId;
    private String userName;
    private String password;
    private String userEmail;
    private String contactUser;

}