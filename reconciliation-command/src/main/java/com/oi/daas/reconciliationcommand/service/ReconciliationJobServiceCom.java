package com.oi.daas.reconciliationcommand.service;

public interface ReconciliationJobServiceCom {

    void getAllReconciliationJobDetails();

    void getReconciliationDetailsById(String jobId);

    void createReconciliationJobConfig();

    void deleteReconciliationDetailsById(String jobId);

    void updateReconciliationDetailsById(String jobId);
}
