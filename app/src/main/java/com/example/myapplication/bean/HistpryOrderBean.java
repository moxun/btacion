package com.example.myapplication.bean;

import java.util.List;

public class HistpryOrderBean {

    /**
     * code : 200
     * message : 请求成功
     * data : {"pageNum":1,"pageSize":10,"totalPage":1,"total":4,"list":[{"id":"839eda6a6b7c4b75a2c0199f0c1a1a87","price":"6820.00000000","amount":"7.27740000","createTime":"2020-04-21 22:54:09","cancelTime":"2020-04-21 22:54:09","symbolId":1,"userId":5,"orderState":"filled","orderContractType":"perpetual","lever":100,"leverAmount":"100.00000000","fee":"0.47740000"},{"id":"96101afa8faa4567acedbfa5a11b7ab3","price":"6000.00000000","amount":"6.42000000","createTime":"2020-04-21 22:42:33","cancelTime":"2020-04-21 22:42:33","symbolId":1,"userId":5,"orderState":"filled","orderContractType":"perpetual","lever":100,"leverAmount":"100.00000000","fee":"0.42000000"},{"id":"a348ee01849443a29c2fa1537699c375","price":"6840.00000000","amount":"7.27880000","createTime":"2020-04-21 22:49:19","cancelTime":"2020-04-21 22:49:19","symbolId":1,"userId":5,"orderState":"filled","orderContractType":"perpetual","lever":100,"leverAmount":"100.00000000","fee":"0.47880000"},{"id":"ccc840687e214a4bbd99dc1c534dab31","price":"6820.00000000","amount":"7.27880000","createTime":"2020-04-21 22:52:06","cancelTime":"2020-04-21 22:52:06","symbolId":1,"userId":5,"orderState":"filled","orderContractType":"perpetual","lever":100,"leverAmount":"100.00000000","fee":"0.47880000"}]}
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
         * pageNum : 1
         * pageSize : 10
         * totalPage : 1
         * total : 4
         * list : [{"id":"839eda6a6b7c4b75a2c0199f0c1a1a87","price":"6820.00000000","amount":"7.27740000","createTime":"2020-04-21 22:54:09","cancelTime":"2020-04-21 22:54:09","symbolId":1,"userId":5,"orderState":"filled","orderContractType":"perpetual","lever":100,"leverAmount":"100.00000000","fee":"0.47740000"},{"id":"96101afa8faa4567acedbfa5a11b7ab3","price":"6000.00000000","amount":"6.42000000","createTime":"2020-04-21 22:42:33","cancelTime":"2020-04-21 22:42:33","symbolId":1,"userId":5,"orderState":"filled","orderContractType":"perpetual","lever":100,"leverAmount":"100.00000000","fee":"0.42000000"},{"id":"a348ee01849443a29c2fa1537699c375","price":"6840.00000000","amount":"7.27880000","createTime":"2020-04-21 22:49:19","cancelTime":"2020-04-21 22:49:19","symbolId":1,"userId":5,"orderState":"filled","orderContractType":"perpetual","lever":100,"leverAmount":"100.00000000","fee":"0.47880000"},{"id":"ccc840687e214a4bbd99dc1c534dab31","price":"6820.00000000","amount":"7.27880000","createTime":"2020-04-21 22:52:06","cancelTime":"2020-04-21 22:52:06","symbolId":1,"userId":5,"orderState":"filled","orderContractType":"perpetual","lever":100,"leverAmount":"100.00000000","fee":"0.47880000"}]
         */

        private int pageNum;
        private int pageSize;
        private int totalPage;
        private int total;
        private List<ListBean> list;

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 839eda6a6b7c4b75a2c0199f0c1a1a87
             * price : 6820.00000000
             * amount : 7.27740000
             * createTime : 2020-04-21 22:54:09
             * cancelTime : 2020-04-21 22:54:09
             * symbolId : 1
             * userId : 5
             * orderState : filled
             * orderContractType : perpetual
             * lever : 100
             * leverAmount : 100.00000000
             * fee : 0.47740000
             */

            private String id;
            private String price;
            private double amount;
            private String createTime;
            private String cancelTime;
            private int symbolId;
            private int userId;
            private String orderState;
            private String orderContractType;
            private int lever;
            private String leverAmount;
            private String fee;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public double getAmount() {
                return amount;
            }

            public void setAmount(double amount) {
                this.amount = amount;
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

            public int getSymbolId() {
                return symbolId;
            }

            public void setSymbolId(int symbolId) {
                this.symbolId = symbolId;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getOrderState() {
                return orderState;
            }

            public void setOrderState(String orderState) {
                this.orderState = orderState;
            }

            public String getOrderContractType() {
                return orderContractType;
            }

            public void setOrderContractType(String orderContractType) {
                this.orderContractType = orderContractType;
            }

            public int getLever() {
                return lever;
            }

            public void setLever(int lever) {
                this.lever = lever;
            }

            public String getLeverAmount() {
                return leverAmount;
            }

            public void setLeverAmount(String leverAmount) {
                this.leverAmount = leverAmount;
            }

            public String getFee() {
                return fee;
            }

            public void setFee(String fee) {
                this.fee = fee;
            }
        }
    }
}
