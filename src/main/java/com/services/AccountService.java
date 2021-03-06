package com.services;

import com.entities.AccountEntity;
import com.exceptions.AccountAlreadyExistsException;
import com.exceptions.EntityNotFoundException;
import com.models.LoginModel;
import com.models.SessionModel;
import com.repositories.AccountRepository;
import com.security.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AccountService {

    private AccountRepository accountRepository;
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AccountService(AccountRepository accountRepository, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    public List<AccountEntity> getAllAccounts() {
        return accountRepository.findAll();
    }

    public void addAccount(AccountEntity accountEntity) {
        if (accountRepository.existsByEmail(accountEntity.getEmail())) {
            throw new AccountAlreadyExistsException("Istnieje juz konto o takim adresie email");
        }

        log.debug("Tworze nowe konto użytkownika!");
        accountEntity.setPassword(passwordEncoder.encode(accountEntity.getPassword()));
        accountRepository.save(accountEntity);
    }

    public void deleteUser(Integer accountId) {
        if (accountRepository.existsById(accountId)) {
            accountRepository.delete(accountRepository.getAccountEntityByAccountId(accountId));
            log.debug("Usuwam usera o id:" + accountId);
        } else {
            throw new EntityNotFoundException("Nie znaleziono konta o takim id" + accountId);
        }
    }

    public SessionModel login(LoginModel loginModel) {
        log.debug("LOGOWANIE - START");
        String email = loginModel.getEmail();
        String password = loginModel.getPassword();
        AccountEntity accountEntity = accountRepository.getAccountEntityByEmail(email);

        if (accountEntity == null) {
            throw new EntityNotFoundException("Nie znaleziono konta o takim emailu");
        }

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        SessionModel sessionModel = new SessionModel();
        sessionModel.setAccountId(accountEntity.getAccountId());
        sessionModel.setRole(accountEntity.getRole());
        sessionModel.setJwtToken(jwtTokenProvider.createToken(email, accountEntity.getRole()));

        log.debug("LOGOWANIE - END");
        return sessionModel;
    }

    public AccountEntity getAccountByAccountId(int id) {
        AccountEntity accountEntity = accountRepository.getAccountEntityByAccountId(id);
        if (accountEntity == null) {
            throw new EntityNotFoundException("Nie znaleziono konta o takim id");
        }

        return accountEntity;
    }

    public void editAccount(AccountEntity accountEntity) {
        accountRepository.save(accountEntity);
        log.debug("Edytuje konto o id:" + accountEntity.getAccountId());
    }
}
