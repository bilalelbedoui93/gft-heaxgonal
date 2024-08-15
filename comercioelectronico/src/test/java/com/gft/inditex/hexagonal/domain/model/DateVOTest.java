package com.gft.inditex.hexagonal.domain.model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.gft.inditex.hexagonal.domain.exception.NotValidLocalDateTimeException;
import com.gft.inditex.hexagonal.domain.exception.ValueObjectIsNullException;
import com.gft.inditex.hexagonal.domain.model.DateVO;

class DateVOTest {

	@Test
	public void verifyConstructorWithCorrectDate_test() {
		//Given
		LocalDateTime expectedDate = LocalDateTime.of(2020, 01, 01, 00, 00);
        
        //When
        DateVO resultDateVo = new DateVO(expectedDate);

        //Then
        assertNotNull(resultDateVo);
        assertEquals(expectedDate, resultDateVo.getDate());
	}
	
	@Test
	public void throws_ValueObjectIsNullExceptionWithNullDate_test() {
		
		assertThrows(ValueObjectIsNullException.class, (() -> new DateVO(null)));	
	}
	
	@Test
	public void verifyMessageThrown_test() {
		//Given
		String messageExpected = "date.is.null";
		
		//When
		ValueObjectIsNullException exception = assertThrows(ValueObjectIsNullException.class, (() -> new DateVO(null)));

		//Then
		assertNotNull(exception);
		assertEquals(messageExpected, exception.getMessage());
	}
	
	@Test
	public void throws_NotValidLocalDateTimeException_provideNoValidtDateAsString_test() {
		
		//Given
		String notVAlidDate = "2020-15-48-00:00:00";
		String messageExpected = "date.is.not.valid";

		//When
		NotValidLocalDateTimeException actualException = assertThrows(NotValidLocalDateTimeException.class, (() -> new DateVO(notVAlidDate, DateVO.DATE_STRING_FORMAT)));	
		NotValidLocalDateTimeException actualException2 = assertThrows(NotValidLocalDateTimeException.class, (() -> new DateVO(notVAlidDate, DateVO.DATE_FORMAT)));
		
		//Then
		assertNotNull(actualException);
		assertEquals(messageExpected, actualException.getMessage());
		
		assertNotNull(actualException2);
		assertEquals(messageExpected, actualException2.getMessage());

	}

}
