package com.example.myapplication.bean;

import java.util.List;

public class SymbolBean {

    /**
     * code : 200
     * message : 请求成功
     * data : [{"id":1,"symbol":"btc_usdt","fromCoinId":2,"toCoinId":1}]
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
         * id : 1
         * symbol : btc_usdt
         * fromCoinId : 2
         * toCoinId : 1
         */

        private int id;
        private String symbol;
        private int fromCoinId;
        private int toCoinId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public int getFromCoinId() {
            return fromCoinId;
        }

        public void setFromCoinId(int fromCoinId) {
            this.fromCoinId = fromCoinId;
        }

        public int getToCoinId() {
            return toCoinId;
        }

        public void setToCoinId(int toCoinId) {
            this.toCoinId = toCoinId;
        }
    }
}
