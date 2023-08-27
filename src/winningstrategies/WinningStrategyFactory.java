package winningstrategies;

public class WinningStrategyFactory {
    public static WinningStrategy getWinningStrategy(int dimension)
    {
        return new OrderOneWinningStrategy(dimension);
    }
}
