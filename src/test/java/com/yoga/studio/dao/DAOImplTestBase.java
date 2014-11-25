package com.yoga.studio.dao;

import org.springframework.test.context.transaction.TransactionConfiguration;

import com.yoga.studio.test.TestBase;

@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
public class DAOImplTestBase extends TestBase {
}
