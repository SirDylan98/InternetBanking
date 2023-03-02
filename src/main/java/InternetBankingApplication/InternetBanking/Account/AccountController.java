package InternetBankingApplication.InternetBanking.Account;

import InternetBankingApplication.InternetBanking.Account.DTO.AccountLogInResponseDTO;
import InternetBankingApplication.InternetBanking.Account.DTO.CreateAccRequestDTO;
import InternetBankingApplication.InternetBanking.Account.DTO.GetAllAccountDTO;
import InternetBankingApplication.InternetBanking.Account.DTO.LogInCredentials;
import InternetBankingApplication.InternetBanking.Account.Exceptions.AccountNumberAlreadyUsed;
import InternetBankingApplication.InternetBanking.Account.Exceptions.NoAccountFound;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
@Slf4j
public class AccountController {
    @Autowired
    private  AccountService  accountService;

    @PostMapping("/create")
    public ResponseEntity<CreateAccRequestDTO> createAccount (@Valid @RequestBody CreateAccRequestDTO account) throws AccountNumberAlreadyUsed
    {
        return ResponseEntity.ok().body(accountService.createNewAccount(account));
    }
    @GetMapping("/all")
    public ResponseEntity<List<GetAllAccountDTO>> getAllAccounts(){
        return ResponseEntity.ok().body(accountService.getAllAccounts());
    }
    @GetMapping("/{accountNumber}")
    public ResponseEntity<GetAllAccountDTO> getOneAccount(@PathVariable("accountNumber") String accountNumber){
        return ResponseEntity.ok().body(accountService.getAccountByID(accountNumber));
    }
    @GetMapping("/login")
    public ResponseEntity<AccountLogInResponseDTO> logIn (@RequestParam("accountNumber") String accountNumber,@RequestParam("password") String password) throws NoAccountFound {
        log.info("We are in");
        return ResponseEntity.ok().body(accountService.logInToAccount(accountNumber,password));
    }
}
