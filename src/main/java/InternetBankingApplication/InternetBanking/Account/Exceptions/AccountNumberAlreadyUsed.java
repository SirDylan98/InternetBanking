package InternetBankingApplication.InternetBanking.Account.Exceptions;

public class AccountNumberAlreadyUsed extends Exception {
    public AccountNumberAlreadyUsed(String message){
        super(message);
    }
}
