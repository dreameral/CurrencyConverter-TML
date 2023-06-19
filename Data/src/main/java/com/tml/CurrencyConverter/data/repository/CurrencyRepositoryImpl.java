package com.tml.CurrencyConverter.data.repository;

import com.tml.CurrencyConverter.core.domain.ConversionRate;
import com.tml.CurrencyConverter.core.domain.Currency;
import com.tml.CurrencyConverter.core.domain.Money;
import com.tml.CurrencyConverter.core.exception.ConversionException;
import com.tml.CurrencyConverter.core.usecase.CurrencyRepository;
import com.tml.CurrencyConverter.data.service.ProviderService;
import org.springframework.stereotype.Repository;

@Repository
public class CurrencyRepositoryImpl implements CurrencyRepository {

    private final ProviderService providerService;

    public CurrencyRepositoryImpl(ProviderService providerService) {
        this.providerService = providerService;
    }

    @Override
    public ConversionRate getConversionRate(Currency source, Currency target) throws ConversionException {
        return providerService.getRates(source, target);
    }

    @Override
    public Boolean providerSupportsConversion() {
        return providerService.providerSupportsConversion();
    }

    @Override
    public Money convert(Money source) {
        return providerService.convert(source);
    }
}
