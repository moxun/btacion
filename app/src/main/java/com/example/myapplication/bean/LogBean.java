package com.example.myapplication.bean;

import com.google.gson.annotations.SerializedName;

public class LogBean {


    /**
     * code : 200
     * message : 请求成功
     * data : {"1":"充值","2":"提现","3":"转账","4":"提现驳回","5":"l.log.type.order.submit","6":"l.log.type.order.refund","7":"l.log.type.order.income","8":"l.log.type.order.point.submit","9":"l.log.type.order.point.refund","10":"l.log.type.order.point.income"}
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
         * 1 : 充值
         * 2 : 提现
         * 3 : 转账
         * 4 : 提现驳回
         * 5 : l.log.type.order.submit
         * 6 : l.log.type.order.refund
         * 7 : l.log.type.order.income
         * 8 : l.log.type.order.point.submit
         * 9 : l.log.type.order.point.refund
         * 10 : l.log.type.order.point.income
         */

        @SerializedName("1")
        private String _$1;
        @SerializedName("2")
        private String _$2;
        @SerializedName("3")
        private String _$3;
        @SerializedName("4")
        private String _$4;
        @SerializedName("5")
        private String _$5;
        @SerializedName("6")
        private String _$6;
        @SerializedName("7")
        private String _$7;
        @SerializedName("8")
        private String _$8;
        @SerializedName("9")
        private String _$9;
        @SerializedName("10")
        private String _$10;

        public String get_$1() {
            return _$1;
        }

        public void set_$1(String _$1) {
            this._$1 = _$1;
        }

        public String get_$2() {
            return _$2;
        }

        public void set_$2(String _$2) {
            this._$2 = _$2;
        }

        public String get_$3() {
            return _$3;
        }

        public void set_$3(String _$3) {
            this._$3 = _$3;
        }

        public String get_$4() {
            return _$4;
        }

        public void set_$4(String _$4) {
            this._$4 = _$4;
        }

        public String get_$5() {
            return _$5;
        }

        public void set_$5(String _$5) {
            this._$5 = _$5;
        }

        public String get_$6() {
            return _$6;
        }

        public void set_$6(String _$6) {
            this._$6 = _$6;
        }

        public String get_$7() {
            return _$7;
        }

        public void set_$7(String _$7) {
            this._$7 = _$7;
        }

        public String get_$8() {
            return _$8;
        }

        public void set_$8(String _$8) {
            this._$8 = _$8;
        }

        public String get_$9() {
            return _$9;
        }

        public void set_$9(String _$9) {
            this._$9 = _$9;
        }

        public String get_$10() {
            return _$10;
        }

        public void set_$10(String _$10) {
            this._$10 = _$10;
        }
    }
}
