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

//		for (int i = 0; i < oneLevelUpMap.size(); i++) {
			for (Map.Entry<String, List<String>> entry : oneLevelUpMap.entrySet()) {

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

				System.out.println("Updated map now: " + oneLevelUpMapCopy);
			}
//		}

		System.out.println("Ordered Employee List:" + orderedEmployeeList);
		System.out.println(oneLevelUpMapCopy.size() +" was the first level list size. Vs. the ordered list size: "+orderedEmployeeList.size()) ;

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
