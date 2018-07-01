package com.snow.rabobank.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.snow.rabobank.entity.Issue;
import com.snow.rabobank.entity.Statement;
import com.snow.rabobank.service.IStatementService;

@RestController
public class UploadController {

	@Autowired
	private IStatementService csvStatementService;

	@Autowired
	private IStatementService xmlStatementService;

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String handleFileUpload(@RequestParam("file") List<MultipartFile> files) {
		List<Statement> allStatements = new ArrayList<>();
		List<Issue> issues = new ArrayList<>();
		files.forEach(multipartFile -> {
			IStatementService statementService = null;
			if (multipartFile.getContentType().equals("text/csv")) {
				statementService = this.csvStatementService;
			} else if (multipartFile.getContentType().equals("text/xml")) {
				statementService = this.xmlStatementService;
			}
			try {
				allStatements.addAll(statementService.parse(multipartFile.getInputStream()));
			} catch (IOException e) {
			}
		});
		issues.addAll(this.csvStatementService.vaidate(allStatements));
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("allStatements", allStatements);
			jsonObject.put("issues", issues);
		} catch (JSONException e) {
		}
		return jsonObject.toString();
	}

}
