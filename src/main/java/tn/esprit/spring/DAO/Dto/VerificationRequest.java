package tn.esprit.spring.DAO.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VerificationRequest {
    private String code;
    private String email;


}
