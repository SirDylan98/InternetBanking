package InternetBankingApplication.InternetBanking.TransferFunds;

import InternetBankingApplication.InternetBanking.TransferFunds.DTO.TransferResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transfer")
@RequiredArgsConstructor
public class TransferFundsController {

    @Autowired
    private  TransferFundsServices transferFundsServices;


    @PutMapping("/")
    public ResponseEntity<TransferResponseDTO> transferFunds(@RequestParam("sender") String accountSender
    ,@RequestParam("receiver") String accountReceiver, @RequestParam("amount") double amount){

        return ResponseEntity.ok().body(transferFundsServices.sendMoneyTo(accountSender,accountReceiver,amount));
    }
}
