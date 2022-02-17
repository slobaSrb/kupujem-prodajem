package rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.dto;

import lombok.Builder;
import lombok.Data;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.Roles;

@Data
@Builder
public class UserDTO {
    private Long userID;
    private String userName;
    private String fullName;
    private String email;
    private String password;
    private Double avgRating;
    //private String salt;
    private Roles role;
}
