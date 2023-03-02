package InternetBankingApplication.InternetBanking.Account.Exceptions;

public class InsufficientFunds extends RuntimeException{
    public  InsufficientFunds(String message){
        super(message);
    }
}
