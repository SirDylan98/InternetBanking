package InternetBankingApplication.InternetBanking.Account.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountLogInResponseDTO {
    private String accountNumber;
    private String address;
    private double balance;

    private String email;

    private boolean isAuth;

    private int age;
}
