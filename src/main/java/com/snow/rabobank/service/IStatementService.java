package com.snow.rabobank.service;

import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.snow.rabobank.entity.Issue;
import com.snow.rabobank.entity.Statement;

public interface IStatementService {

	// Known issue - Logger not working with JUNIT
	/*Logger logger = Logger.getLogger(IStatementService.class);*/

	List<Statement> parse(InputStream inputStream);

	/*
	 * default interface common for all implementation Both Csv & Xml implementation
	 * require to validate the data
	 */
	default List<Issue> vaidate(List<Statement> allStatements) {
		Set<Statement> uniqueStatements = new HashSet<>();
		Map<String, Issue> issueMap = new HashMap<>();
		allStatements.forEach(statement -> {
			if (uniqueStatements.contains(statement)) {
				/*if (logger.isDebugEnabled()) {
					logger.debug("duplicate - acc: " + statement.getAccountNumber() + " ref: " + statement.getReference());
				}*/
				/*
				 * If there is no known issue for the account create new issue and increment
				 * issue count plus one If there is a known issue for the account then retrieve
				 * the issue and increment issue count plus one
				 */
				if (issueMap.containsKey(statement.getAccountNumber())) {
					issueMap.get(statement.getAccountNumber()).countPlusOne();
				} else {
					Issue issue = new Issue(statement.getAccountNumber());
					issue.countPlusOne();
					issueMap.put(statement.getAccountNumber(), issue);
				}
			} else {
				uniqueStatements.add(statement);
			}
			if (!this.isValidEndBalance(statement)) {
				/*if (logger.isDebugEnabled()) {
					logger.debug("end balance not correct - acc: " + statement.getAccountNumber() + " ref: " + statement.getReference());
				}*/
				/*
				 * If there is no known issue for the account create new issue and increment
				 * issue count plus one If there is a known issue for the account then retrieve
				 * the issue and increment issue count plus one
				 */
				if (issueMap.containsKey(statement.getAccountNumber())) {
					issueMap.get(statement.getAccountNumber()).countPlusOne();
				} else {
					Issue issue = new Issue(statement.getAccountNumber());
					issue.countPlusOne();
					issueMap.put(statement.getAccountNumber(), issue);
				}
			}
		});
		return new ArrayList<>(issueMap.values());
	}

	default boolean isValidEndBalance(Statement statement) {
		double endBalance = statement.getStartBalance() + statement.getMutation();
		BigDecimal bd = new BigDecimal(endBalance);
		bd = bd.setScale(2, RoundingMode.HALF_UP);
		endBalance = bd.doubleValue();
		return statement.getEndBalance() == endBalance;

	}

}
