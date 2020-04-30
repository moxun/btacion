package com.example.myapplication.bean;

import java.util.List;

public class UserinfoBean {

    /**
     * code : 200
     * message : 请求成功
     * data : {"balanceModels":[{"amount":"0.00000000","coin":{"coinName":"USDT","minRecharge":"100.00000000","minWithdraw":"100.00000000","maxWithdraw":"10000.00000000","withdrawFree":"1.00000000","contractAddress":"1","coinPrice":"1.00000000","netWork":"ETH","id":1,"coinImg":"5f7db74648d948488b1011148bacf0a9","closeRecharge":"N","closeWithdraw":"N"}},{"amount":"0.00000000","coin":{"coinName":"EOS","minRecharge":"123.00000000","minWithdraw":"123.00000000","maxWithdraw":"123.00000000","withdrawFree":"123.00000000","contractAddress":"123","coinPrice":"123.00000000","netWork":"EOS","id":2,"coinImg":"91c6ecceca1b4ef6a877b8ec2ebf56c6","closeRecharge":"N","closeWithdraw":"N"}},{"amount":"0.00000000","coin":{"coinName":"ETH","minRecharge":"123.00000000","minWithdraw":"123.00000000","maxWithdraw":"213.00000000","withdrawFree":"3323.00000000","contractAddress":"123","coinPrice":"123.00000000","netWork":"EOS","id":3,"coinImg":"541164443f8f42cab7f676664fbb9c1f","closeRecharge":"N","closeWithdraw":"N"}}],"user":{"id":11,"userName":"onlyY","userHead":"","userInvitation":"0d1631","userPhone":"17611559382","userEmail":"","userEnabled":"Y","userParentId":1},"rechargeAddress":[{"rechargeAddress":{"id":1,"userId":11,"address":"bec4fb600675f420ffed213d87466c13"},"coin":{"coinName":"USDT","minRecharge":"100.00000000","minWithdraw":"100.00000000","maxWithdraw":"10000.00000000","withdrawFree":"1.00000000","contractAddress":"1","coinPrice":"1.00000000","netWork":"ETH","id":1,"coinImg":"5f7db74648d948488b1011148bacf0a9","closeRecharge":"N","closeWithdraw":"N"},"balance":{"amount":"0.00000000","coin":{"coinName":"USDT","minRecharge":"100.00000000","minWithdraw":"100.00000000","maxWithdraw":"10000.00000000","withdrawFree":"1.00000000","contractAddress":"1","coinPrice":"1.00000000","netWork":"ETH","id":1,"coinImg":"5f7db74648d948488b1011148bacf0a9","closeRecharge":"N","closeWithdraw":"N"}}},{"rechargeAddress":{"id":2,"userId":11,"address":"7119712830db5e93c1ed09fe948b007b"},"coin":{"coinName":"EOS","minRecharge":"123.00000000","minWithdraw":"123.00000000","maxWithdraw":"123.00000000","withdrawFree":"123.00000000","contractAddress":"123","coinPrice":"123.00000000","netWork":"EOS","id":2,"coinImg":"91c6ecceca1b4ef6a877b8ec2ebf56c6","closeRecharge":"N","closeWithdraw":"N"},"balance":{"amount":"0.00000000","coin":{"coinName":"EOS","minRecharge":"123.00000000","minWithdraw":"123.00000000","maxWithdraw":"123.00000000","withdrawFree":"123.00000000","contractAddress":"123","coinPrice":"123.00000000","netWork":"EOS","id":2,"coinImg":"91c6ecceca1b4ef6a877b8ec2ebf56c6","closeRecharge":"N","closeWithdraw":"N"}}},{"rechargeAddress":{"id":3,"userId":11,"address":"1799b612e92a2d5bde5f6afb4810f558"},"coin":{"coinName":"ETH","minRecharge":"123.00000000","minWithdraw":"123.00000000","maxWithdraw":"213.00000000","withdrawFree":"3323.00000000","contractAddress":"123","coinPrice":"123.00000000","netWork":"EOS","id":3,"coinImg":"541164443f8f42cab7f676664fbb9c1f","closeRecharge":"N","closeWithdraw":"N"},"balance":{"amount":"0.00000000","coin":{"coinName":"ETH","minRecharge":"123.00000000","minWithdraw":"123.00000000","maxWithdraw":"213.00000000","withdrawFree":"3323.00000000","contractAddress":"123","coinPrice":"123.00000000","netWork":"EOS","id":3,"coinImg":"541164443f8f42cab7f676664fbb9c1f","closeRecharge":"N","closeWithdraw":"N"}}}]}
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
         * balanceModels : [{"amount":"0.00000000","coin":{"coinName":"USDT","minRecharge":"100.00000000","minWithdraw":"100.00000000","maxWithdraw":"10000.00000000","withdrawFree":"1.00000000","contractAddress":"1","coinPrice":"1.00000000","netWork":"ETH","id":1,"coinImg":"5f7db74648d948488b1011148bacf0a9","closeRecharge":"N","closeWithdraw":"N"}},{"amount":"0.00000000","coin":{"coinName":"EOS","minRecharge":"123.00000000","minWithdraw":"123.00000000","maxWithdraw":"123.00000000","withdrawFree":"123.00000000","contractAddress":"123","coinPrice":"123.00000000","netWork":"EOS","id":2,"coinImg":"91c6ecceca1b4ef6a877b8ec2ebf56c6","closeRecharge":"N","closeWithdraw":"N"}},{"amount":"0.00000000","coin":{"coinName":"ETH","minRecharge":"123.00000000","minWithdraw":"123.00000000","maxWithdraw":"213.00000000","withdrawFree":"3323.00000000","contractAddress":"123","coinPrice":"123.00000000","netWork":"EOS","id":3,"coinImg":"541164443f8f42cab7f676664fbb9c1f","closeRecharge":"N","closeWithdraw":"N"}}]
         * user : {"id":11,"userName":"onlyY","userHead":"","userInvitation":"0d1631","userPhone":"17611559382","userEmail":"","userEnabled":"Y","userParentId":1}
         * rechargeAddress : [{"rechargeAddress":{"id":1,"userId":11,"address":"bec4fb600675f420ffed213d87466c13"},"coin":{"coinName":"USDT","minRecharge":"100.00000000","minWithdraw":"100.00000000","maxWithdraw":"10000.00000000","withdrawFree":"1.00000000","contractAddress":"1","coinPrice":"1.00000000","netWork":"ETH","id":1,"coinImg":"5f7db74648d948488b1011148bacf0a9","closeRecharge":"N","closeWithdraw":"N"},"balance":{"amount":"0.00000000","coin":{"coinName":"USDT","minRecharge":"100.00000000","minWithdraw":"100.00000000","maxWithdraw":"10000.00000000","withdrawFree":"1.00000000","contractAddress":"1","coinPrice":"1.00000000","netWork":"ETH","id":1,"coinImg":"5f7db74648d948488b1011148bacf0a9","closeRecharge":"N","closeWithdraw":"N"}}},{"rechargeAddress":{"id":2,"userId":11,"address":"7119712830db5e93c1ed09fe948b007b"},"coin":{"coinName":"EOS","minRecharge":"123.00000000","minWithdraw":"123.00000000","maxWithdraw":"123.00000000","withdrawFree":"123.00000000","contractAddress":"123","coinPrice":"123.00000000","netWork":"EOS","id":2,"coinImg":"91c6ecceca1b4ef6a877b8ec2ebf56c6","closeRecharge":"N","closeWithdraw":"N"},"balance":{"amount":"0.00000000","coin":{"coinName":"EOS","minRecharge":"123.00000000","minWithdraw":"123.00000000","maxWithdraw":"123.00000000","withdrawFree":"123.00000000","contractAddress":"123","coinPrice":"123.00000000","netWork":"EOS","id":2,"coinImg":"91c6ecceca1b4ef6a877b8ec2ebf56c6","closeRecharge":"N","closeWithdraw":"N"}}},{"rechargeAddress":{"id":3,"userId":11,"address":"1799b612e92a2d5bde5f6afb4810f558"},"coin":{"coinName":"ETH","minRecharge":"123.00000000","minWithdraw":"123.00000000","maxWithdraw":"213.00000000","withdrawFree":"3323.00000000","contractAddress":"123","coinPrice":"123.00000000","netWork":"EOS","id":3,"coinImg":"541164443f8f42cab7f676664fbb9c1f","closeRecharge":"N","closeWithdraw":"N"},"balance":{"amount":"0.00000000","coin":{"coinName":"ETH","minRecharge":"123.00000000","minWithdraw":"123.00000000","maxWithdraw":"213.00000000","withdrawFree":"3323.00000000","contractAddress":"123","coinPrice":"123.00000000","netWork":"EOS","id":3,"coinImg":"541164443f8f42cab7f676664fbb9c1f","closeRecharge":"N","closeWithdraw":"N"}}}]
         */

        private UserBean user;
        private List<BalanceModelsBean> balanceModels;
        private List<RechargeAddressBeanX> rechargeAddress;

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public List<BalanceModelsBean> getBalanceModels() {
            return balanceModels;
        }

        public void setBalanceModels(List<BalanceModelsBean> balanceModels) {
            this.balanceModels = balanceModels;
        }

        public List<RechargeAddressBeanX> getRechargeAddress() {
            return rechargeAddress;
        }

        public void setRechargeAddress(List<RechargeAddressBeanX> rechargeAddress) {
            this.rechargeAddress = rechargeAddress;
        }

        public static class UserBean {
            /**
             * id : 11
             * userName : onlyY
             * userHead :
             * userInvitation : 0d1631
             * userPhone : 17611559382
             * userEmail :
             * userEnabled : Y
             * userParentId : 1
             */

            private int id;
            private String userName;
            private String userHead;
            private String userInvitation;
            private String userPhone;
            private String userEmail;
            private String userEnabled;
            private int userParentId;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getUserHead() {
                return userHead;
            }

            public void setUserHead(String userHead) {
                this.userHead = userHead;
            }

            public String getUserInvitation() {
                return userInvitation;
            }

            public void setUserInvitation(String userInvitation) {
                this.userInvitation = userInvitation;
            }

            public String getUserPhone() {
                return userPhone;
            }

            public void setUserPhone(String userPhone) {
                this.userPhone = userPhone;
            }

            public String getUserEmail() {
                return userEmail;
            }

            public void setUserEmail(String userEmail) {
                this.userEmail = userEmail;
            }

            public String getUserEnabled() {
                return userEnabled;
            }

            public void setUserEnabled(String userEnabled) {
                this.userEnabled = userEnabled;
            }

            public int getUserParentId() {
                return userParentId;
            }

            public void setUserParentId(int userParentId) {
                this.userParentId = userParentId;
            }
        }

        public static class BalanceModelsBean {
            /**
             * amount : 0.00000000
             * coin : {"coinName":"USDT","minRecharge":"100.00000000","minWithdraw":"100.00000000","maxWithdraw":"10000.00000000","withdrawFree":"1.00000000","contractAddress":"1","coinPrice":"1.00000000","netWork":"ETH","id":1,"coinImg":"5f7db74648d948488b1011148bacf0a9","closeRecharge":"N","closeWithdraw":"N"}
             */

            private double amount;
            private CoinBean coin;
            private double freeze;

            public double getFreeze() {
                return freeze;
            }

            public void setFreeze(double freeze) {
                this.freeze = freeze;
            }

            public double getAmount() {
                return amount;
            }

            public void setAmount(double amount) {
                this.amount = amount;
            }

            public CoinBean getCoin() {
                return coin;
            }

            public void setCoin(CoinBean coin) {
                this.coin = coin;
            }

            public static class CoinBean {
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
                private String withdrawFree;
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

                public String getWithdrawFree() {
                    return withdrawFree;
                }

                public void setWithdrawFree(String withdrawFree) {
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

        public static class RechargeAddressBeanX {
            /**
             * rechargeAddress : {"id":1,"userId":11,"address":"bec4fb600675f420ffed213d87466c13"}
             * coin : {"coinName":"USDT","minRecharge":"100.00000000","minWithdraw":"100.00000000","maxWithdraw":"10000.00000000","withdrawFree":"1.00000000","contractAddress":"1","coinPrice":"1.00000000","netWork":"ETH","id":1,"coinImg":"5f7db74648d948488b1011148bacf0a9","closeRecharge":"N","closeWithdraw":"N"}
             * balance : {"amount":"0.00000000","coin":{"coinName":"USDT","minRecharge":"100.00000000","minWithdraw":"100.00000000","maxWithdraw":"10000.00000000","withdrawFree":"1.00000000","contractAddress":"1","coinPrice":"1.00000000","netWork":"ETH","id":1,"coinImg":"5f7db74648d948488b1011148bacf0a9","closeRecharge":"N","closeWithdraw":"N"}}
             */

            private RechargeAddressBean rechargeAddress;
            private CoinBeanX coin;
            private BalanceBean balance;

            public RechargeAddressBean getRechargeAddress() {
                return rechargeAddress;
            }

            public void setRechargeAddress(RechargeAddressBean rechargeAddress) {
                this.rechargeAddress = rechargeAddress;
            }

            public CoinBeanX getCoin() {
                return coin;
            }

            public void setCoin(CoinBeanX coin) {
                this.coin = coin;
            }

            public BalanceBean getBalance() {
                return balance;
            }

            public void setBalance(BalanceBean balance) {
                this.balance = balance;
            }

            public static class RechargeAddressBean {
                /**
                 * id : 1
                 * userId : 11
                 * address : bec4fb600675f420ffed213d87466c13
                 */

                private int id;
                private int userId;
                private String address;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getUserId() {
                    return userId;
                }

                public void setUserId(int userId) {
                    this.userId = userId;
                }

                public String getAddress() {
                    return address;
                }

                public void setAddress(String address) {
                    this.address = address;
                }
            }

            public static class CoinBeanX {
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
                private String withdrawFree;
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

                public String getWithdrawFree() {
                    return withdrawFree;
                }

                public void setWithdrawFree(String withdrawFree) {
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

            public static class BalanceBean {
                /**
                 * amount : 0.00000000
                 * coin : {"coinName":"USDT","minRecharge":"100.00000000","minWithdraw":"100.00000000","maxWithdraw":"10000.00000000","withdrawFree":"1.00000000","contractAddress":"1","coinPrice":"1.00000000","netWork":"ETH","id":1,"coinImg":"5f7db74648d948488b1011148bacf0a9","closeRecharge":"N","closeWithdraw":"N"}
                 */

                private String amount;
                private CoinBeanXX coin;

                public String getAmount() {
                    return amount;
                }

                public void setAmount(String amount) {
                    this.amount = amount;
                }

                public CoinBeanXX getCoin() {
                    return coin;
                }

                public void setCoin(CoinBeanXX coin) {
                    this.coin = coin;
                }

                public static class CoinBeanXX {
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
                    private String withdrawFree;
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

                    public String getWithdrawFree() {
                        return withdrawFree;
                    }

                    public void setWithdrawFree(String withdrawFree) {
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
        }
    }
}
