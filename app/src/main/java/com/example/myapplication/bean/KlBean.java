package com.example.myapplication.bean;

import java.util.List;

public class KlBean {


    /**
     * table : swap/candle60s
     * data : [{"candle":["2020-04-27T12:06:00.000Z","7713.5","7713.5","7707.5","7708.8","1971","25.5607"],"instrument_id":"BTC-USD-SWAP"}]
     */

    private String table;
    private List<DataBean> data;
    private String event;
    private String channel;

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * candle : ["2020-04-27T12:06:00.000Z","7713.5","7713.5","7707.5","7708.8","1971","25.5607"]
         * instrument_id : BTC-USD-SWAP
         */

        //开始时间    开盘价格（open）   最高价格（high）   最低价格（low）  收盘价格（close）  交易量（volume） 	按币折算的交易量（currency_volume）
        private String instrument_id;//	合约BTC-USD-SWAP
        private List<String> candle;

        public String getInstrument_id() {
            return instrument_id;
        }

        public void setInstrument_id(String instrument_id) {
            this.instrument_id = instrument_id;
        }

        public List<String> getCandle() {
            return candle;
        }

        public void setCandle(List<String> candle) {
            this.candle = candle;
        }
    }
}
