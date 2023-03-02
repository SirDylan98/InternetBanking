package InternetBankingApplication.InternetBanking.Account;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends MongoRepository<Account,String> {
    Account findByAccountNumber(String accountNumber);
}
