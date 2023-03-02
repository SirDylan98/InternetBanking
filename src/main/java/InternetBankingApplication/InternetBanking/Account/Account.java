package InternetBankingApplication.InternetBanking.Account;

import InternetBankingApplication.InternetBanking.Deposit.Model.Deposits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Document(collection = "Accounts")
public class Account {
    @Id
    private String accountNumber;
//    @NotBlank(message = "Full Name is required")
//    private  String fullName;
//    private  String gender;
//    @NotBlank(message = "phone number is required")
//    private  String phoneNumber;
    private String address;
    @NotBlank(message = "Email cannot Be")
    @Email(message = "Please Enter a Valid Email")
    private String email;

    private double balance;
    @Size(min=6,max = 18,message = "Password must be 6 chars and more")
    private String password;
    @Min(18)
    private int age;

    private List<Deposits> myDeposits;


}
