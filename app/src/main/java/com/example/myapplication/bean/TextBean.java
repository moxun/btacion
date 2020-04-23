package com.example.myapplication.bean;

import java.util.List;

public class TextBean {

    /**
     * code : 200
     * message : 请求成功
     * data : {"pageNum":1,"pageSize":20,"totalPage":1,"total":3,"list":[{"id":24305,"addTime":"2020-04-14 23:55:04","userId":1,"coinId":4,"amount":"1.00000000","type":12,"lastBalance":"9930.21000000","oldBalance":"9929.21000000","actualAmount":"1.00000000","producer":"-1","transferUserId":null,"receivablesUserId":null,"fromCoinId":1,"winning":null},{"id":22068,"addTime":"2020-04-12 01:42:22","userId":1,"coinId":4,"amount":"12.00000000","type":12,"lastBalance":"10001.75000000","oldBalance":"9989.75000000","actualAmount":"12.00000000","producer":"-1","transferUserId":null,"receivablesUserId":null,"fromCoinId":1,"winning":null},{"id":22066,"addTime":"2020-04-12 01:38:24","userId":1,"coinId":4,"amount":"10.00000000","type":12,"lastBalance":"9989.75000000","oldBalance":"9979.75000000","actualAmount":"10.00000000","producer":"-1","transferUserId":null,"receivablesUserId":null,"fromCoinId":1,"winning":null}]}
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
         * pageSize : 20
         * totalPage : 1
         * total : 3
         * list : [{"id":24305,"addTime":"2020-04-14 23:55:04","userId":1,"coinId":4,"amount":"1.00000000","type":12,"lastBalance":"9930.21000000","oldBalance":"9929.21000000","actualAmount":"1.00000000","producer":"-1","transferUserId":null,"receivablesUserId":null,"fromCoinId":1,"winning":null},{"id":22068,"addTime":"2020-04-12 01:42:22","userId":1,"coinId":4,"amount":"12.00000000","type":12,"lastBalance":"10001.75000000","oldBalance":"9989.75000000","actualAmount":"12.00000000","producer":"-1","transferUserId":null,"receivablesUserId":null,"fromCoinId":1,"winning":null},{"id":22066,"addTime":"2020-04-12 01:38:24","userId":1,"coinId":4,"amount":"10.00000000","type":12,"lastBalance":"9989.75000000","oldBalance":"9979.75000000","actualAmount":"10.00000000","producer":"-1","transferUserId":null,"receivablesUserId":null,"fromCoinId":1,"winning":null}]
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
             * id : 24305
             * addTime : 2020-04-14 23:55:04
             * userId : 1
             * coinId : 4
             * amount : 1.00000000
             * type : 12
             * lastBalance : 9930.21000000
             * oldBalance : 9929.21000000
             * actualAmount : 1.00000000
             * producer : -1
             * transferUserId : null
             * receivablesUserId : null
             * fromCoinId : 1
             * winning : null
             */

            private int id;
            private String addTime;
            private int userId;
            private int coinId;
            private String amount;
            private int type;
            private String lastBalance;
            private String oldBalance;
            private String actualAmount;
            private String producer;
            private Object transferUserId;
            private Object receivablesUserId;
            private int fromCoinId;
            private Object winning;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getAddTime() {
                return addTime;
            }

            public void setAddTime(String addTime) {
                this.addTime = addTime;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public int getCoinId() {
                return coinId;
            }

            public void setCoinId(int coinId) {
                this.coinId = coinId;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getLastBalance() {
                return lastBalance;
            }

            public void setLastBalance(String lastBalance) {
                this.lastBalance = lastBalance;
            }

            public String getOldBalance() {
                return oldBalance;
            }

            public void setOldBalance(String oldBalance) {
                this.oldBalance = oldBalance;
            }

            public String getActualAmount() {
                return actualAmount;
            }

            public void setActualAmount(String actualAmount) {
                this.actualAmount = actualAmount;
            }

            public String getProducer() {
                return producer;
            }

            public void setProducer(String producer) {
                this.producer = producer;
            }

            public Object getTransferUserId() {
                return transferUserId;
            }

            public void setTransferUserId(Object transferUserId) {
                this.transferUserId = transferUserId;
            }

            public Object getReceivablesUserId() {
                return receivablesUserId;
            }

            public void setReceivablesUserId(Object receivablesUserId) {
                this.receivablesUserId = receivablesUserId;
            }

            public int getFromCoinId() {
                return fromCoinId;
            }

            public void setFromCoinId(int fromCoinId) {
                this.fromCoinId = fromCoinId;
            }

            public Object getWinning() {
                return winning;
            }

            public void setWinning(Object winning) {
                this.winning = winning;
            }
        }
    }
}
