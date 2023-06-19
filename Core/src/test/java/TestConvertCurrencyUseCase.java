import com.tml.CurrencyConverter.core.domain.ConversionRate;
import com.tml.CurrencyConverter.core.domain.Currency;
import com.tml.CurrencyConverter.core.domain.Money;
import com.tml.CurrencyConverter.core.usecase.ConvertCurrencyUseCase;
import com.tml.CurrencyConverter.core.usecase.CurrencyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.ExecutionException;

@ExtendWith(MockitoExtension.class)
public class TestConvertCurrencyUseCase {

    @Mock
    private CurrencyRepository currencyRepository;

    @Test
    public void test_convertCurrencyUseCase() throws ExecutionException, InterruptedException {
        Mockito.when(currencyRepository.providerSupportsConversion()).thenReturn(Boolean.FALSE);
        Mockito.when(currencyRepository.getConversionRate(new Currency("USD"), new Currency("ALL")))
                .thenReturn(new ConversionRate("USD", 1d, "ALL", 97d));

         Money convertedResult = new TestUseCaseExecutor().execute(
                new ConvertCurrencyUseCase(currencyRepository),
                new ConvertCurrencyUseCase.InputValues(new Money(new Currency("USD"), 1000d), new Currency("ALL")),
                 ConvertCurrencyUseCase.OutputValues::targetCurrencyAmount).get();

        Assertions.assertEquals(convertedResult.currency().name(), "ALL");
        Assertions.assertEquals(convertedResult.amount(), 97000d);
    }

}
