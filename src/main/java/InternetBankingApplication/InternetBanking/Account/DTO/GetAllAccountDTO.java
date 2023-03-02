package InternetBankingApplication.InternetBanking.Account.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetAllAccountDTO {
    private String accountNumber;
    private String address;
    private double balance;

    private String email;



    private int age;
}
