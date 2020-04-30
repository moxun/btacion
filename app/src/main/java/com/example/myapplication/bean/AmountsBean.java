package com.example.myapplication.bean;

import java.util.List;

public class AmountsBean {


    /**
     * code : 200
     * message : 请求成功
     * data : [{"symbolId":1,"amount":"500.00000000","lever":[50]},{"symbolId":1,"amount":"100.00000000","lever":[50]},{"symbolId":1,"amount":"10000.00000000","lever":[50]},{"symbolId":1,"amount":"2000.00000000","lever":[50]},{"symbolId":1,"amount":"1000.00000000","lever":[50]},{"symbolId":1,"amount":"5000.00000000","lever":[50]}]
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
         * symbolId : 1
         * amount : 500.00000000
         * lever : [50]
         */

        private int symbolId;
        private String amount;
        private List<Integer> lever;

        public int getSymbolId() {
            return symbolId;
        }

        public void setSymbolId(int symbolId) {
            this.symbolId = symbolId;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public List<Integer> getLever() {
            return lever;
        }

        public void setLever(List<Integer> lever) {
            this.lever = lever;
        }
    }
}
