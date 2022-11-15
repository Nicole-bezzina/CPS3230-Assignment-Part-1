package test.stubs;


import cps3230.assignment.market.MarketInterface;

public class MarketInterfaceIsFavourable implements MarketInterface {

    @Override
    public boolean areLatestResultsDisplayed() {
        return true;
    }
}

