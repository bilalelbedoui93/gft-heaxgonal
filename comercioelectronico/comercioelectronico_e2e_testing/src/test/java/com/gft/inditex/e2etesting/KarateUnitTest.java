package com.gft.inditex.e2etesting;

import com.intuit.karate.junit5.Karate;
import com.intuit.karate.junit5.Karate.Test;

public class KarateUnitTest {

	private static final String PATH_FEATURES="classpath:com/gft/inditex/e2etesting/feature/";
	
	@Test
	Karate executeEnd2EndTests_forComercioElectronicoAPI() {
		return Karate.run(PATH_FEATURES).relativeTo(getClass());
	}
}
