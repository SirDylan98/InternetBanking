package InternetBankingApplication.InternetBanking.Deposit;

import InternetBankingApplication.InternetBanking.Account.DTO.CreateAccRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class DepositController {
    @Autowired
    private DepositService depositService;
    @PutMapping("/deposit")
    public ResponseEntity<DepositResponseDTO> doDeposit(@RequestParam("accountNumber") String accountNumber, @RequestParam("amount") double amount){
        return ResponseEntity.ok().body(depositService.processDeposit(accountNumber,amount));
    }
}
