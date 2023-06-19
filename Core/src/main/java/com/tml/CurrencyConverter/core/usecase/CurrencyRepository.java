package com.tml.CurrencyConverter.core.usecase;

import com.tml.CurrencyConverter.core.domain.ConversionRate;
import com.tml.CurrencyConverter.core.domain.Currency;
import com.tml.CurrencyConverter.core.domain.Money;
import com.tml.CurrencyConverter.core.exception.ConversionException;

public interface CurrencyRepository {

    ConversionRate getConversionRate(Currency source, Currency target) throws ConversionException;

    Boolean providerSupportsConversion();

    Money convert(Money source);

}
