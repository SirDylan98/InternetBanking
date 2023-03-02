package InternetBankingApplication.InternetBanking.Account.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateAccRequestDTO {
    private String accountNumber;
    private String address;
    private double balance;
    @NotBlank(message = "Email cannot Be")
    @Email(message = "Please Enter a Valid Email")
    private String email;
    @Size(min=6,max = 18,message = "Password must be 6 chars and more")
    private String password;
    @Min(18)
    private int age;
}
