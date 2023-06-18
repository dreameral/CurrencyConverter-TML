package com.tml.CurrencyConverter.api.model;

public record ConvertCurrencyRequest(String sourceCurrency, String targetCurrency, double amount) {
}
