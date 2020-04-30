package com.example.myapplication.bean;

import java.util.List;

public class TickerinfoBean {


    /**
     * code : 200
     * message : 请求成功
     * data : [{"instrumentId":"AAC-BTC","last":"0.00000031","lastQty":"13300.00000000","bestAsk":"0.00000031","bestAskSize":"302.17000000","bestBid":"0.00000031","bestBidSize":"55.71000000","open24H":"0.00000030","high24H":"0.00000031","low24H":"0.00000030","baseVolume24H":"104130305.02000000","quoteVolume24H":"31.55000000"},{"instrumentId":"BLOC-USDT","last":"0.00091000","lastQty":"0.00000000","bestAsk":"0.00091000","bestAskSize":"997352.28630000","bestBid":"0.00090000","bestBidSize":"35715.67410000","open24H":"0.00080000","high24H":"0.00093000","low24H":"0.00079000","baseVolume24H":"130189733.54370000","quoteVolume24H":"111016.93490000"}]
     */

    private int code;
    private String message;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * instrumentId : AAC-BTC
         * last : 0.00000031
         * lastQty : 13300.00000000
         * bestAsk : 0.00000031
         * bestAskSize : 302.17000000
         * bestBid : 0.00000031
         * bestBidSize : 55.71000000
         * open24H : 0.00000030
         * high24H : 0.00000031
         * low24H : 0.00000030
         * baseVolume24H : 104130305.02000000
         * quoteVolume24H : 31.55000000
         */

        private String instrumentId;
        private float last;
        private String lastQty;
        private String bestAsk;
        private String bestAskSize;
        private String bestBid;
        private String bestBidSize;
        private float open24H;
        private String high24H;
        private String low24H;
        private String baseVolume24H;
        private String quoteVolume24H;

        public String getInstrumentId() {
            return instrumentId;
        }

        public void setInstrumentId(String instrumentId) {
            this.instrumentId = instrumentId;
        }

        public float getLast() {
            return last;
        }

        public void setLast(float last) {
            this.last = last;
        }

        public String getLastQty() {
            return lastQty;
        }

        public void setLastQty(String lastQty) {
            this.lastQty = lastQty;
        }

        public String getBestAsk() {
            return bestAsk;
        }

        public void setBestAsk(String bestAsk) {
            this.bestAsk = bestAsk;
        }

        public String getBestAskSize() {
            return bestAskSize;
        }

        public void setBestAskSize(String bestAskSize) {
            this.bestAskSize = bestAskSize;
        }

        public String getBestBid() {
            return bestBid;
        }

        public void setBestBid(String bestBid) {
            this.bestBid = bestBid;
        }

        public String getBestBidSize() {
            return bestBidSize;
        }

        public void setBestBidSize(String bestBidSize) {
            this.bestBidSize = bestBidSize;
        }

        public float getOpen24H() {
            return open24H;
        }

        public void setOpen24H(float open24H) {
            this.open24H = open24H;
        }

        public String getHigh24H() {
            return high24H;
        }

        public void setHigh24H(String high24H) {
            this.high24H = high24H;
        }

        public String getLow24H() {
            return low24H;
        }

        public void setLow24H(String low24H) {
            this.low24H = low24H;
        }

        public String getBaseVolume24H() {
            return baseVolume24H;
        }

        public void setBaseVolume24H(String baseVolume24H) {
            this.baseVolume24H = baseVolume24H;
        }

        public String getQuoteVolume24H() {
            return quoteVolume24H;
        }

        public void setQuoteVolume24H(String quoteVolume24H) {
            this.quoteVolume24H = quoteVolume24H;
        }
    }
}
