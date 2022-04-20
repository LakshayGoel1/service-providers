package com.myassignment.providers.service;

import com.myassignment.providers.pojo.ServiceProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class ProviderService {

    @Autowired
    private RestTemplate restTemplate;

    public String requestAcceptOrDeny(String serviceProviderNumber){
        if(serviceProviderNumber.startsWith("E") || serviceProviderNumber.startsWith("T")){
            log.info("Request accepted");
            ServiceProvider serviceProvider = new ServiceProvider();
            serviceProvider.setServiceProviderName("Assumed Company Ltd");
            serviceProvider.setServiceProviderNumber(serviceProviderNumber);
            restTemplate.postForObject("http://SERVICE-RECEIVERS/order/notification/booking", serviceProvider, String.class);
            return "Accepted";
        }
        else{
            log.info("Request denied due to unavailability");
            return "Denied";
        }
    }

}
