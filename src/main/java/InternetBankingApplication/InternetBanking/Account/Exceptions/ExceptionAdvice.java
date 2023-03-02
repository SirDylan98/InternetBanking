package InternetBankingApplication.InternetBanking.Account.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@ResponseStatus
public class ExceptionAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(AccountNumberAlreadyUsed.class)
    public ResponseEntity<ErrorMessage> accountAlreadyUsed(AccountNumberAlreadyUsed exception){
        ErrorMessage error= ErrorMessage.builder()
                .httpStatus(HttpStatus.ALREADY_REPORTED)
                .message(exception.getMessage()).build();
        return ResponseEntity.status(HttpStatus.IM_USED).body(error);
    }
    @ExceptionHandler(NoAccountFound.class)
    public  ResponseEntity<ErrorMessage> noAccountFound( NoAccountFound exception){
        ErrorMessage error =ErrorMessage.builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .message(exception.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(InsufficientFunds.class)
    public  ResponseEntity<ErrorMessage> insufficientFunds(InsufficientFunds exception){
        ErrorMessage errror= ErrorMessage.builder()
                .httpStatus(HttpStatus.NOT_ACCEPTABLE)
                .message(exception.getMessage()).build();
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(errror);
    }
}
