package org.poo.Implementation;

import org.poo.fileio.ExchangeInput;

import java.util.HashMap;

public class Exchange {
    private String mainRate; // primul exchange "from"!!!!!!!!!!!!!!!!!!!
    private HashMap<String, Double> rates;


    public Exchange(ExchangeInput[] exchangeInputs) {
        rates = new HashMap<String, Double>();
        this.initExchange(exchangeInputs);
    }

    private void addToRates(String from, String to, double rate) {
        if(this.rates.containsKey(from)) {
            double mainToFrom = this.rates.get(from);
            this.rates.put(to, mainToFrom * rate);
        } else if(this.rates.containsKey(to)) {
            double mainToTo = this.rates.get(to);
            this.rates.put(from, mainToTo / rate);
        }
    }

    private void initExchange(ExchangeInput[] exchangeInputs) {
        mainRate = exchangeInputs[0].getFrom();
        rates.put(exchangeInputs[0].getTo(), exchangeInputs[0].getRate());

        for (int i = 0; i < exchangeInputs.length; i++) {
            addToRates(exchangeInputs[i].getFrom(), exchangeInputs[i].getTo(), exchangeInputs[i].getRate());
        }
    }

    public double getRate(String from, String to) {
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
