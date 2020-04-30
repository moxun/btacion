package com.example.myapplication.bean;

import java.util.List;

public class JsonBean {

    /**
     * op : subscribe
     * args : ["swap/candle60s:BTC-USD-SWAP"]
     */

    private String op;
    private List<String> args;

    public JsonBean(String op, List<String> args) {
        this.op = op;
        this.args = args;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public List<String> getArgs() {
        return args;
    }

    public void setArgs(List<String> args) {
        this.args = args;
    }
}
