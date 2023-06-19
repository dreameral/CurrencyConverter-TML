package com.tml.CurrencyConverter.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CurrencyLayerRatesResponse(String source, Map<String, Double> quotes) {

    public ProviderRatesResponse toProviderRatesResponse() {
        if (quotes.size() == 1) {
            // when one of the sources or target is the base currency, currencyLayer API returns only the target rate.
            // E.g when getting the rates for USD and ALL and USD is the base currency,
            // currency layer will return "USDALL": {rate}, different from other providers
            String key = quotes.keySet().toArray()[0].toString();
            String newKey = key.replace(source, "");

            quotes.put(source, 1d);
            quotes.put(newKey, quotes.get(key));
            quotes.remove(key);
        }
        return new ProviderRatesResponse(quotes());
    }

}
