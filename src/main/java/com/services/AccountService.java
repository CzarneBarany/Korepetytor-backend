package com.services;

import com.entities.AccountEntity;
import com.exceptions.EntityNotFoundException;
import com.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<AccountEntity> getAllAccounts(){
        return accountRepository.findAll();
    }

    public void addAccount(AccountEntity accountEntity){
        accountRepository.save(accountEntity);
    }

    public void deleteUser(Integer accountId){
        if(accountRepository.existsById(accountId)) {
            accountRepository.delete(accountRepository.getAccountEntityByAccountId(accountId));
        }
        else{
            throw new EntityNotFoundException("Nie znaleziono konta o takim id" + accountId);
        }
    }

}
