package InternetBankingApplication.InternetBanking.Account;

import InternetBankingApplication.InternetBanking.Account.DTO.AccountLogInResponseDTO;
import InternetBankingApplication.InternetBanking.Account.DTO.CreateAccRequestDTO;
import InternetBankingApplication.InternetBanking.Account.DTO.GetAllAccountDTO;
import InternetBankingApplication.InternetBanking.Account.Exceptions.AccountNumberAlreadyUsed;
import InternetBankingApplication.InternetBanking.Account.Exceptions.NoAccountFound;
import InternetBankingApplication.InternetBanking.Account.processors.AccountProcessors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AccountService {

    @Autowired
    private final AccountRepository accountRepository;

    private final AccountProcessors accountProcessors;



    //Get all the accounts
    public List<GetAllAccountDTO> getAllAccounts() {
        List<Account> allTheAccounts = accountRepository.findAll();
        return accountProcessors.createAllAccList(allTheAccounts);
    }

    //Get account by Id
    public GetAllAccountDTO getAccountByID(String accountNumber) {
        Account myAccount = accountRepository.findByAccountNumber(accountNumber);
        //
        GetAllAccountDTO getAccountDTO = new GetAllAccountDTO();
        BeanUtils.copyProperties(myAccount, getAccountDTO);
        return getAccountDTO;
//
    }

    public CreateAccRequestDTO createNewAccount(CreateAccRequestDTO account) throws AccountNumberAlreadyUsed {
        //checking if accounts is already used
        Account accountNumberUsed = accountRepository.findByAccountNumber(account.getAccountNumber());
        if (accountNumberUsed != null) {
            log.info("The account number is already used");
            throw new AccountNumberAlreadyUsed("The account number is already used");
        }

        Account createAcc = Account.builder()
                .accountNumber(account.getAccountNumber())
                .address(account.getAddress())
                .age(account.getAge())
                .email(account.getEmail())
                .password(account.getPassword())
                .balance(0).build();
        accountRepository.save(createAcc);

        return account;


    }

    public AccountLogInResponseDTO logInToAccount(String accountNumber, String password) throws NoAccountFound {
        //check if the account exists
        Account accountNumberFound = accountRepository.findByAccountNumber(accountNumber);
        AccountLogInResponseDTO responseDTO;
        if (accountNumberFound == null) { // checking if there is No a Account in the DB
            log.info("There is no account associated with this accountNumber");
            throw new NoAccountFound("There is no account associated with this accountNumber");
        } else {
            // if the account exists
            if (password.equals(accountNumberFound.getPassword())) {
                log.info("CORRECT CREDENTIALS");
                responseDTO = AccountLogInResponseDTO.builder()
                        .accountNumber(accountNumberFound.getAccountNumber())
                        .address(accountNumberFound.getAddress())
                        .email(accountNumberFound.getEmail())
                        .age(accountNumberFound.getAge())
                        .balance(accountNumberFound.getBalance())
                        .isAuth(true).build();
                // build our response with correct auth
            } else {
                log.info("INCORRECT CREDENTIALS");

                // build our response with incorrect auth
                responseDTO = AccountLogInResponseDTO.builder()
                        .accountNumber(accountNumberFound.getAccountNumber())
                        .isAuth(false).build();
                System.out.println("These are my Credentials" + responseDTO.getAccountNumber());

            }
            return responseDTO;
        }

    }

    // Deposit


    public Account depositInAccount(String accountNumber, double amount) {
        // get the account details
        Account myAccount = accountRepository.findByAccountNumber(accountNumber);
        double newBalance = myAccount.getBalance() + amount;
        myAccount.setBalance(newBalance);
        log.info("The deposit was successful");
        return accountRepository.save(myAccount);
    }

}
