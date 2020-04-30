package com.example.myapplication.bean;

import com.google.gson.annotations.SerializedName;

public class MyCangweiBean {


    /**
     * code : 200
     * message : 请求成功
     * data : {"id":3,"userId":5,"symbolId":1,"number":"100.00000000","openPrice":"7050.00000000","margin":"7.49350000","lever":100,"closePrice":"6944.25000000","contractType":"perpetual"}
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
         * id : 3
         * userId : 5
         * symbolId : 1
         * number : 100.00000000
         * openPrice : 7050.00000000
         * margin : 7.49350000
         * lever : 100
         * closePrice : 6944.25000000
         * contractType : perpetual
         */

        private String id;
        private int userId;
        private int symbolId;
        private double number;
        private double openPrice;
        private double margin;
        private int lever;
        private double closePrice;
        private String contractType;
        /**
         * id : 0c0487a9ee0c4fdf886feda5ef2e7bc1
         * stopProfit : 200.00000000
         * stopLoss : 100.00000000
         * fee : 0.10000000
         * leverage : 10.00000000
         * leverageAmount : 100.00000000
         * createTime : 2020-04-21 14:18:32
         * cancelTime : 2020-04-21 14:18:32
         * closeTime : 2020-04-21 15:34:55
         * doneTime : 2020-04-21 14:18:32
         * state : success
         * close : -1.00000000
         */


        private String stopProfit;
        private String stopLoss;
        private double fee;
        private int leverage;
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

        public double getNumber() {
            return number;
        }

        public void setNumber(double number) {
            this.number = number;
        }

        public double getOpenPrice() {
            return openPrice;
        }

        public void setOpenPrice(double openPrice) {
            this.openPrice = openPrice;
        }

        public double getMargin() {
            return margin;
        }

        public void setMargin(double margin) {
            this.margin = margin;
        }

        public int getLever() {
            return lever;
        }

        public void setLever(int lever) {
            this.lever = lever;
        }

        public double getClosePrice() {
            return closePrice;
        }

        public void setClosePrice(double closePrice) {
            this.closePrice = closePrice;
        }

        public String getContractType() {
            return contractType;
        }

        public void setContractType(String contractType) {
            this.contractType = contractType;
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
