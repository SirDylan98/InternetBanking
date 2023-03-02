package InternetBankingApplication.InternetBanking.TransferFunds;

import InternetBankingApplication.InternetBanking.Account.Account;
import InternetBankingApplication.InternetBanking.Account.AccountRepository;
import InternetBankingApplication.InternetBanking.Account.AccountService;
import InternetBankingApplication.InternetBanking.Account.DTO.GetAllAccountDTO;
import InternetBankingApplication.InternetBanking.Account.Exceptions.InsufficientFunds;
import InternetBankingApplication.InternetBanking.Deposit.DepositResponseDTO;
import InternetBankingApplication.InternetBanking.TransferFunds.DTO.TransferResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class TransferFundsServices {

    @Autowired
    private AccountRepository accountRepository;// needed to check if the sender has enough money and if the other account exists

    @Autowired
    private AccountService accountService;

    @Autowired
    private MongoTemplate mongoTemplate;


    public TransferResponseDTO sendMoneyTo(String accountNumberSender, String accountNumberReceiver, double amount) throws InsufficientFunds {
        // check if the accountsender balance  is less than amount to be sent

        GetAllAccountDTO accountSender = accountService.getAccountByID(accountNumberSender);
        GetAllAccountDTO accountReceiver = accountService.getAccountByID(accountNumberReceiver);
        if(accountSender.getBalance()<amount) throw new  InsufficientFunds(" You don't have enough funds to make this Transcation");
        //remove the money from sender and  and add it to reciever
        Query query = new Query().addCriteria(Criteria.where("_id").is(accountNumberSender));
        Update updateSender = new Update().set("balance",accountSender.getBalance()-amount);

        Account senderAccount=mongoTemplate.findAndModify(query,updateSender,Account.class);
        log.info("SENDER Account has been updated");
        Query queryReceiver = new Query().addCriteria(Criteria.where("_id").is(accountNumberReceiver));
        Update updateReceiver = new Update().set("balance",accountReceiver.getBalance()+amount);

        Account receiverAccount=mongoTemplate.findAndModify(queryReceiver,updateReceiver,Account.class);
        log.info("RECEIVER Account has been updated");

        // updating the reciever

        TransferResponseDTO transferResponseDTO=TransferResponseDTO.builder()
                .accountSender(accountNumberSender)
                .accountReceiver(accountNumberReceiver)
                .amountTransferred(amount)
                .remainingBalance(senderAccount.getBalance()).build();



        return transferResponseDTO ;
    }


}
