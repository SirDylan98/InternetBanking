package InternetBankingApplication.InternetBanking.Account.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogInCredentials {

    private String accountNumber;
    private String password;

}
