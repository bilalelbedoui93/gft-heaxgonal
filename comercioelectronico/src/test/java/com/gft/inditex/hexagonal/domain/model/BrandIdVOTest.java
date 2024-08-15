package com.gft.inditex.hexagonal.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.gft.inditex.hexagonal.domain.exception.ValueObjectIsNullException;

class BrandIdVOTest {

	@Test
	public void verifyConstructorWithCorrectId_test() {
		//Given
        Integer idExpected = 1;
        
        //When
        BrandIdVO resultValueBrandId = new BrandIdVO(idExpected);

        //Then
        assertNotNull(resultValueBrandId);
        assertEquals(idExpected, resultValueBrandId.getId());
	}
	
	@Test
	public void throws_ValueObjectIsNullExceptionWithNullId_test() {
		
		assertThrows(ValueObjectIsNullException.class, (() -> new BrandIdVO(null)));	
	}
	
	@Test
	public void verifyMessageThrown_test() {
		//Given
		String messageExpected = "brandid.is.null";
		
		//When
		ValueObjectIsNullException exception = assertThrows(ValueObjectIsNullException.class, (() -> new BrandIdVO(null)));

		//Then
		assertNotNull(exception);
		assertEquals(messageExpected, exception.getMessage());
	}
}
