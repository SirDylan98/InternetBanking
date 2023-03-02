package InternetBankingApplication.InternetBanking.Account.processors;

import InternetBankingApplication.InternetBanking.Account.Account;
import InternetBankingApplication.InternetBanking.Account.DTO.GetAllAccountDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AccountProcessors {

    // bussiness log

    public List<GetAllAccountDTO> createAllAccList(List<Account> allTheAccounts){
        List<GetAllAccountDTO> newRevisedList = new ArrayList<>();
        for (Account acc : allTheAccounts) {
            GetAllAccountDTO getAllAccountDTO = new GetAllAccountDTO();
            // USING BEAN UTILS TO COMP
            BeanUtils.copyProperties(acc, getAllAccountDTO);
            newRevisedList.add(getAllAccountDTO);//
        }
        List<GetAllAccountDTO> newAccountList = allTheAccounts.stream().map(account -> dtoMapper(account))
                .collect(Collectors.toList());
        log.info("YES WE ARE GETTING ALL THE ACCOUNTS");
        return  newAccountList;
    }
    public GetAllAccountDTO dtoMapper(Account account) {
        GetAllAccountDTO getAllAccountDTO = new GetAllAccountDTO();
        BeanUtils.copyProperties(account, getAllAccountDTO);
        return getAllAccountDTO;
    }
}
