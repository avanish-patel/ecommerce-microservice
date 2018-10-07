package com.solstice.accountaddressservice.service;

import com.solstice.accountaddressservice.domain.Account;
import com.solstice.accountaddressservice.domain.Address;
import com.solstice.accountaddressservice.repository.AccountRepository;
import com.solstice.accountaddressservice.repository.AddressRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private AccountRepository accountRepository;
    private AddressRepository addressRepository;

    public AccountService(AccountRepository accountRepository, AddressRepository addressRepository) {
        this.accountRepository = accountRepository;
        this.addressRepository = addressRepository;
    }

    public Iterable<Account> getAccounts() {

        return accountRepository.findAll();
    }

    public Account getAccount(long id) {
        return accountRepository.findById(id).get();
    }

    public Account addAccount(Account account) {

        return accountRepository.save(account);

    }

    public Account updateAccount(long id, Account account) {

        return accountRepository.save(account);
    }

    public void deleteAccount(long id) {

        accountRepository.deleteById(id);
    }

    public Address addAddressToAccount(long id, Address address) {

        Account account = accountRepository.findById(id).get();

        address.setAccount(account);
       return addressRepository.save(address);
    }
}
