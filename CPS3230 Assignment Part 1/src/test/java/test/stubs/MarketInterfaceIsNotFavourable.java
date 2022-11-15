package test.stubs;


import cps3230.assignment.market.MarketInterface;

public class MarketInterfaceIsNotFavourable implements MarketInterface {

    @Override
    public boolean areLatestResultsDisplayed() {
        return false;
    }
}
