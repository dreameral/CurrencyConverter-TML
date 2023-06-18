package com.tml.CurrencyConverter.core.usecase;

import com.tml.CurrencyConverter.core.domain.Currency;

public interface CurrencyRepository {

    Double getConversionRate(Currency source, Currency target);

}
