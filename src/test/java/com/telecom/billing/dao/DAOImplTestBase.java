package com.telecom.billing.dao;

import org.springframework.test.context.transaction.TransactionConfiguration;

import com.telecom.billing.test.TestBase;

@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
public class DAOImplTestBase extends TestBase {
}
