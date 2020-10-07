package com.oi.daas.reconciliationcommand.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.oi.daas.reconciliation.model.ReconciliationJob;
import com.oi.daas.reconciliationcommand.config.AppProperties;
import jdk.internal.org.objectweb.asm.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;

@Slf4j
@Component
public class ReconciliationJobServiceImpl implements ReconciliationJobServiceCom {

    private final String CONTEXT_PATH = "/api/v1/";
    private final String GET_JOB_PATH = "jobs";

    private RestTemplate restTemplate;

    @Autowired
    private AppProperties appProperties;

    public ReconciliationJobServiceImpl(RestTemplateBuilder restTemplate) {
        this.restTemplate = restTemplate.build();
    }

    public void getAllReconciliationJobDetails() {
        log.info("Calling getAllReconciliationJobDetails");

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        final String URL = appProperties.getDataReconciliationHost() + CONTEXT_PATH + GET_JOB_PATH;
        log.info(URL);
        ResponseEntity<String> result = restTemplate.exchange(URL, HttpMethod.GET, entity, String.class);
        log.info(result.getBody());
    }

    public void getReconciliationDetailsById(String jobId) {
        log.info("Calling getReconciliationDetailsById");

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        final String URL = appProperties.getDataReconciliationHost() + CONTEXT_PATH + GET_JOB_PATH + "/" + jobId;
        log.info(URL);
        ResponseEntity<String> result = restTemplate.exchange(URL, HttpMethod.GET, entity, String.class);
        log.info(result.getBody());

    }

    public void createReconciliationJobConfig() {
        log.info("Calling createReconciliationJobConfig");

        ReconciliationJob reconciliationJob = new ReconciliationJob();

        ObjectMapper mapper = new ObjectMapper();
        InputStream inputStream = TypeReference.class.getResourceAsStream("/json/reconciliationJob.json");

        try {
            reconciliationJob = mapper.readValue(inputStream, ReconciliationJob.class);
            log.info("Job present with id: " + reconciliationJob.getJobId());
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<?> entity = new HttpEntity<>(reconciliationJob, httpHeaders);

        final String URL = appProperties.getDataReconciliationHost() + CONTEXT_PATH + GET_JOB_PATH;
        log.info(URL);

        ResponseEntity<ReconciliationJob> responseEntity = restTemplate.exchange(URL, HttpMethod.POST, entity, ReconciliationJob.class);
        log.info(responseEntity.getStatusCode().toString());
    }

    @Override
    public void deleteReconciliationDetailsById(String jobId) {
        log.info("Calling deleteReconciliationDetailsById");

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        final String URL = appProperties.getDataReconciliationHost() + CONTEXT_PATH + GET_JOB_PATH + "/" + jobId;
        log.info(URL);
        ResponseEntity<String> result = restTemplate.exchange(URL, HttpMethod.DELETE, entity, String.class);
        log.info(result.getBody());
    }

}