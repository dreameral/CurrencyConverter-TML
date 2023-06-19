package com.tml.CurrencyConverter.data.config;

import com.tml.CurrencyConverter.data.model.Provider;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Optional;

@ConfigurationProperties
public class PropertiesConfig {

    private final List<Provider> providers;
    private final String provider;

    public PropertiesConfig(List<Provider> providers, String provider) {
        this.providers = providers;
        this.provider = provider;
    }

    @Bean
    public Provider getProviderInstance() {
        Optional<Provider> optionalProvider = providers.stream()
                .filter(p -> p.name().equalsIgnoreCase(provider))
                .findFirst();

        return optionalProvider.orElseThrow(() -> new RuntimeException("Invalid provider specified"));
    }
}

