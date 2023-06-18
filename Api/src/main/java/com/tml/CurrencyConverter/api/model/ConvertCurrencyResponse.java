package com.tml.CurrencyConverter.api.model;

import com.tml.CurrencyConverter.core.domain.Money;

public record ConvertCurrencyResponse(String targetCurrency, double amount) {

    public static ConvertCurrencyResponse from(Money convertedCurrency) {
        return new ConvertCurrencyResponse(convertedCurrency.currency().name(), convertedCurrency.amount());
    }

}
