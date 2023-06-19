package com.tml.CurrencyConverter.api.controller;

import com.tml.CurrencyConverter.api.model.ConvertCurrencyRequest;
import com.tml.CurrencyConverter.api.model.ConvertCurrencyResponse;
import com.tml.CurrencyConverter.core.domain.Currency;
import com.tml.CurrencyConverter.core.domain.Money;
import com.tml.CurrencyConverter.core.usecase.ConvertCurrencyUseCase;
import com.tml.CurrencyConverter.core.usecase.UseCaseExecutor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
public class CurrencyConverterController {

    private final UseCaseExecutor useCaseExecutor;
    private final ConvertCurrencyUseCase convertCurrencyUseCase;

    public CurrencyConverterController(UseCaseExecutor useCaseExecutor,
                                       ConvertCurrencyUseCase convertCurrencyUseCase) {
        this.useCaseExecutor = useCaseExecutor;
        this.convertCurrencyUseCase = convertCurrencyUseCase;
    }

    @PostMapping("/convertCurrency")
    public CompletableFuture<ConvertCurrencyResponse> convertCurrency(@RequestBody ConvertCurrencyRequest convertCurrencyRequest) {
        String sourceCurrency = convertCurrencyRequest.sourceCurrency();
        String targetCurrency = convertCurrencyRequest.targetCurrency();
        double amount = convertCurrencyRequest.amount();

        return useCaseExecutor.execute(
                convertCurrencyUseCase,
                new ConvertCurrencyUseCase.InputValues(new Money(new Currency(sourceCurrency), amount), new Currency(targetCurrency)),
                (outputValues) -> ConvertCurrencyResponse.from(outputValues.targetCurrencyAmount()));
    }

}
