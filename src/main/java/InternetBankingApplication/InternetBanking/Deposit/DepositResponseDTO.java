package InternetBankingApplication.InternetBanking.Deposit;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepositResponseDTO {
    private String accountNumber;
    @NotBlank(message = "The amount cannot be blank")
    @Min(value = 10, message = "The minimum deposit is $10 ")
    private double balance;



}
