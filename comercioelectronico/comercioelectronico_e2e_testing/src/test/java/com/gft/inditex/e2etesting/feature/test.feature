Feature: Working with Price API

	Scenario: Retrieve the correct price, when a unique price is available in the BDD for a requested Date
		Given url 'http://localhost:8082/api/v1/prices?dateApplyingPriceString=2020-06-14-14:00:00&productId=35455&brandId=1'
		When method GET
		Then status 200
		And match response == {"productId": 35455,"brandId": 1,"startDate": "2020-06-14T00:00:00","endDate": "2020-12-31T23:59:59","price": 35.5}
	
	
	Scenario: Retrieve highest priority price, when a two price are available in the BDD for a requested Date
		Given url 'http://localhost:8082/api/v1/prices?dateApplyingPriceString=2020-06-14-17:00:00&productId=35455&brandId=1'
		When method GET
		Then status 200
		And match response == {"productId": 35455,"brandId": 1,"startDate": "2020-06-14T15:00:00","endDate": "2020-06-14T18:30:00","price": 25.45}
	
	
	Scenario: No price is found for the product
		Given url 'http://localhost:8082/api/v1/prices?dateApplyingPriceString=2020-01-01-17:00:00&productId=35455&brandId=1'
		When method GET
		Then status 404
		And match response.message == "price.not.found"
		
		
	Scenario: It should fail if a non valid date is provided in the request
		Given url 'http://localhost:8082/api/v1/prices?dateApplyingPriceString=2020-56-39-17:00:00&productId=35455&brandId=1'
		When method GET
		Then status 400
		And match response.message == "date.is.not.valid"
		
		
	Scenario: The should fail if a non numeric product ID is provided in the request
		Given url 'http://localhost:8082/api/v1/prices?dateApplyingPriceString=2020-06-14-17:00:00&productId=hola&brandId=1'
		When method GET
		Then status 400
		
	Scenario: The should fail if a non numeric brand ID is provided in the request
		Given url 'http://localhost:8082/api/v1/prices?dateApplyingPriceString=2020-06-14-17:00:00&productId=35455&brandId=hola'
		When method GET
		Then status 400
		
		