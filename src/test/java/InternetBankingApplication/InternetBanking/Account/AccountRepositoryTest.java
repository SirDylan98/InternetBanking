package InternetBankingApplication.InternetBanking.Account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.*;


@DataMongoTest
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepositoryTest;

    @BeforeEach
    void setUp() {
    }


    @Test
    void  findByAccountNumber(){
        //given
        String accountNumber="FN683852 ";

        //when
        Account myAccount= accountRepositoryTest.findByAccountNumber(accountNumber);

        //then
        assertEquals(accountNumber,myAccount.getAccountNumber());

    }
}