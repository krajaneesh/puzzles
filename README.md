package com.everchanging;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EverChangingOrgApplicationTests {

	Map<String, String> tempMap = new HashMap<String, String>();

	String jsonInput;

	Map<String, List<String>> oneLevelUpMap = null;

	Map<String, List<String>> oneLevelUpMapCopy = null;
	Map<String, String> sourceMap;

	List<Employee> orderedEmployeeList = new LinkedList<>();

	Employee bigBoss = null;

	@Before
	public void setup() throws Exception {
		tempMap.put("Fawad", "Duane");
		tempMap.put("Raj", "Tom");
		tempMap.put("Duane", "Michael");
		tempMap.put("Wendy", "Mahesh");
		tempMap.put("Tom", "Wendy");
		tempMap.put("Mahesh", "Fawad");
		tempMap.put("Mark", "Duane");
		tempMap.put("Ritesh", "Fawad");
		tempMap.put("Steve", "Tom");
		tempMap.put("Ritesh", "Fawad");
		tempMap.put("Naveen", "Tom");
		tempMap.put("Barb", "Michael");
		tempMap.put("Kelly", "Mahesh");
		tempMap.put("Venkata", "Wendy");
		tempMap.put("Scott", "Tom");

		jsonInput = new JSONObject(tempMap).toString();

		System.out.println("Raw data populated to build JSON String. Json Input:" + jsonInput);

		sourceMap = new ObjectMapper().readValue(jsonInput, HashMap.class);

		System.out.println(
				"Map with Json Data after converting populated Map to String - expected input and back to Map for processing:"
						+ sourceMap);

		oneLevelUpMap = buildtargetMap();
		oneLevelUpMapCopy = buildtargetMap();

		System.out.println("Temporary staging map with 1 level hierarchy:" + oneLevelUpMap);
		
		
		
		do {
			
			for (Map.Entry<String, List<String>> entry : oneLevelUpMap.entrySet()) {
				
				if((oneLevelUpMapCopy.containsKey(entry.getKey()))) {
					boolean hasSup = doesSupervisorHaveSupervisor(entry.getKey(), oneLevelUpMapCopy);

					System.out.println("***Does " + entry.getKey() + " have supervisor?: " + hasSup);

					if (!hasSup) {

						Employee emp = new Employee(entry.getKey());

						for (String sub : oneLevelUpMap.get(entry.getKey())) {
							emp.getList().add(new Employee(sub));
						}
						orderedEmployeeList.add(emp);
						oneLevelUpMapCopy.remove(entry.getKey());

					}
				}

				

				System.out.println("Updated map now: " + oneLevelUpMapCopy);
			}
			
		}while(oneLevelUpMapCopy.size()!=0);
		

		

		System.out.println("Ordered Employee List:" + orderedEmployeeList);
		System.out.println(oneLevelUpMapCopy.size() + " was the first level list size. Vs. the ordered list size: "
				+ orderedEmployeeList.size());

		
		Employee boss = null;
		
		for(int i = orderedEmployeeList.size()-1; i>0 ;i--) {
			
			
//			if(orderedEmployeeList.get(i-1).getList().contains(orderedEmployeeList.get(i))) {
				for(Employee emp: orderedEmployeeList.get(i-1).getList()) {
					if(emp.getName().equals(orderedEmployeeList.get(i-1).getName())) {
//						orderedEmployeeList.get(i-1).getList().add(emp);
						boss = new Employee(orderedEmployeeList.get(i-1).getName());					
						boss.getList().add(emp);
					}
				}
//			}else {
//				System.out.println("missing hierarchy");
//			}
				
				
		}
		
		System.out.println("Final nested Employees:" + boss);
	}

	private boolean doesSupervisorHaveSupervisor(String supervisor, Map<String, List<String>> filteredMap) {
		boolean hasSupervisor = false;
		for (Map.Entry<String, List<String>> entry : filteredMap.entrySet()) {
			if (entry.getValue().contains(supervisor)) {
				return true;
			}
		}
		return hasSupervisor;

	}

	private Map<String, List<String>> buildtargetMap() {

		Map<String, List<String>> employeeMap = new HashMap<>();

		for (Map.Entry<String, String> entry : sourceMap.entrySet()) {
			if (employeeMap.containsKey(entry.getValue())) {
				if (!employeeMap.get(entry.getValue()).isEmpty()) {
					employeeMap.get(entry.getValue()).add(entry.getKey());
				} else {
					List<String> subList = employeeMap.get(entry.getValue());
					subList = new ArrayList<String>();
					subList.add(entry.getKey());
				}
			} else {
				List<String> subList = new ArrayList<String>();
				subList.add(entry.getKey());
				employeeMap.put(entry.getValue(), subList);
			}
		}

		return employeeMap;
	}

	@Test
	public void contextLoads() {

	}

}




{
  "name": "javascript-development-environment",
  "version": "1.0.0",
  "description": "JavaScript development environment Pluralsight course by Cory House",
  "scripts": {
  },
  "author": "Cory House",
  "license": "MIT",
  "dependencies": {
    "whatwg-fetch": "1.0.0"
  },
  "devDependencies": {
    "babel-cli": "6.16.0",
    "babel-core": "6.17.0",
    "babel-loader": "6.2.5",
    "babel-preset-latest": "6.16.0",
    "babel-register": "6.16.3",
    "chai": "3.5.0",
    "chalk": "1.1.3",
    "cheerio": "0.22.0",
    "compression": "1.6.2",
    "cross-env": "3.1.3",
    "css-loader": "0.25.0",
    "eslint": "3.8.1",
    "eslint-plugin-import": "2.0.1",
    "eslint-watch": "2.1.14",
    "express": "4.14.0",
    "extract-text-webpack-plugin": "1.0.1",
    "html-webpack-plugin": "2.22.0",
    "jsdom": "9.8.0",
    "json-schema-faker": "0.3.6",
    "json-server": "0.8.22",
    "localtunnel": "1.8.1",
    "mocha": "3.1.2",
    "nock": "8.1.0",
    "npm-run-all": "3.1.1",
    "nsp": "2.6.2",
    "numeral": "1.5.3",
    "open": "0.0.5",
    "rimraf": "2.5.4",
    "style-loader": "0.13.1",
    "surge": "0.20.4",
    "webpack": "1.13.2",
    "webpack-dev-middleware": "1.8.4",
    "webpack-hot-middleware": "2.13.0",
    "webpack-md5-hash": "0.0.5"
  }
}
