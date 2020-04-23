package com.example.myapplication.bean;

public class ChicangBean {


    /**
     * code : 200
     * message : 请求成功
     * data : {"id":"0c0487a9ee0c4fdf886feda5ef2e7bc1","userId":5,"symbolId":1,"openPrice":"6871.30000000","closePrice":"6767.23050000","stopProfit":"200.00000000","stopLoss":"100.00000000","fee":"0.10000000","leverage":"10.00000000","margin":"10.00000000","leverageAmount":"100.00000000","createTime":"2020-04-21 14:18:32","cancelTime":"2020-04-21 14:18:32","closeTime":"2020-04-21 15:34:55","doneTime":"2020-04-21 14:18:32","state":"success","close":"-1.00000000"}
     */

    private int code;
    private String message;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 0c0487a9ee0c4fdf886feda5ef2e7bc1
         * userId : 5
         * symbolId : 1
         * openPrice : 6871.30000000
         * closePrice : 6767.23050000
         * stopProfit : 200.00000000
         * stopLoss : 100.00000000
         * fee : 0.10000000
         * leverage : 10.00000000
         * margin : 10.00000000
         * leverageAmount : 100.00000000
         * createTime : 2020-04-21 14:18:32
         * cancelTime : 2020-04-21 14:18:32
         * closeTime : 2020-04-21 15:34:55
         * doneTime : 2020-04-21 14:18:32
         * state : success
         * close : -1.00000000
         */

        private String id;
        private int userId;
        private int symbolId;
        private double openPrice;
        private String closePrice;
        private String stopProfit;
        private String stopLoss;
        private double fee;
        private int leverage;
        private double margin;
        private String leverageAmount;
        private String createTime;
        private String cancelTime;
        private String closeTime;
        private String doneTime;
        private String state;
        private String close;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getSymbolId() {
            return symbolId;
        }

        public void setSymbolId(int symbolId) {
            this.symbolId = symbolId;
        }

        public double getOpenPrice() {
            return openPrice;
        }

        public void setOpenPrice(double openPrice) {
            this.openPrice = openPrice;
        }

        public String getClosePrice() {
            return closePrice;
        }

        public void setClosePrice(String closePrice) {
            this.closePrice = closePrice;
        }

        public String getStopProfit() {
            return stopProfit;
        }

        public void setStopProfit(String stopProfit) {
            this.stopProfit = stopProfit;
        }

        public String getStopLoss() {
            return stopLoss;
        }

        public void setStopLoss(String stopLoss) {
            this.stopLoss = stopLoss;
        }

        public double getFee() {
            return fee;
        }

        public void setFee(double fee) {
            this.fee = fee;
        }

        public int getLeverage() {
            return leverage;
        }

        public void setLeverage(int leverage) {
            this.leverage = leverage;
        }

        public double getMargin() {
            return margin;
        }

        public void setMargin(double margin) {
            this.margin = margin;
        }

        public String getLeverageAmount() {
            return leverageAmount;
        }

        public void setLeverageAmount(String leverageAmount) {
            this.leverageAmount = leverageAmount;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getCancelTime() {
            return cancelTime;
        }

        public void setCancelTime(String cancelTime) {
            this.cancelTime = cancelTime;
        }

        public String getCloseTime() {
            return closeTime;
        }

        public void setCloseTime(String closeTime) {
            this.closeTime = closeTime;
        }

        public String getDoneTime() {
            return doneTime;
        }

        public void setDoneTime(String doneTime) {
            this.doneTime = doneTime;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getClose() {
            return close;
        }

        public void setClose(String close) {
            this.close = close;
        }
    }
}
