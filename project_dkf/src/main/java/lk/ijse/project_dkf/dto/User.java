package lk.ijse.project_dkf.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String userName;
    private String password;
    private String userEmail;
    private String contactUser;
    public User(String userName, String password){
        this.userName=userName;
        this.password=password;
    }

}