package InternetBankingApplication.InternetBanking.TransferFunds.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransferResponseDTO {

    
    private String accountSender;
    private String accountReceiver;
    private String receiverName;
    private  double amountTransferred;
    private double remainingBalance;
    
}
