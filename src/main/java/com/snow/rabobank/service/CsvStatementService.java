package com.snow.rabobank.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.snow.rabobank.entity.Statement;

@Component
public class CsvStatementService implements IStatementService {

	@Override
	public List<Statement> parse(InputStream inputStream) {
		List<Statement> statements = new ArrayList<>();
		statements = new BufferedReader(new InputStreamReader(inputStream)).lines().skip(1).filter(s -> {
			String[] array = s.split(",");
			if(array.length < 6) {
				return false;
			}
			if(array[1] == null || array[1].isEmpty()) {
				return false;
			}
			try {
				Long.parseLong(array[0]);
				Double.parseDouble(array[3]);
				Double.parseDouble(array[4]);
				Double.parseDouble(array[5]);
			} catch (Exception e) {
				return false;
			}
			return true;
		}).map(s -> {
			String[] array = s.split(",");
			if (array.length >= 6) {
				return new Statement(Long.parseLong(array[0]), array[1], array[2], Double.parseDouble(array[3]),
						Double.parseDouble(array[4]), Double.parseDouble(array[5]));
			}
			return null;

		}).collect(Collectors.toList());
		return statements;
	}

}
