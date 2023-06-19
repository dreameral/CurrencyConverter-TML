package com.tml.CurrencyConverter.data.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tml.CurrencyConverter.core.domain.ConversionRate;
import com.tml.CurrencyConverter.core.domain.Currency;
import com.tml.CurrencyConverter.core.domain.Money;
import com.tml.CurrencyConverter.core.exception.ConversionException;
import com.tml.CurrencyConverter.core.exception.InvalidInputException;
import com.tml.CurrencyConverter.data.model.CurrencyLayerRatesResponse;
import com.tml.CurrencyConverter.data.model.Provider;
import com.tml.CurrencyConverter.data.model.ProviderRatesResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class ProviderService {

    private final Provider provider;

    public ProviderService(Provider provider) {
        this.provider = provider;
    }

    public ConversionRate getRates(Currency source, Currency target) throws ConversionException {
        String uri = provider.baseApiUrl() + String.format(provider.listRatesEndpoint(), provider.apiKey(), source.name(), target.name());
        try {
            HttpRequest request = HttpRequest.newBuilder().uri(new URI(uri)).build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            ProviderRatesResponse ratesResponse = readResponse(response.body());

            if (!ratesResponse.rates().containsKey(source.name()) || !ratesResponse.rates().containsKey(target.name())) {
                throw new InvalidInputException("Either source or target currency does not exist.");
            }

            return new ConversionRate(
                    source.name(),
                    ratesResponse.rates().get(source.name()),
                    target.name(),
                    ratesResponse.rates().get(target.name())
            );
        } catch (URISyntaxException e) {
            throw new ConversionException("Something went wrong. Please contact our support team.", e);
        } catch (IOException | InterruptedException e) {
            throw new ConversionException("Failed to fetch rates from provider.", e);
        }
    }

    public Boolean providerSupportsConversion() {
        return provider.supportsConversion();
    }

    public Money convert(Money source) {
        throw new UnsupportedOperationException("This service is not offered yet.");
    }

    private ProviderRatesResponse readResponse(String body) throws IOException {
        return provider.name().equalsIgnoreCase("currencyLayer") ?
                new ObjectMapper().readValue(body, CurrencyLayerRatesResponse.class).toProviderRatesResponse() :
                new ObjectMapper().readValue(body, ProviderRatesResponse.class);
    }

}
