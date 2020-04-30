package com.example.myapplication.bean;

import java.util.List;

public class CoinsListBean {


    /**
     * code : 200
     * message : 请求成功
     * data : [{"coinName":"USDT","minRecharge":"100.00000000","minWithdraw":"100.00000000","maxWithdraw":"10000.00000000","withdrawFree":"1.00000000","contractAddress":"1","coinPrice":"1.00000000","netWork":"ETH","id":1,"coinImg":"5f7db74648d948488b1011148bacf0a9","closeRecharge":"N","closeWithdraw":"N"},{"coinName":"EOS","minRecharge":"123.00000000","minWithdraw":"123.00000000","maxWithdraw":"123.00000000","withdrawFree":"123.00000000","contractAddress":"123","coinPrice":"123.00000000","netWork":"EOS","id":2,"coinImg":"91c6ecceca1b4ef6a877b8ec2ebf56c6","closeRecharge":"N","closeWithdraw":"N"},{"coinName":"ETH","minRecharge":"123.00000000","minWithdraw":"123.00000000","maxWithdraw":"213.00000000","withdrawFree":"3323.00000000","contractAddress":"123","coinPrice":"123.00000000","netWork":"EOS","id":3,"coinImg":"541164443f8f42cab7f676664fbb9c1f","closeRecharge":"N","closeWithdraw":"N"}]
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
         * coinName : USDT
         * minRecharge : 100.00000000
         * minWithdraw : 100.00000000
         * maxWithdraw : 10000.00000000
         * withdrawFree : 1.00000000
         * contractAddress : 1
         * coinPrice : 1.00000000
         * netWork : ETH
         * id : 1
         * coinImg : 5f7db74648d948488b1011148bacf0a9
         * closeRecharge : N
         * closeWithdraw : N
         */

        private String coinName;
        private String minRecharge;
        private String minWithdraw;
        private String maxWithdraw;
        private double withdrawFree;
        private String contractAddress;
        private String coinPrice;
        private String netWork;
        private int id;
        private String coinImg;
        private String closeRecharge;
        private String closeWithdraw;

        public String getCoinName() {
            return coinName;
        }

        public void setCoinName(String coinName) {
            this.coinName = coinName;
        }

        public String getMinRecharge() {
            return minRecharge;
        }

        public void setMinRecharge(String minRecharge) {
            this.minRecharge = minRecharge;
        }

        public String getMinWithdraw() {
            return minWithdraw;
        }

        public void setMinWithdraw(String minWithdraw) {
            this.minWithdraw = minWithdraw;
        }

        public String getMaxWithdraw() {
            return maxWithdraw;
        }

        public void setMaxWithdraw(String maxWithdraw) {
            this.maxWithdraw = maxWithdraw;
        }

        public double getWithdrawFree() {
            return withdrawFree;
        }

        public void setWithdrawFree(double withdrawFree) {
            this.withdrawFree = withdrawFree;
        }

        public String getContractAddress() {
            return contractAddress;
        }

        public void setContractAddress(String contractAddress) {
            this.contractAddress = contractAddress;
        }

        public String getCoinPrice() {
            return coinPrice;
        }

        public void setCoinPrice(String coinPrice) {
            this.coinPrice = coinPrice;
        }

        public String getNetWork() {
            return netWork;
        }

        public void setNetWork(String netWork) {
            this.netWork = netWork;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCoinImg() {
            return coinImg;
        }

        public void setCoinImg(String coinImg) {
            this.coinImg = coinImg;
        }

        public String getCloseRecharge() {
            return closeRecharge;
        }

        public void setCloseRecharge(String closeRecharge) {
            this.closeRecharge = closeRecharge;
        }

        public String getCloseWithdraw() {
            return closeWithdraw;
        }

        public void setCloseWithdraw(String closeWithdraw) {
            this.closeWithdraw = closeWithdraw;
        }
    }
}
