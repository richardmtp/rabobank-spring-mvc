package com.snow.rabobank;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import com.snow.rabobank.entity.Issue;
import com.snow.rabobank.entity.Statement;
import com.snow.rabobank.service.CsvStatementService;
import com.snow.rabobank.service.IStatementService;
import com.snow.rabobank.service.XmlStatementService;

public class RabobankApplicationTests {

	private IStatementService csvStatementService;

	private IStatementService xmlStatementService;

	@Before
	public void setUp() {
		this.csvStatementService = new CsvStatementService();
		this.xmlStatementService = new XmlStatementService();
	}

	private String path = "." + File.separator + "src" + File.separator + "test" + File.separator + "java"
			+ File.separator + "com" + File.separator + "snow" + File.separator + "rabobank" + File.separator;

	/*
	 * 
	 */
	@Test
	public void uploadCsvFile() {
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(this.path + "records.csv");
		} catch (FileNotFoundException e) {
		}
		if (inputStream != null) {
			List<Statement> statements = this.csvStatementService.parse(inputStream);
			List<Issue> issues = this.csvStatementService.vaidate(statements);
			assertEquals("Statements must have 10 records", 10, statements.size());
			assertEquals("No of accounts having issues is 2", 2, issues.size());
			int sum = issues.stream().mapToInt(issue -> issue.getCount()).sum();
			assertEquals("sum of all issues is 2", 2, sum);
			try {
				inputStream.close();
			} catch (IOException e) {
			}
		}
	}
	
	@Test
	public void uploadCsvWithoutRef() {
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(this.path + "records-without-ref.csv");
		} catch (FileNotFoundException e) {
		}
		if (inputStream != null) {
			List<Statement> statements = this.csvStatementService.parse(inputStream);
			List<Issue> issues = this.csvStatementService.vaidate(statements);
			assertEquals("Statements must have 1 records", 1, statements.size());
			assertEquals("No of accounts having issues is 0", 0, issues.size());
			int sum = issues.stream().mapToInt(issue -> issue.getCount()).sum();
			assertEquals("sum of all issues is 0", 0, sum);
			try {
				inputStream.close();
			} catch (IOException e) {
			}
		}
	}

	@Test
	public void uploadCsvWithoutAccountNumber() {
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(this.path + "records-without-account-number.csv");
		} catch (FileNotFoundException e) {
		}
		if (inputStream != null) {
			List<Statement> statements = this.csvStatementService.parse(inputStream);
			List<Issue> issues = this.csvStatementService.vaidate(statements);
			assertEquals("Statements must have 1 records", 1, statements.size());
			assertEquals("No of accounts having issues is 0", 0, issues.size());
			int sum = issues.stream().mapToInt(issue -> issue.getCount()).sum();
			assertEquals("sum of all issues is 0", 0, sum);
			try {
				inputStream.close();
			} catch (IOException e) {
			}
		}
	}

	@Test
	public void uploadCsvWithoutStartBalance() {
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(this.path + "records-without-start-bal.csv");
		} catch (FileNotFoundException e) {
		}
		if (inputStream != null) {
			List<Statement> statements = this.csvStatementService.parse(inputStream);
			List<Issue> issues = this.csvStatementService.vaidate(statements);
			assertEquals("Statements must have 1 records", 1, statements.size());
			assertEquals("No of accounts having issues is 0", 0, issues.size());
			int sum = issues.stream().mapToInt(issue -> issue.getCount()).sum();
			assertEquals("sum of all issues is 0", 0, sum);
			try {
				inputStream.close();
			} catch (IOException e) {
			}
		}
	}

	@Test
	public void uploadCsvWithoutMutation() {
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(this.path + "records-without-mutation.csv");
		} catch (FileNotFoundException e) {
		}
		if (inputStream != null) {
			List<Statement> statements = this.csvStatementService.parse(inputStream);
			List<Issue> issues = this.csvStatementService.vaidate(statements);
			assertEquals("Statements must have 1 records", 1, statements.size());
			assertEquals("No of accounts having issues is 0", 0, issues.size());
			int sum = issues.stream().mapToInt(issue -> issue.getCount()).sum();
			assertEquals("sum of all issues is 0", 0, sum);
		}
	}

	@Test
	public void uploadCsvWithoutEndBalance() {
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(this.path + "records-without-end-bal.csv");
		} catch (FileNotFoundException e) {
		}
		if (inputStream != null) {
			List<Statement> statements = this.csvStatementService.parse(inputStream);
			List<Issue> issues = this.csvStatementService.vaidate(statements);
			assertEquals("Statements must have 1 records", 1, statements.size());
			assertEquals("No of accounts having issues is 0", 0, issues.size());
			int sum = issues.stream().mapToInt(issue -> issue.getCount()).sum();
			assertEquals("sum of all issues is 0", 0, sum);
			try {
				inputStream.close();
			} catch (IOException e) {
			}
		}
	}
	
	@Test
	public void uploadCsvWithoutDesc() {
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(this.path + "records-without-desc.csv");
		} catch (FileNotFoundException e) {
		}
		if (inputStream != null) {
			List<Statement> statements = this.csvStatementService.parse(inputStream);
			List<Issue> issues = this.csvStatementService.vaidate(statements);
			assertEquals("Statements must have 2 records", 2, statements.size());
			assertEquals("No of accounts having issues is 0", 0, issues.size());
			int sum = issues.stream().mapToInt(issue -> issue.getCount()).sum();
			assertEquals("sum of all issues is 0", 0, sum);
			try {
				inputStream.close();
			} catch (IOException e) {
			}
		}
	}

	@Test
	public void uploadXmlFile() {
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(this.path + "records.xml");
		} catch (FileNotFoundException e) {
		}
		if (inputStream != null) {
			List<Statement> statements = this.xmlStatementService.parse(inputStream);
			List<Issue> issues = this.xmlStatementService.vaidate(statements);
			assertEquals("Statements must have 10 records", 10, statements.size());
			assertEquals("No of accounts having issues is 1", 1, issues.size());
			int sum = issues.stream().mapToInt(issue -> issue.getCount()).sum();
			assertEquals("sum of all issues is 2", 2, sum);
			try {
				inputStream.close();
			} catch (IOException e) {
			}
		}
	}
	
	@Test
	public void uploadXmlWithoutRef() {
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(this.path + "records-without-ref.xml");
		} catch (FileNotFoundException e) {
		}
		if (inputStream != null) {
			List<Statement> statements = this.xmlStatementService.parse(inputStream);
			List<Issue> issues = this.csvStatementService.vaidate(statements);
			assertEquals("Statements must have 1 records", 1, statements.size());
			assertEquals("No of accounts having issues is 1", 1, issues.size());
			int sum = issues.stream().mapToInt(issue -> issue.getCount()).sum();
			assertEquals("sum of all issues is 1", 1, sum);
			try {
				inputStream.close();
			} catch (IOException e) {
			}
		}
	}

	@Test
	public void uploadXmlWithoutAccountNumber() {
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(this.path + "records-without-account-number.xml");
		} catch (FileNotFoundException e) {
		}
		if (inputStream != null) {
			List<Statement> statements = this.xmlStatementService.parse(inputStream);
			List<Issue> issues = this.csvStatementService.vaidate(statements);
			assertEquals("Statements must have 1 records", 1, statements.size());
			assertEquals("No of accounts having issues is 1", 1, issues.size());
			int sum = issues.stream().mapToInt(issue -> issue.getCount()).sum();
			assertEquals("sum of all issues is 1", 1, sum);
			try {
				inputStream.close();
			} catch (IOException e) {
			}
		}
	}
	
	@Test
	public void uploadXmlWithoutStartBalance() {
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(this.path + "records-without-start-bal.xml");
		} catch (FileNotFoundException e) {
		}
		if (inputStream != null) {
			List<Statement> statements = this.xmlStatementService.parse(inputStream);
			List<Issue> issues = this.csvStatementService.vaidate(statements);
			assertEquals("Statements must have 1 records", 1, statements.size());
			assertEquals("No of accounts having issues is 1", 1, issues.size());
			int sum = issues.stream().mapToInt(issue -> issue.getCount()).sum();
			assertEquals("sum of all issues is 1", 1, sum);
			try {
				inputStream.close();
			} catch (IOException e) {
			}
		}
	}

	
	@Test
	public void uploadXmlWithoutMutation() {
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(this.path + "records-without-mutation.xml");
		} catch (FileNotFoundException e) {
		}
		if (inputStream != null) {
			List<Statement> statements = this.xmlStatementService.parse(inputStream);
			List<Issue> issues = this.csvStatementService.vaidate(statements);
			assertEquals("Statements must have 1 records", 1, statements.size());
			assertEquals("No of accounts having issues is 1", 1, issues.size());
			int sum = issues.stream().mapToInt(issue -> issue.getCount()).sum();
			assertEquals("sum of all issues is 1", 1, sum);
			try {
				inputStream.close();
			} catch (IOException e) {
			}
		}
	}

	@Test
	public void uploadXmlWithoutEndBalance() {
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(this.path + "records-without-end-bal.xml");
		} catch (FileNotFoundException e) {
		}
		if (inputStream != null) {
			List<Statement> statements = this.xmlStatementService.parse(inputStream);
			List<Issue> issues = this.csvStatementService.vaidate(statements);
			assertEquals("Statements must have 1 records", 1, statements.size());
			assertEquals("No of accounts having issues is 1", 1, issues.size());
			int sum = issues.stream().mapToInt(issue -> issue.getCount()).sum();
			assertEquals("sum of all issues is 1", 1, sum);
			try {
				inputStream.close();
			} catch (IOException e) {
			}
		}
	}
	
	@Test
	public void uploadXmlWithoutDesc() {
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(this.path + "records-without-desc.xml");
		} catch (FileNotFoundException e) {
		}
		if (inputStream != null) {
			List<Statement> statements = this.xmlStatementService.parse(inputStream);
			List<Issue> issues = this.csvStatementService.vaidate(statements);
			assertEquals("Statements must have 1 records", 2, statements.size());
			assertEquals("No of accounts having issues is 1", 1, issues.size());
			int sum = issues.stream().mapToInt(issue -> issue.getCount()).sum();
			assertEquals("sum of all issues is 1", 1, sum);
			try {
				inputStream.close();
			} catch (IOException e) {
			}
		}
	}

	@Test
	public void uploadMultipleFile() {
		InputStream inputStream = null;
		InputStream xInputStream = null;
		try {
			inputStream = new FileInputStream(this.path + "records.csv");
			xInputStream = new FileInputStream(this.path + "records.xml");
		} catch (FileNotFoundException e) {
		}
		List<Statement> statements = new ArrayList<>();
		List<Issue> issues = new ArrayList<>();
		int sum = 0;
		if (inputStream != null) {
			statements.addAll(this.csvStatementService.parse(inputStream));
			statements.addAll(this.xmlStatementService.parse(xInputStream));
			issues = this.csvStatementService.vaidate(statements);
			sum = issues.stream().mapToInt(issue -> issue.getCount()).sum();
			try {
				inputStream.close();
				xInputStream.close();
			} catch (IOException e) {
			}
		}
		assertEquals("Statements must have 20 records", 20, statements.size());
		assertEquals("No of accounts having issues is 2", 2, issues.size());
		assertEquals("sum of all issues is 4", 4, sum);
	}

	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(RabobankApplicationTests.class);

		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}

		System.out.println(result.wasSuccessful());
	}

}
