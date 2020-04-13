package com.example.myapplication.kline;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class KlineBean {

    private int code;
    private String msg;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1571970360
         * period : 1min
         * base-currency : BTC
         * quote-currency : USDT
         * open : 7445.56
         * close : 7442.43
         * high : 7447.01
         * low : 7441.29
         * vol : 32215.73192728
         * amount : 4.327329077145359
         * time : 1571970360000
         * volume : 4.327329077145359
         */

        private int id;
        private String period;
        @SerializedName("base-currency")
        private String basecurrency;
        @SerializedName("quote-currency")
        private String quotecurrency;
        private double open;
        private double close;
        private double high;
        private double low;
        private double vol;
        private double amount;
        private long time;
        private double volume;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPeriod() {
            return period;
        }

        public void setPeriod(String period) {
            this.period = period;
        }

        public String getBasecurrency() {
            return basecurrency;
        }

        public void setBasecurrency(String basecurrency) {
            this.basecurrency = basecurrency;
        }

        public String getQuotecurrency() {
            return quotecurrency;
        }

        public void setQuotecurrency(String quotecurrency) {
            this.quotecurrency = quotecurrency;
        }

        public double getOpen() {
            return open;
        }

        public void setOpen(double open) {
            this.open = open;
        }

        public double getClose() {
            return close;
        }

        public void setClose(double close) {
            this.close = close;
        }

        public double getHigh() {
            return high;
        }

        public void setHigh(double high) {
            this.high = high;
        }

        public double getLow() {
            return low;
        }

        public void setLow(double low) {
            this.low = low;
        }

        public double getVol() {
            return vol;
        }

        public void setVol(double vol) {
            this.vol = vol;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public double getVolume() {
            return volume;
        }

        public void setVolume(double volume) {
            this.volume = volume;
        }
    }
}
