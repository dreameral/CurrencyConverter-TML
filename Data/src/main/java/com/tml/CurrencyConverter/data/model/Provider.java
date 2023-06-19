package com.tml.CurrencyConverter.data.model;

public record Provider(String name,
                       String apiKey,
                       String baseApiUrl,
                       String listRatesEndpoint,
                       String convertCurrencyEndpoint,
                       Boolean supportsConversion) {
}
