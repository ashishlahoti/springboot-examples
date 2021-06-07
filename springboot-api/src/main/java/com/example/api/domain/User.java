package com.example.api.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Builder
@Getter
@Setter
public class User {

	User(){}

	User(Long id, String name, LocalDate dateOfBirth, LocalDateTime lastLogin, ZonedDateTime zonedDateTime){
		this.id = id;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.lastLogin = lastLogin;
		this.zonedDateTime = zonedDateTime;
	}

	Long id;

	String name;
	
	@JsonFormat(pattern="dd MMM yyyy")
	@JsonSerialize(using = LocalDateSerializer.class)
	LocalDate dateOfBirth;

	@JsonFormat(pattern="dd MMM yyyy hh:mm:ss")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	LocalDateTime lastLogin;
	
	@JsonFormat(pattern = "yyyy-MM-dd@HH:mm:ss.SSSXXX", locale = "en_SG", timezone = "Asia/Singapore")
	@JsonSerialize(using = ZonedDateTimeSerializer.class)
	ZonedDateTime zonedDateTime;

}
