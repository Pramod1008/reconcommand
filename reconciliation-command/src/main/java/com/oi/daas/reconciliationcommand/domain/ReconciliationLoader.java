package com.oi.daas.reconciliationcommand.domain;


import com.oi.daas.reconciliationcommand.service.ReconciliationJobServiceCom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Slf4j
@Component
public class ReconciliationLoader implements CommandLineRunner {

    @Autowired
    private ReconciliationJobServiceCom reconciliationJobServiceCom;

    public void run(String... args) throws Exception {
        String strArgs = String.join(" ", args);
        log.info("Welcome to the Command Line Editor.");

        String[] getOrSet = strArgs.split(" ");

        switch (getOrSet[0]) {
            case "list":
                log.info("recon " + getOrSet[0]);
                getAllReconciliationJobDetails();
                break;
            case "jobid":
                log.info("recon " + getOrSet[0]);
                getReconciliationDetailsById(getOrSet[1]);
                break;
            case "jobcreate":
                log.info("recon " + getOrSet[0]);
                createReconciliationJobConfig();
                break;
            case "jobdelete":
                log.info("recon " + getOrSet[0]);
                deleteReconciliationDetailsById(getOrSet[1]);
                break;
            case "updatejob":
                log.info("recon " + getOrSet[0]);
                updateReconciliationDetailsById(getOrSet[1]);
                break;
            default:
                break;
        }
    }

    private void updateReconciliationDetailsById(String jobId) {
        reconciliationJobServiceCom.updateReconciliationDetailsById(jobId);
    }

    private void deleteReconciliationDetailsById(String jobid) {
        reconciliationJobServiceCom.deleteReconciliationDetailsById(jobid);
    }

    private void createReconciliationJobConfig() {
        reconciliationJobServiceCom.createReconciliationJobConfig();
    }

    private void commandRun() throws Exception {
        Scanner scanner = new Scanner(System.in);
        run(scanner.nextLine());
    }

    private void getReconciliationDetailsById(String jobId) {
        reconciliationJobServiceCom.getReconciliationDetailsById(jobId);
    }

    private void getAllReconciliationJobDetails() {
        reconciliationJobServiceCom.getAllReconciliationJobDetails();
    }
}
