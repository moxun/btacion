package com.example.myapplication.bean;

public class TeamBean {

    /**
     * code : 200
     * message : 请求成功
     * data : {"totalAmount":"0.00000000","yesterdayAmount":"0.00000000","totalFee":"0.00000000","childSize":0}
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
         * totalAmount : 0.00000000
         * yesterdayAmount : 0.00000000
         * totalFee : 0.00000000
         * childSize : 0
         */

        private String totalAmount;
        private String yesterdayAmount;
        private String totalFee;
        private int childSize;

        public String getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(String totalAmount) {
            this.totalAmount = totalAmount;
        }

        public String getYesterdayAmount() {
            return yesterdayAmount;
        }

        public void setYesterdayAmount(String yesterdayAmount) {
            this.yesterdayAmount = yesterdayAmount;
        }

        public String getTotalFee() {
            return totalFee;
        }

        public void setTotalFee(String totalFee) {
            this.totalFee = totalFee;
        }

        public int getChildSize() {
            return childSize;
        }

        public void setChildSize(int childSize) {
            this.childSize = childSize;
        }
    }
}
