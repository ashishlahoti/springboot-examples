package com.abc.config;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
public class CourseConfig {
	

	@Value("${course.title}")
	private String title;
	
	@Value("${course.duration}")
	private int duration;
	
	@Value("${course.rating}")
	private float rating;
	
	@Value("${course.page_views}")
	private double pageViews;
	
	@Value("${course.trending}")
	private boolean trending;
	
	// Default Value
	@Value("${course.review: No Reviews Yet}")
	private String review;
	
	// SpEL expression used to initialize a Date
	@Value("#{T(java.time.LocalDate).parse('${course.created_date}')}")
	private LocalDate createdDate;
	
	// Comma separated property values auto initialize a List
	//@Value("${course.tags}")
	@Value ("#{'${course.tags}'.split(',')}")
	private List<String> tags;
	
	@Value("#{'${course.tags}'.split(',')[0]}")
	private String firstTag;
	
	// SpEL expression used to initialize a Map
	@Value("#{${course.keyword_count}}")
	private Map<String, Integer> keywordCountMap;
	
	@Value("#{${course.keyword_count}.keyword1}")
	private Integer Keyword1Count;
	
	@Value("#{${course.keyword_count}['keyword2']}")
	private Integer Keyword2Count;
	
	@Value("#{${course.keyword_count}.?[value > '50']}")
	private Map<String, Integer> keywordCountMapFiltered;
	
	@Value("#{${unknownMap : {key1: '1', key2: '2'}}}")
	private Map<String, Integer> unknownMap;
	 
	@Value("#{${course.keyword_count}['unknownKey'] ?: 100}")
	private Integer unknownKeyWithDefaultValue;
	
	@Value("#{systemProperties}")
	private Map<String, String> systemPropertiesMap;
	
	@Value("${file.encoding}")
	private String fileEncoding;
	
	@Value("#{systemProperties['java.home']}")
	private String javaVersion;
	
	
	@Value("Test")
	public void printValues(String a, String b){
		System.out.println(a + " & " + b);
	}
	
	@Value("Test")
	public void printOtherValues(String a, @Value("Data") String b){
		System.out.println(a + " & " + b);
	}
	
	@GetMapping("/course")
	private String getCourse() {
		return "Course { title: " + title + 
				", duration: " + duration + 
				", rating:" + rating + 
				", views:" + pageViews +
				", trending:" + trending + 
				", createdDate:" + createdDate +
				", review:" + review + 
				", tags:" + tags.size() + tags + 
				", keywordCountMap:" + keywordCountMap.size() + keywordCountMap +
				", Keyword1Count:" + Keyword1Count + 	
				", Keyword2Count:" + Keyword2Count + 	
				", keywordCountMapFiltered:" + keywordCountMapFiltered + 	
				", unknownMap:" + unknownMap + 	
				", unknownKeyWithDefaultValue:" + unknownKeyWithDefaultValue + 
				", javaVersion:" + javaVersion +
				", fileEncoding:" + fileEncoding + "}";
	}
}
