package com.lemi.newsfeedmicroservice.client;

import com.lemi.newsfeedmicroservice.dto.client.AccountInfoClientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("account-info-microservice")
public interface AccountInfoClientApi {

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/rest/v1/account_info/{id}",
            consumes = "application/json"
    )
    AccountInfoClientDto getById(@PathVariable (name = "id") Long id);

}
