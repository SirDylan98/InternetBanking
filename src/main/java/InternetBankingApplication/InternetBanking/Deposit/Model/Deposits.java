package InternetBankingApplication.InternetBanking.Deposit.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Document(collection = "Deposits")
public class Deposits {

    //TODO create a sequence generator

    @Transient
    public static final String SEQUENCE_NAME="user_sequence";
    @Id
    private  Long depositId;
    private String accountNumber;
    private double amount;
    private String date;



}
