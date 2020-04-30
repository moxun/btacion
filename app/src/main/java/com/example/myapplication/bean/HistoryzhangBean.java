package com.example.myapplication.bean;

import java.util.List;

public class HistoryzhangBean {


    /**
     * code : 200
     * message : 请求成功
     * data : {"pageNum":1,"pageSize":20,"totalPage":1,"total":11,"list":[{"id":71,"addTime":"2020-04-29 09:28:06","userId":5,"coinId":1,"amount":"-505.00000000","type":8,"lastBalance":"98864.00000000","oldBalance":"99369.00000000","actualAmount":"-505.00000000","producer":"966df79394f84c3fa4a2b93e9c08df57"},{"id":70,"addTime":"2020-04-27 23:20:51","userId":5,"coinId":1,"amount":"-505.00000000","type":8,"lastBalance":"99369.00000000","oldBalance":"99874.00000000","actualAmount":"-505.00000000","producer":"9d11e9e678fc4153887153df4c6893a3"},{"id":69,"addTime":"2020-04-27 23:15:00","userId":5,"coinId":1,"amount":"-505.00000000","type":8,"lastBalance":"99874.00000000","oldBalance":"100379.00000000","actualAmount":"-505.00000000","producer":"e99829f1625e425fb82ce76aa8701280"},{"id":68,"addTime":"2020-04-27 22:41:43","userId":5,"coinId":1,"amount":"100000.00000000","type":1,"lastBalance":"100379.00000000","oldBalance":"379.00000000","actualAmount":"100000.00000000","producer":"1"},{"id":67,"addTime":"2020-04-26 21:28:27","userId":5,"coinId":1,"amount":"-505.00000000","type":8,"lastBalance":"379.00000000","oldBalance":"884.00000000","actualAmount":"-505.00000000","producer":"0f2a6caa110342b49597ac569e27cb2f"},{"id":66,"addTime":"2020-04-26 21:04:10","userId":5,"coinId":1,"amount":"-101.00000000","type":8,"lastBalance":"884.00000000","oldBalance":"985.00000000","actualAmount":"-101.00000000","producer":"ce76d42c486c4b22be09a79eb06b57ae"},{"id":65,"addTime":"2020-04-26 15:41:53","userId":5,"coinId":1,"amount":"495.00000000","type":8,"lastBalance":"985.00000000","oldBalance":"490.00000000","actualAmount":"495.00000000","producer":"a725657a412041b78701abfccf6a499b"},{"id":64,"addTime":"2020-04-26 15:35:42","userId":5,"coinId":1,"amount":"-505.00000000","type":8,"lastBalance":"490.00000000","oldBalance":"995.00000000","actualAmount":"-505.00000000","producer":"0391db0fe05a4080b501186823e44957"},{"id":63,"addTime":"2020-04-26 15:16:04","userId":5,"coinId":1,"amount":"500.00000000","type":10,"lastBalance":"995.00000000","oldBalance":"495.00000000","actualAmount":"500.00000000","producer":"8f243d03f27f44f6ae5da06694759a22"},{"id":62,"addTime":"2020-04-26 15:16:03","userId":5,"coinId":1,"amount":"-505.00000000","type":8,"lastBalance":"495.00000000","oldBalance":"1000.00000000","actualAmount":"-505.00000000","producer":"8f243d03f27f44f6ae5da06694759a22"},{"id":17,"addTime":"2019-12-20 18:35:51","userId":5,"coinId":1,"amount":"1000.00000000","type":1,"lastBalance":"1000.00000000","oldBalance":"0.00000000","actualAmount":"1000.00000000","producer":"1"}]}
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
         * total : 11
         * list : [{"id":71,"addTime":"2020-04-29 09:28:06","userId":5,"coinId":1,"amount":"-505.00000000","type":8,"lastBalance":"98864.00000000","oldBalance":"99369.00000000","actualAmount":"-505.00000000","producer":"966df79394f84c3fa4a2b93e9c08df57"},{"id":70,"addTime":"2020-04-27 23:20:51","userId":5,"coinId":1,"amount":"-505.00000000","type":8,"lastBalance":"99369.00000000","oldBalance":"99874.00000000","actualAmount":"-505.00000000","producer":"9d11e9e678fc4153887153df4c6893a3"},{"id":69,"addTime":"2020-04-27 23:15:00","userId":5,"coinId":1,"amount":"-505.00000000","type":8,"lastBalance":"99874.00000000","oldBalance":"100379.00000000","actualAmount":"-505.00000000","producer":"e99829f1625e425fb82ce76aa8701280"},{"id":68,"addTime":"2020-04-27 22:41:43","userId":5,"coinId":1,"amount":"100000.00000000","type":1,"lastBalance":"100379.00000000","oldBalance":"379.00000000","actualAmount":"100000.00000000","producer":"1"},{"id":67,"addTime":"2020-04-26 21:28:27","userId":5,"coinId":1,"amount":"-505.00000000","type":8,"lastBalance":"379.00000000","oldBalance":"884.00000000","actualAmount":"-505.00000000","producer":"0f2a6caa110342b49597ac569e27cb2f"},{"id":66,"addTime":"2020-04-26 21:04:10","userId":5,"coinId":1,"amount":"-101.00000000","type":8,"lastBalance":"884.00000000","oldBalance":"985.00000000","actualAmount":"-101.00000000","producer":"ce76d42c486c4b22be09a79eb06b57ae"},{"id":65,"addTime":"2020-04-26 15:41:53","userId":5,"coinId":1,"amount":"495.00000000","type":8,"lastBalance":"985.00000000","oldBalance":"490.00000000","actualAmount":"495.00000000","producer":"a725657a412041b78701abfccf6a499b"},{"id":64,"addTime":"2020-04-26 15:35:42","userId":5,"coinId":1,"amount":"-505.00000000","type":8,"lastBalance":"490.00000000","oldBalance":"995.00000000","actualAmount":"-505.00000000","producer":"0391db0fe05a4080b501186823e44957"},{"id":63,"addTime":"2020-04-26 15:16:04","userId":5,"coinId":1,"amount":"500.00000000","type":10,"lastBalance":"995.00000000","oldBalance":"495.00000000","actualAmount":"500.00000000","producer":"8f243d03f27f44f6ae5da06694759a22"},{"id":62,"addTime":"2020-04-26 15:16:03","userId":5,"coinId":1,"amount":"-505.00000000","type":8,"lastBalance":"495.00000000","oldBalance":"1000.00000000","actualAmount":"-505.00000000","producer":"8f243d03f27f44f6ae5da06694759a22"},{"id":17,"addTime":"2019-12-20 18:35:51","userId":5,"coinId":1,"amount":"1000.00000000","type":1,"lastBalance":"1000.00000000","oldBalance":"0.00000000","actualAmount":"1000.00000000","producer":"1"}]
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
             * id : 71
             * addTime : 2020-04-29 09:28:06
             * userId : 5
             * coinId : 1
             * amount : -505.00000000
             * type : 8
             * lastBalance : 98864.00000000
             * oldBalance : 99369.00000000
             * actualAmount : -505.00000000
             * producer : 966df79394f84c3fa4a2b93e9c08df57
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
        }
    }
}
