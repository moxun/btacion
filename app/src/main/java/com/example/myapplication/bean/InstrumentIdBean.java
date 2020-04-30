package com.example.myapplication.bean;

import java.util.List;

public class InstrumentIdBean {


    /**
     * code : 200
     * message : 请求成功
     * data : [{"instrumentId":"AAC-BTC"},{"instrumentId":"AAC-ETH"},{"instrumentId":"AAC-USDT"},{"instrumentId":"ABT-BTC"},{"instrumentId":"ABT-ETH"},{"instrumentId":"ABT-USDT"},{"instrumentId":"ACT-BTC"},{"instrumentId":"ACT-USDT"},{"instrumentId":"ADA-BTC"},{"instrumentId":"ADA-ETH"},{"instrumentId":"ADA-USDT"},{"instrumentId":"AE-BTC"},{"instrumentId":"AE-ETH"},{"instrumentId":"AE-OKB"},{"instrumentId":"AE-USDT"},{"instrumentId":"ALGO-BTC"},{"instrumentId":"ALGO-ETH"},{"instrumentId":"ALGO-USDK"},{"instrumentId":"ALGO-USDT"},{"instrumentId":"ALV-USDT"},{"instrumentId":"APM-BTC"},{"instrumentId":"APM-USDT"},{"instrumentId":"ARDR-BTC"},{"instrumentId":"ARK-BTC"},{"instrumentId":"ARK-USDT"},{"instrumentId":"AST-USDT"},{"instrumentId":"ATOM-BTC"},{"instrumentId":"ATOM-ETH"},{"instrumentId":"ATOM-USDT"},{"instrumentId":"BAT-BTC"},{"instrumentId":"BAT-USDT"},{"instrumentId":"BCD-BTC"},{"instrumentId":"BCD-USDT"},{"instrumentId":"BCH-BTC"},{"instrumentId":"BCH-USDC"},{"instrumentId":"BCH-USDK"},{"instrumentId":"BCH-USDT"},{"instrumentId":"BCX-BTC"},{"instrumentId":"BEC-USDT"},{"instrumentId":"BLOC-USDT"},{"instrumentId":"BNT-BTC"},{"instrumentId":"BNT-USDT"},{"instrumentId":"BSV-BTC"},{"instrumentId":"BSV-USDC"},{"instrumentId":"BSV-USDK"},{"instrumentId":"BSV-USDT"},{"instrumentId":"BTC-DAI"},{"instrumentId":"BTC-USDC"},{"instrumentId":"BTC-USDK"},{"instrumentId":"BTC-USDT"},{"instrumentId":"BTG-BTC"},{"instrumentId":"BTG-USDT"},{"instrumentId":"BTM-BTC"},{"instrumentId":"BTM-ETH"},{"instrumentId":"BTM-USDT"},{"instrumentId":"BTT-BTC"},{"instrumentId":"BTT-ETH"},{"instrumentId":"BTT-USDT"},{"instrumentId":"CHAT-BTC"},{"instrumentId":"CHAT-USDT"},{"instrumentId":"CMT-BTC"},{"instrumentId":"CMT-ETH"},{"instrumentId":"CMT-USDT"},{"instrumentId":"CRO-BTC"},{"instrumentId":"CRO-USDK"},{"instrumentId":"CRO-USDT"},{"instrumentId":"CTC-BTC"},{"instrumentId":"CTC-USDT"},{"instrumentId":"CTXC-BTC"},{"instrumentId":"CTXC-ETH"},{"instrumentId":"CTXC-USDT"},{"instrumentId":"CVC-BTC"},{"instrumentId":"CVC-ETH"},{"instrumentId":"CVC-USDT"},{"instrumentId":"CVT-BTC"},{"instrumentId":"CVT-USDT"},{"instrumentId":"DAI-USDT"},{"instrumentId":"DASH-BTC"},{"instrumentId":"DASH-ETH"},{"instrumentId":"DASH-OKB"},{"instrumentId":"DASH-USDT"},{"instrumentId":"DCR-BTC"},{"instrumentId":"DCR-ETH"},{"instrumentId":"DCR-USDT"},{"instrumentId":"DEP-USDK"},{"instrumentId":"DEP-USDT"},{"instrumentId":"DGB-BTC"},{"instrumentId":"DGB-USDT"},{"instrumentId":"DOGE-USDK"},{"instrumentId":"DOGE-USDT"},{"instrumentId":"EC-USDK"},{"instrumentId":"EC-USDT"},{"instrumentId":"EDO-BTC"},{"instrumentId":"EDO-USDT"},{"instrumentId":"EGT-BTC"},{"instrumentId":"EGT-ETH"},{"instrumentId":"EGT-OKB"},{"instrumentId":"EGT-USDT"},{"instrumentId":"ELF-BTC"},{"instrumentId":"ELF-ETH"},{"instrumentId":"ELF-USDT"},{"instrumentId":"EM-USDK"},{"instrumentId":"EM-USDT"},{"instrumentId":"EOS-BTC"},{"instrumentId":"EOS-ETH"},{"instrumentId":"EOS-OKB"},{"instrumentId":"EOS-USDC"},{"instrumentId":"EOS-USDK"},{"instrumentId":"EOS-USDT"},{"instrumentId":"ETC-BTC"},{"instrumentId":"ETC-ETH"},{"instrumentId":"ETC-OKB"},{"instrumentId":"ETC-USDC"},{"instrumentId":"ETC-USDK"},{"instrumentId":"ETC-USDT"},{"instrumentId":"ETH-BTC"},{"instrumentId":"ETH-DAI"},{"instrumentId":"ETH-USDC"},{"instrumentId":"ETH-USDK"},{"instrumentId":"ETH-USDT"},{"instrumentId":"ETM-USDT"},{"instrumentId":"FAIR-ETH"},{"instrumentId":"FAIR-USDT"},{"instrumentId":"FSN-USDK"},{"instrumentId":"FSN-USDT"},{"instrumentId":"FTM-USDK"},{"instrumentId":"FTM-USDT"},{"instrumentId":"FUN-BTC"},{"instrumentId":"GAS-BTC"},{"instrumentId":"GAS-ETH"},{"instrumentId":"GAS-USDT"},{"instrumentId":"GNT-BTC"},{"instrumentId":"GNT-USDT"},{"instrumentId":"GNX-BTC"},{"instrumentId":"GNX-ETH"},{"instrumentId":"GTO-BTC"},{"instrumentId":"GTO-ETH"},{"instrumentId":"GTO-USDT"},{"instrumentId":"GUSD-BTC"},{"instrumentId":"GUSD-USDT"},{"instrumentId":"HBAR-BTC"},{"instrumentId":"HBAR-USDK"},{"instrumentId":"HBAR-USDT"},{"instrumentId":"HC-BTC"},{"instrumentId":"HC-ETH"},{"instrumentId":"HC-USDT"},{"instrumentId":"HDAO-USDK"},{"instrumentId":"HDAO-USDT"},{"instrumentId":"HYC-BTC"},{"instrumentId":"HYC-ETH"},{"instrumentId":"HYC-USDT"},{"instrumentId":"ICX-BTC"},{"instrumentId":"ICX-USDT"},{"instrumentId":"INT-BTC"},{"instrumentId":"INT-ETH"},{"instrumentId":"INT-USDT"},{"instrumentId":"IOST-BTC"},{"instrumentId":"IOST-ETH"},{"instrumentId":"IOST-USDT"},{"instrumentId":"IOTA-BTC"},{"instrumentId":"IOTA-ETH"},{"instrumentId":"IOTA-OKB"},{"instrumentId":"IOTA-USDT"},{"instrumentId":"ITC-BTC"},{"instrumentId":"ITC-USDT"},{"instrumentId":"KAN-BTC"},{"instrumentId":"KAN-ETH"},{"instrumentId":"KAN-USDT"},{"instrumentId":"KCASH-BTC"},{"instrumentId":"KCASH-ETH"},{"instrumentId":"KCASH-USDT"},{"instrumentId":"KNC-BTC"},{"instrumentId":"KNC-USDT"},{"instrumentId":"LAMB-USDK"},{"instrumentId":"LAMB-USDT"},{"instrumentId":"LBA-BTC"},{"instrumentId":"LBA-USDT"},{"instrumentId":"LEO-BTC"},{"instrumentId":"LEO-ETH"},{"instrumentId":"LEO-USDK"},{"instrumentId":"LEO-USDT"},{"instrumentId":"LET-BTC"},{"instrumentId":"LET-USDT"},{"instrumentId":"LINK-BTC"},{"instrumentId":"LINK-ETH"},{"instrumentId":"LINK-USDT"},{"instrumentId":"LRC-BTC"},{"instrumentId":"LRC-ETH"},{"instrumentId":"LRC-USDT"},{"instrumentId":"LSK-BTC"},{"instrumentId":"LSK-USDT"},{"instrumentId":"LTC-BTC"},{"instrumentId":"LTC-ETH"},{"instrumentId":"LTC-OKB"},{"instrumentId":"LTC-USDC"},{"instrumentId":"LTC-USDK"},{"instrumentId":"LTC-USDT"},{"instrumentId":"MANA-BTC"},{"instrumentId":"MANA-ETH"},{"instrumentId":"MANA-USDT"},{"instrumentId":"MCO-BTC"},{"instrumentId":"MCO-ETH"},{"instrumentId":"MCO-USDT"},{"instrumentId":"MDA-USDT"},{"instrumentId":"MDT-BTC"},{"instrumentId":"MDT-ETH"},{"instrumentId":"MDT-USDT"},{"instrumentId":"MITH-BTC"},{"instrumentId":"MITH-ETH"},{"instrumentId":"MITH-USDT"},{"instrumentId":"MKR-BTC"},{"instrumentId":"MKR-ETH"},{"instrumentId":"MKR-USDT"},{"instrumentId":"MOF-BTC"},{"instrumentId":"MOF-ETH"},{"instrumentId":"MOF-USDT"},{"instrumentId":"NANO-BTC"},{"instrumentId":"NANO-ETH"},{"instrumentId":"NANO-USDT"},{"instrumentId":"NAS-BTC"},{"instrumentId":"NAS-ETH"},{"instrumentId":"NAS-USDT"},{"instrumentId":"NEO-BTC"},{"instrumentId":"NEO-ETH"},{"instrumentId":"NEO-OKB"},{"instrumentId":"NEO-USDT"},{"instrumentId":"NULS-BTC"},{"instrumentId":"NULS-ETH"},{"instrumentId":"NULS-USDT"},{"instrumentId":"OKB-BTC"},{"instrumentId":"OKB-ETH"},{"instrumentId":"OKB-USDC"},{"instrumentId":"OKB-USDK"},{"instrumentId":"OKB-USDT"},{"instrumentId":"OMG-BTC"},{"instrumentId":"OMG-ETH"},{"instrumentId":"OMG-USDT"},{"instrumentId":"ONT-BTC"},{"instrumentId":"ONT-ETH"},{"instrumentId":"ONT-USDT"},{"instrumentId":"ORBS-USDK"},{"instrumentId":"ORBS-USDT"},{"instrumentId":"ORS-BTC"},{"instrumentId":"ORS-ETH"},{"instrumentId":"ORS-USDT"},{"instrumentId":"OXT-BTC"},{"instrumentId":"OXT-USDT"},{"instrumentId":"PAX-BTC"},{"instrumentId":"PAX-USDT"},{"instrumentId":"PAY-BTC"},{"instrumentId":"PAY-USDT"},{"instrumentId":"PLG-USDK"},{"instrumentId":"PLG-USDT"},{"instrumentId":"PMA-BTC"},{"instrumentId":"PMA-USDK"},{"instrumentId":"PPT-USDT"},{"instrumentId":"PST-BTC"},{"instrumentId":"PST-USDT"},{"instrumentId":"QTUM-BTC"},{"instrumentId":"QTUM-ETH"},{"instrumentId":"QTUM-USDT"},{"instrumentId":"QUN-BTC"},{"instrumentId":"QUN-USDT"},{"instrumentId":"RNT-USDT"},{"instrumentId":"ROAD-USDK"},{"instrumentId":"ROAD-USDT"},{"instrumentId":"RVN-BTC"},{"instrumentId":"RVN-USDT"},{"instrumentId":"SBTC-BTC"},{"instrumentId":"SC-BTC"},{"instrumentId":"SC-ETH"},{"instrumentId":"SC-OKB"},{"instrumentId":"SC-USDT"},{"instrumentId":"SNC-BTC"},{"instrumentId":"SNT-BTC"},{"instrumentId":"SNT-USDT"},{"instrumentId":"SOC-BTC"},{"instrumentId":"SOC-USDT"},{"instrumentId":"SPND-BTC"},{"instrumentId":"SPND-USDK"},{"instrumentId":"STORJ-ETH"},{"instrumentId":"STORJ-USDT"},{"instrumentId":"SWFTC-BTC"},{"instrumentId":"SWFTC-ETH"},{"instrumentId":"SWFTC-USDT"},{"instrumentId":"TCT-BTC"},{"instrumentId":"TCT-USDT"},{"instrumentId":"THETA-BTC"},{"instrumentId":"THETA-USDT"},{"instrumentId":"TOPC-ETH"},{"instrumentId":"TOPC-USDT"},{"instrumentId":"TRIO-BTC"},{"instrumentId":"TRIO-ETH"},{"instrumentId":"TRIO-USDT"},{"instrumentId":"TRUE-BTC"},{"instrumentId":"TRUE-ETH"},{"instrumentId":"TRUE-USDT"},{"instrumentId":"TRX-BTC"},{"instrumentId":"TRX-ETH"},{"instrumentId":"TRX-OKB"},{"instrumentId":"TRX-USDC"},{"instrumentId":"TRX-USDK"},{"instrumentId":"TRX-USDT"},{"instrumentId":"TUSD-BTC"},{"instrumentId":"TUSD-USDT"},{"instrumentId":"UBTC-USDT"},{"instrumentId":"USDC-BTC"},{"instrumentId":"USDC-USDT"},{"instrumentId":"USDT-USDK"},{"instrumentId":"UTK-USDT"},{"instrumentId":"VIB-BTC"},{"instrumentId":"VIB-USDT"},{"instrumentId":"VITE-BTC"},{"instrumentId":"VSYS-BTC"},{"instrumentId":"VSYS-USDK"},{"instrumentId":"VSYS-USDT"},{"instrumentId":"WAVES-BTC"},{"instrumentId":"WAVES-ETH"},{"instrumentId":"WAVES-USDT"},{"instrumentId":"WTC-BTC"},{"instrumentId":"WTC-ETH"},{"instrumentId":"WTC-USDT"},{"instrumentId":"WXT-BTC"},{"instrumentId":"WXT-OKB"},{"instrumentId":"WXT-USDK"},{"instrumentId":"WXT-USDT"},{"instrumentId":"XEM-BTC"},{"instrumentId":"XEM-ETH"},{"instrumentId":"XEM-USDT"},{"instrumentId":"XLM-BTC"},{"instrumentId":"XLM-ETH"},{"instrumentId":"XLM-USDT"},{"instrumentId":"XMR-BTC"},{"instrumentId":"XMR-ETH"},{"instrumentId":"XMR-USDT"},{"instrumentId":"XPO-USDT"},{"instrumentId":"XRP-BTC"},{"instrumentId":"XRP-ETH"},{"instrumentId":"XRP-OKB"},{"instrumentId":"XRP-USDC"},{"instrumentId":"XRP-USDK"},{"instrumentId":"XRP-USDT"},{"instrumentId":"XTZ-BTC"},{"instrumentId":"XTZ-USDT"},{"instrumentId":"XUC-USDT"},{"instrumentId":"YEE-USDT"},{"instrumentId":"YOU-BTC"},{"instrumentId":"YOU-ETH"},{"instrumentId":"YOU-USDT"},{"instrumentId":"YOYO-BTC"},{"instrumentId":"YOYO-USDT"},{"instrumentId":"ZEC-BTC"},{"instrumentId":"ZEC-ETH"},{"instrumentId":"ZEC-OKB"},{"instrumentId":"ZEC-USDT"},{"instrumentId":"ZEN-BTC"},{"instrumentId":"ZEN-ETH"},{"instrumentId":"ZEN-USDT"},{"instrumentId":"ZIL-BTC"},{"instrumentId":"ZIL-ETH"},{"instrumentId":"ZIL-USDT"},{"instrumentId":"ZIP-USDT"},{"instrumentId":"ZRX-BTC"},{"instrumentId":"ZRX-ETH"},{"instrumentId":"ZRX-USDT"}]
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
         * instrumentId : AAC-BTC
         */

        private String instrumentId;

        public String getInstrumentId() {
            return instrumentId;
        }

        public void setInstrumentId(String instrumentId) {
            this.instrumentId = instrumentId;
        }
    }
}
