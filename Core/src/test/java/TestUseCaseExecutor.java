import com.tml.CurrencyConverter.core.usecase.UseCase;
import com.tml.CurrencyConverter.core.usecase.UseCaseExecutor;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class TestUseCaseExecutor implements UseCaseExecutor {
    @Override
    public <RX, I extends UseCase.InputValues, O extends UseCase.OutputValues> CompletableFuture<RX> execute(UseCase<I, O> useCase, I input, Function<O, RX> outputMapper) {
        return CompletableFuture
                .supplyAsync(() -> input)
                .thenApplyAsync(useCase::execute)
                .thenApplyAsync(outputMapper);
    }
}
