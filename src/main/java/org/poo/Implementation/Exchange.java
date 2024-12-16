package org.poo.Implementation;

import org.poo.fileio.ExchangeInput;

import java.util.HashMap;

public final class Exchange {
    private String mainRate; // mainRate is initialized to the first "from" parameter
    private final HashMap<String, Double> rates;


    public Exchange(final ExchangeInput[] exchangeInputs) {
        rates = new HashMap<String, Double>();
        this.initExchange(exchangeInputs);
    }

    /**
     * This private method is usd to find the value
     * of a currency depending on which one is in
     * the currency/rate table.
     * @param from
     * @param to
     * @param rate
     */
    private void addToRates(final String from, final String to, final double rate) {
        if (this.rates.containsKey(from)) {
            double mainToFrom = this.rates.get(from);
            this.rates.put(to, mainToFrom * rate);
        } else if (this.rates.containsKey(to)) {
            double mainToTo = this.rates.get(to);
            this.rates.put(from, mainToTo / rate);
        }
    }

    /**
     * This method make the exchange table, all
     * the currency being calculated after the
     * main currency that is the "mainRate".
     * @param exchangeInputs
     */
    private void initExchange(final ExchangeInput[] exchangeInputs) {
        mainRate = exchangeInputs[0].getFrom();
        rates.put(exchangeInputs[0].getTo(), exchangeInputs[0].getRate());

        for (ExchangeInput exchangeInput : exchangeInputs) {
            addToRates(exchangeInput.getFrom(), exchangeInput.getTo(),
                    exchangeInput.getRate());
        }
    }

    /**
     * This method return the exchange rate
     * between two currencies.
     * @param from
     * @param to
     * @return
     */
    public double getRate(final String from, final String to) {
        if (from.equals(to)) {
            return 1;
        }

        if (from.equals(mainRate)) {
            return this.rates.get(to);
        } else if (to.equals(mainRate)) {
            return 1 / this.rates.get(from);
        }

        double fromRate = this.rates.get(from);
        double toRate = this.rates.get(to);

        return toRate / fromRate;
    }
}
