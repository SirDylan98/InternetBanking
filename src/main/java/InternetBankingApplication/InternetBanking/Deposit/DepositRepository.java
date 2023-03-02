package InternetBankingApplication.InternetBanking.Deposit;

import InternetBankingApplication.InternetBanking.Deposit.Model.Deposits;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DepositRepository extends MongoRepository<Deposits,Long> {
}
