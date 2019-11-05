package com.endpoints;

import com.entities.AccountEntity;
import com.models.LoginModel;
import com.models.SessionModel;
import com.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class AccountEndpoint {

    private AccountService accountService;

    @Autowired
    public AccountEndpoint(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/add/account")
    ResponseEntity addAccount(@RequestBody AccountEntity accountEntity) {
        accountService.addAccount(accountEntity);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/get/account/{id}")
    ResponseEntity<AccountEntity> getAccountByAccountId(@PathVariable int id){
    return new ResponseEntity<>(accountService.getAccountByAccountId(id), HttpStatus.OK);
    }

    @GetMapping("/get/allAccounts")
    ResponseEntity<List<AccountEntity>> getAllAccounts() {
        return new ResponseEntity<>(accountService.getAllAccounts(), HttpStatus.OK);
    }

    @PostMapping("/delete/account/{id}")
    ResponseEntity deleteAccount(@PathVariable int id) {
        accountService.deleteUser(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/login")
    ResponseEntity<SessionModel> login(@RequestBody LoginModel loginModel) {
        return new ResponseEntity<>(accountService.login(loginModel), HttpStatus.OK);
    }
}
