package com.solstice.orderorderlineservice.feignClient;

import com.solstice.orderorderlineservice.domain.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(name = "account-address-service", url = "http://account-address-service.cfapps.io")
public interface AccountClient {

    @RequestMapping(path = "/accounts/{id}", method = RequestMethod.GET)
    Account getAccount(@PathVariable("id") Long id);
}
