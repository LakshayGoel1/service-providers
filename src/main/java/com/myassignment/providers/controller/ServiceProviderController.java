package com.myassignment.providers.controller;


import com.myassignment.providers.pojo.JobRequest;
import com.myassignment.providers.service.ProviderService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/notification")
public class ServiceProviderController {

    @Autowired
    private ProviderService providerService;

    @PostMapping("/provider/{provider_number}")
    public ResponseEntity<String> notificationToServiceProvider(@PathVariable("provider_number") String serviceProvider)
        throws JSONException {
        log.info("Notification received by service provider: {}", serviceProvider);
        String status = providerService.requestAcceptOrDeny(serviceProvider);
        JSONObject response = new JSONObject();
        response.put("Order status", status);
        return new ResponseEntity<>(response.toString(), HttpStatus.OK);
    }

    @PostMapping("/consumer/data")
    public ResponseEntity<String> notificationToServiceProvider(@RequestBody JobRequest jobRequest) throws JSONException {
        log.info("Consumer details received by service provider: {}", jobRequest.getAssignedServiceProvider());
        log.info("Job description: {}, Consumer address: {}, Consumer contact number: {}",
            jobRequest.getJobDescription(), jobRequest.getConsumerAddress(), jobRequest.getConsumerNumber());
        JSONObject response = new JSONObject();
        response.put("Status", "Job Description and Consumer data received by service provider");
        return new ResponseEntity<>(response.toString(), HttpStatus.OK);
    }

}
