package me.aflak.cryptodata.entities;

public class Crypto {
    private String name;
    private String symbol;
    private String currency;
    private double price;
    private double percentChangeDay;
    private double percentChangeWeek;

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getCurrency() {
        return currency;
    }

    public double getPrice() {
        return price;
    }

    public double getPercentChangeDay() {
        return percentChangeDay;
    }

    public double getPercentChangeWeek() {
        return percentChangeWeek;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPercentChangeDay(double percentChangeDay) {
        this.percentChangeDay = percentChangeDay;
    }

    public void setPercentChangeWeek(double percentChangeWeek) {
        this.percentChangeWeek = percentChangeWeek;
    }

    public Crypto(String name, String symbol, String currency, double price, double percentChangeDay, double percentChangeWeek) {
        this.name = name;
        this.symbol = symbol;
        this.currency = currency;
        this.price = price;
        this.percentChangeDay = percentChangeDay;
        this.percentChangeWeek = percentChangeWeek;


    }

    public static class Builder{
        private String name;
        private String symbol;
        private String currency;
        private double price;
        private double percentChangeDay;
        private double percentChangeWeek;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setSymbol(String symbol) {
            this.symbol = symbol;
            return this;
        }

        public Builder setCurrency(String currency) {
            this.currency = currency;
            return this;
        }

        public Builder setPrice(double price) {
            this.price = price;
            return this;
        }

        public Builder setPercentChangeDay(double percentChangeDay) {
            this.percentChangeDay = percentChangeDay;
            return this;
        }

        public Builder setPercentChangeWeek(double percentChangeWeek) {
            this.percentChangeWeek = percentChangeWeek;
            return this;
        }

        public Crypto build(){
            return new Crypto(name, symbol, currency, price, percentChangeDay, percentChangeWeek);
        }
    }
}
