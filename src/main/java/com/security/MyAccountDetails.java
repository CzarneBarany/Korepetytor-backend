package com.security;

import com.entities.AccountEntity;
import com.exceptions.EntityNotFoundException;
import com.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyAccountDetails implements UserDetailsService {

    private AccountRepository accountRepository;

    @Autowired
    public MyAccountDetails(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AccountEntity accountEntity = accountRepository.getAccountEntityByEmail(email);
        if (accountEntity != null) {
            return org.springframework.security.core.userdetails.User//
                    .withUsername(email)//
                    .password(accountEntity.getPassword())//
                    .authorities("User")//
                    .accountExpired(false)//
                    .accountLocked(false)//
                    .credentialsExpired(false)//
                    .disabled(false)//
                    .build();
        } else {
            throw new EntityNotFoundException("Nie znaleziono konta o takim adresie email" + email);
        }
    }
}
