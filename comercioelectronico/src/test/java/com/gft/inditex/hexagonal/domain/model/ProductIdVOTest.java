package com.gft.inditex.hexagonal.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.gft.inditex.hexagonal.domain.exception.ValueObjectIsNullException;
import com.gft.inditex.hexagonal.domain.model.ProductIdVO;

class ProductIdVOTest {

	@Test
	public void verifyConstructorWithCorrectId_test() {
		//Given
        Integer idExpected = 1;
        
        //When
        ProductIdVO resultValueProductId = new ProductIdVO(idExpected);

        //Then
        assertNotNull(resultValueProductId);
        assertEquals(idExpected, resultValueProductId.getId());
	}
	
	@Test
	public void throws_ValueObjectIsNullExceptionWithNullId_test() {
		
		assertThrows(ValueObjectIsNullException.class, (() -> new ProductIdVO(null)));	
	}
	
	@Test
	public void verifyMessageThrown_test() {
		//Given
		String messageExpected = "productid.is.null";
		
		//When
		ValueObjectIsNullException exception = assertThrows(ValueObjectIsNullException.class, (() -> new ProductIdVO(null)));

		//Then
		assertNotNull(exception);
		assertEquals(messageExpected, exception.getMessage());
	}
}
