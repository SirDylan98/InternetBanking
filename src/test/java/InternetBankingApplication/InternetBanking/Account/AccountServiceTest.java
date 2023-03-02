package InternetBankingApplication.InternetBanking.Account;

import InternetBankingApplication.InternetBanking.Account.DTO.CreateAccRequestDTO;
import InternetBankingApplication.InternetBanking.Account.Exceptions.AccountNumberAlreadyUsed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class AccountServiceTest {
    @Mock
    private  AccountRepository  accountRepository;
    @Autowired private AccountService accountService;

    private CreateAccRequestDTO accountCreate;
    @BeforeEach
    void setUp() {
        accountCreate=CreateAccRequestDTO.builder()
                .accountNumber("FN2255444")
                .email("Tashdee@gmail.com")
                .age(25)
                .address("2290 Ascot Infill")
                .password("23ED0778174323")
                .build();
    }

    @Test
    //@Disabled
    void createNewAccount() throws AccountNumberAlreadyUsed {
        // given account

        //when
        CreateAccRequestDTO myAccount = accountService.createNewAccount(accountCreate);
    // then
        //verify(accountRepository).findByAccountNumber();
        assertEquals(accountCreate,myAccount);

    }

    @Test
    void logInToAccount() {
    }
}