package com.example.myapplication.bean;

public class ShimingBean {


    /**
     * code : 200
     * message : 请求成功
     * data : {"userId":4,"name":"xiaolaji","idcard":"142622199904251019","positivePhoto":"","backPhoto":"","state":"CERTIFICATION","addTime":"2020-04-16 16:57:29","reason":""}
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
         * userId : 4
         * name : xiaolaji
         * idcard : 142622199904251019
         * positivePhoto :
         * backPhoto :
         * state : CERTIFICATION
         * addTime : 2020-04-16 16:57:29
         * reason :
         */

        private int userId;
        private String name;
        private String idcard;
        private String positivePhoto;
        private String backPhoto;
        private String state;
        private String addTime;
        private String reason;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public String getPositivePhoto() {
            return positivePhoto;
        }

        public void setPositivePhoto(String positivePhoto) {
            this.positivePhoto = positivePhoto;
        }

        public String getBackPhoto() {
            return backPhoto;
        }

        public void setBackPhoto(String backPhoto) {
            this.backPhoto = backPhoto;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }
    }
}
