package InternetBankingApplication.InternetBanking.Deposit;

import InternetBankingApplication.InternetBanking.Account.Account;
import InternetBankingApplication.InternetBanking.Account.AccountService;
import InternetBankingApplication.InternetBanking.Deposit.Model.Deposits;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

import static InternetBankingApplication.InternetBanking.Deposit.Model.Deposits.SEQUENCE_NAME;

@Service
@Transactional
@Slf4j

@RequiredArgsConstructor
public class DepositService {

    @Autowired
    private AccountService accountService;

    @Autowired
    private  DepositRepository depositRepository;
    @Autowired
    private  SequenceGenerator sequenceGenerator;
    public  void  saveDeposit(String accountNumber, double amount){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date depositDate = new Date();
        String depositDatee = simpleDateFormat.format(depositDate);
        //generate the sequence
        Deposits deposits= Deposits.builder()
                .depositId((long) sequenceGenerator.getSequenceNumber(SEQUENCE_NAME)).
                date(depositDatee)
                .accountNumber(accountNumber)
                .amount(amount).build();
        depositRepository.save(deposits);

        log.info("DEPOSIT RECORDED");
        // UpDate the deposits
        //Todo Update the deposit for the account


    }

    public DepositResponseDTO processDeposit(String accountNumber , double amount){
        Account mydepositAccount=accountService.depositInAccount(accountNumber,amount);
        saveDeposit(accountNumber,amount);



        return DepositResponseDTO.builder()
                .accountNumber(mydepositAccount.getAccountNumber())
                .balance(mydepositAccount.getBalance()).build();

    }

}
