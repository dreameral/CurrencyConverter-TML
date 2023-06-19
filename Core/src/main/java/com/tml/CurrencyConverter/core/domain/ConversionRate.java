package com.tml.CurrencyConverter.core.domain;

public record ConversionRate(String sourceCurrency, Double sourceRate, String targetCurrency, Double targetRate) {

    public Money toTarget(Double amount) {
        return new Money(new Currency(this.targetCurrency), amount * (targetRate / sourceRate));
    }

}
