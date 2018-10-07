package com.solstice.accountaddressservice.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.solstice.accountaddressservice.domain.Account;
import com.solstice.accountaddressservice.domain.Address;
import com.solstice.accountaddressservice.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private AccountService accountService;
    private static Logger logger = LoggerFactory.getLogger(AccountController.class);

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    
    @GetMapping
    @HystrixCommand(fallbackMethod = "getAccountsFallback")
    public Iterable<Account> getAccounts() {

        return accountService.getAccounts();
    }

    @GetMapping("/{id}")
    @HystrixCommand(fallbackMethod = "getAccountFallback")
    public Account getAccount(@PathVariable("id") long id) {

        return accountService.getAccount(id);
    }

    @PostMapping()
    @HystrixCommand(fallbackMethod = "addAccountFallback")
    public Account addAccount(@RequestBody Account account) {
        logger.info("Account created: "+ account.toString());
        return accountService.addAccount(account);
    }

    @PostMapping("/{id}/address")
    @HystrixCommand(fallbackMethod = "addAddressToAccountFallback")
    public Address addAddressToAccount(@PathVariable("id") long id,@RequestBody Address address) {

        return accountService.addAddressToAccount(id,address);
    }

    @PutMapping("/{id}")
    @HystrixCommand(fallbackMethod = "updateAccountFallback")
    public Account updateAccount(@PathVariable("id") long accountId, @RequestBody Account account) {
        logger.info("Account updated"+ account.toString());
        return accountService.updateAccount(accountId, account);
    }

    @DeleteMapping("/{id}")
    @HystrixCommand(fallbackMethod = "deleteAccountFallback")
    public void deleteAccount(@PathVariable("id") Long id) {

        accountService.deleteAccount(id);
        logger.info("Account deleted.");
    }

    public Iterable<Account> getAccountsFallback() {
        logger.error("Fallback while getting list of Accounts");
        return (Iterable<Account>) new Account();
    }

    public Account getAccountFallback(long id) {
        logger.error("Fallback while getting account with id"+id);
        return new Account();
    }

    public Account addAccountFallback(Account account) {
        logger.error("Fallback while adding account");
        return new Account();
    }

    public Address addAddressToAccountFallback(long id, Address address) {
        logger.error("Fallback while adding address to account");
        return new Address();
    }

    public Account updateAccountFallback(long id,Account account) {
        logger.error("Fallback while updating account");
        return new Account();
    }

    public void deleteAccountFallback(){
        logger.info("Fallbakc while deleting account");

    }


}
