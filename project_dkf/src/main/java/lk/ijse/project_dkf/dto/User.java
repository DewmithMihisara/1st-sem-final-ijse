package lk.ijse.project_dkf.dto;

import lombok.*;

@Data
@NoArgsConstructor
public class User {
    private String userName;
    private String password;
    private String userEmail;
    private String contactUser;
    public User(String userName, String password, String userEmail, String contactUser){
        this.userName=userName;
        this.password=password;
        this.userEmail=userEmail;
        this.contactUser=contactUser;
    }

}