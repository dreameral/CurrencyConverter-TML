package com.tml.CurrencyConverter.api.config;

import com.tml.CurrencyConverter.core.usecase.ConvertCurrencyUseCase;
import com.tml.CurrencyConverter.core.usecase.CurrencyRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DependencyConfig {

    @Bean
    public ConvertCurrencyUseCase createOpeningUseCase(CurrencyRepository currencyRepository) {
        return new ConvertCurrencyUseCase(currencyRepository);
    }

}
