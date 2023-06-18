package com.tml.CurrencyConverter.core.usecase;

import com.tml.CurrencyConverter.core.domain.Currency;
import com.tml.CurrencyConverter.core.domain.Money;

public class ConvertCurrencyUseCase extends UseCase<ConvertCurrencyUseCase.InputValues, ConvertCurrencyUseCase.OutputValues> {

    private final CurrencyRepository repository;

    public ConvertCurrencyUseCase(CurrencyRepository repository) {
        this.repository = repository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        Double conversionRate = repository.getConversionRate(input.sourceCurrencyAmount().currency(), input.targetCurrency());
        Money convertedResult =  new Money(input.targetCurrency(), input.sourceCurrencyAmount().amount() * conversionRate);
        return new OutputValues(convertedResult);
    }

    public record InputValues(Money sourceCurrencyAmount, Currency targetCurrency) implements UseCase.InputValues {
    }

    public record OutputValues(Money targetCurrencyAmount) implements UseCase.OutputValues {
    }

}
