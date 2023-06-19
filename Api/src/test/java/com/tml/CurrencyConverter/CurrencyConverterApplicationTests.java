package com.tml.CurrencyConverter;

import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@AutoConfigureMockMvc
@SpringBootTest
class CurrencyConverterApplicationTests {

	@Autowired
	MockMvc mvc;

	@Test
	public void currencyConverter_test()
			throws Exception {
		mvc.perform(post("/convertCurrency")
						.content("""
								{
								\t"sourceCurrency": "USD",
								    "targetCurrency": "ALL",
								    "amount": 1000
								}""")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				//TODO needs fixing, test against non changing data
				.andExpect(jsonPath("$.targetCurrency", new IsEqual("ALL")))
				.andExpect(jsonPath("$.amount", new IsEqual(98940)));
	}

}
