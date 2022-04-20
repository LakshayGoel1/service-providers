package com.myassignment.providers.pojo;

import lombok.Data;

@Data
public class JobRequest {

    private String jobDescription;

    private String consumerAddress;

    private String consumerNumber;

    private String assignedServiceProvider;

}
