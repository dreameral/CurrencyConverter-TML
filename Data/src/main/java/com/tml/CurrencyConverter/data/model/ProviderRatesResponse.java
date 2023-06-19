package com.tml.CurrencyConverter.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ProviderRatesResponse(Map<String, Double> rates) {
}
