package com.gft.inditex.domain.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import com.gft.inditex.domain.configuration.IValueObject;
import com.gft.inditex.domain.exception.NotValidLocalDateTimeException;
import com.gft.inditex.domain.exception.ValueObjectIsNullException;

public class DateVO implements IValueObject{

	public static final String DATE_STRING_FORMAT="yyyy-MM-dd-HH:mm:ss";
	public static final String DATE_FORMAT="yyyy-MM-dd HH:mm:ss";

	private LocalDateTime date;

	
	public DateVO(LocalDateTime date) {
		if(!Objects.nonNull(date)) {
			throw new ValueObjectIsNullException("date");
		}
		this.date=date;
	}
	
	public DateVO(String dateString, String format) {
		if(!Objects.nonNull(dateString)) {
			throw new ValueObjectIsNullException("date");
		}

		this.date=convertStringToLocalDateTime(dateString, DATE_STRING_FORMAT);
	}
	
	public LocalDateTime getDate() {
		return date;
	}
	
		
	public LocalDateTime convertStringToLocalDateTime(String dateString, String format) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		
		try {
			return LocalDateTime.parse(dateString, formatter);									
		}catch (Exception e) {
			throw new NotValidLocalDateTimeException();
		}
	}	

}
