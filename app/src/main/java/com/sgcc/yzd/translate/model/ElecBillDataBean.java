package com.sgcc.yzd.translate.model;

import com.google.gson.Gson;

import java.util.List;

public class ElecBillDataBean {
    public static class BeiJingResponse {
        /**
         * code : 1
         * message : 成功
         * data : { "rtnMsg": "成功", "list": [ { "electricParticulars": { "voltageClasses": "交流220V", "endDate": "2023-07-31", "totalYearPq": "0", "consName": "*xx", "remark": "-", "levelFlag": "0", "acctNo": "0011226765", "orgNo": "1140102", "begDate": "2023-07-01", "notifier": "张玉萍d/65598401", "getDate": "2023-08-01", "orgName": "东城供电服务中心", "twoLevelPq": "", "oneLevelPq": "", "totalPq": "680", "consNo": "0011226765", "pvFlag": "1", "segmentNo": "6001122893", "totalAmount": "311.71", "refundedPq": "0", "elecAddress": "北京市东城区******31号", "readerNo": "02000865", "totalMonthPq": "680", "provinceName": "国网北京市电力公司", "refundedAmt": "0" }, "readList": [ { "activeCount": "680", "quantity": "0", "rate": "1", "relaAppNo": "-", "billRead": [ { "thisReadPq": "680", "preNumber": "67271", "currentNumber": "67951", "type": "有功(总)", "apDeductPq": "0", "typeCode": "11" }, {}, { "thisReadPq": "108", "preNumber": "25564", "currentNumber": "25672", "type": "有功(谷)", "apDeductPq": "0", "typeCode": "14" }, {} ], "meterNo": "3201548039", "reactiveCount": "-" } ], "billSettleType": "0", "common": { "sumLastPqTxt2": "8.11%", "totalAmount2": "286.43", "sumSign2": "1", "totalPq2": "629", "sumLastPq2": "0.0811", "sumSign2Msg": "支持正常结算" }, "consSortCode": "03", "billYm": "202307" } ], "rtnCode": "1" }
         */
        public Integer code;
        public String message;
        public DataDTO data;

        public static BeiJingResponse objectFromData(String str) {
            return new Gson().fromJson(str, BeiJingResponse.class);
        }

        public static class DataDTO {
            /**
             * rtnMsg : 成功
             * list : [ { "electricParticulars": { "voltageClasses": "交流220V", "endDate": "2023-07-31", "totalYearPq": "0", "consName": "*xx", "remark": "-", "levelFlag": "0", "acctNo": "0011226765", "orgNo": "1140102", "begDate": "2023-07-01", "notifier": "张玉萍d/65598401", "getDate": "2023-08-01", "orgName": "东城供电服务中心", "twoLevelPq": "", "oneLevelPq": "", "totalPq": "680", "consNo": "0011226765", "pvFlag": "1", "segmentNo": "6001122893", "totalAmount": "311.71", "refundedPq": "0", "elecAddress": "北京市东城区******31号", "readerNo": "02000865", "totalMonthPq": "680", "provinceName": "国网北京市电力公司", "refundedAmt": "0" }, "readList": [ { "activeCount": "680", "quantity": "0", "rate": "1", "relaAppNo": "-", "billRead": [ { "thisReadPq": "680", "preNumber": "67271", "currentNumber": "67951", "type": "有功(总)", "apDeductPq": "0", "typeCode": "11" }, {}, { "thisReadPq": "108", "preNumber": "25564", "currentNumber": "25672", "type": "有功(谷)", "apDeductPq": "0", "typeCode": "14" }, {} ], "meterNo": "3201548039", "reactiveCount": "-" } ], "billSettleType": "0", "common": { "sumLastPqTxt2": "8.11%", "totalAmount2": "286.43", "sumSign2": "1", "totalPq2": "629", "sumLastPq2": "0.0811", "sumSign2Msg": "支持正常结算" }, "consSortCode": "03", "billYm": "202307" } ]
             * rtnCode : 1
             */
            public String rtnMsg;
            public List<ListDTO> list;
            public String rtnCode;

            public static DataDTO objectFromData(String str) {
                return new Gson().fromJson(str, DataDTO.class);
            }

            public static class ListDTO {
                /**
                 * electricParticulars : { "voltageClasses": "交流220V", "endDate": "2023-07-31", "totalYearPq": "0", "consName": "*xx", "remark": "-", "levelFlag": "0", "acctNo": "0011226765", "orgNo": "1140102", "begDate": "2023-07-01", "notifier": "张玉萍d/65598401", "getDate": "2023-08-01", "orgName": "东城供电服务中心", "twoLevelPq": "", "oneLevelPq": "", "totalPq": "680", "consNo": "0011226765", "pvFlag": "1", "segmentNo": "6001122893", "totalAmount": "311.71", "refundedPq": "0", "elecAddress": "北京市东城区******31号", "readerNo": "02000865", "totalMonthPq": "680", "provinceName": "国网北京市电力公司", "refundedAmt": "0" }
                 * readList : [ { "activeCount": "680", "quantity": "0", "rate": "1", "relaAppNo": "-", "billRead": [ { "thisReadPq": "680", "preNumber": "67271", "currentNumber": "67951", "type": "有功(总)", "apDeductPq": "0", "typeCode": "11" }, {}, { "thisReadPq": "108", "preNumber": "25564", "currentNumber": "25672", "type": "有功(谷)", "apDeductPq": "0", "typeCode": "14" }, {} ], "meterNo": "3201548039", "reactiveCount": "-" } ]
                 * billSettleType : 0
                 * common : { "sumLastPqTxt2": "8.11%", "totalAmount2": "286.43", "sumSign2": "1", "totalPq2": "629", "sumLastPq2": "0.0811", "sumSign2Msg": "支持正常结算" }
                 * consSortCode : 03
                 * billYm : 202307
                 */
                public ElectricParticularsDTO electricParticulars;
                public List<ReadListDTO> readList;
                public String billSettleType;
                public CommonDTO common;
                public String consSortCode;
                public String billYm;

                public static ListDTO objectFromData(String str) {
                    return new Gson().fromJson(str, ListDTO.class);
                }

                public static class ElectricParticularsDTO {
                    /**
                     * voltageClasses : 交流220V
                     * endDate : 2023-07-31
                     * totalYearPq : 0
                     * consName : *xx
                     * remark : -
                     * levelFlag : 0
                     * acctNo : 0011226765
                     * orgNo : 1140102
                     * begDate : 2023-07-01
                     * notifier : 张玉萍d/65598401
                     * getDate : 2023-08-01
                     * orgName : 东城供电服务中心
                     * twoLevelPq :
                     * oneLevelPq :
                     * totalPq : 680
                     * consNo : 0011226765
                     * pvFlag : 1
                     * segmentNo : 6001122893
                     * totalAmount : 311.71
                     * refundedPq : 0
                     * elecAddress : 北京市东城区******31号
                     * readerNo : 02000865
                     * totalMonthPq : 680
                     * provinceName : 国网北京市电力公司
                     * refundedAmt : 0
                     */
                    public String voltageClasses;
                    public String endDate;
                    public String totalYearPq;
                    public String consName;
                    public String remark;
                    public String levelFlag;
                    public String acctNo;
                    public String orgNo;
                    public String begDate;
                    public String notifier;
                    public String getDate;
                    public String orgName;
                    public String twoLevelPq;
                    public String oneLevelPq;
                    public String totalPq;
                    public String consNo;
                    public String pvFlag;
                    public String segmentNo;
                    public String totalAmount;
                    public String refundedPq;
                    public String elecAddress;
                    public String readerNo;
                    public String totalMonthPq;
                    public String provinceName;
                    public String refundedAmt;

                    public static ElectricParticularsDTO objectFromData(String str) {
                        return new Gson().fromJson(str, ElectricParticularsDTO.class);
                    }
                }

                public static class CommonDTO {
                    /**
                     * sumLastPqTxt2 : 8.11%
                     * totalAmount2 : 286.43
                     * sumSign2 : 1
                     * totalPq2 : 629
                     * sumLastPq2 : 0.0811
                     * sumSign2Msg : 支持正常结算
                     */
                    public String sumLastPqTxt2;
                    public String totalAmount2;
                    public String sumSign2;
                    public String totalPq2;
                    public String sumLastPq2;
                    public String sumSign2Msg;

                    public static CommonDTO objectFromData(String str) {
                        return new Gson().fromJson(str, CommonDTO.class);
                    }
                }

                public static class ReadListDTO {
                    /**
                     * activeCount : 680
                     * quantity : 0
                     * rate : 1
                     * relaAppNo : -
                     * billRead : [{"thisReadPq": "680","preNumber": "67271","currentNumber": "67951","type": "有功(总)","apDeductPq": "0","typeCode": "11"},{},{"thisReadPq": "108","preNumber": "25564","currentNumber": "25672","type": "有功(谷)","apDeductPq": "0","typeCode": "14"},{}]
                     * meterNo : 3201548039
                     * reactiveCount : -
                     */
                    public String activeCount;
                    public String quantity;
                    public String rate;
                    public String relaAppNo;
                    public List<BillReadDTO> billRead;
                    public String meterNo;
                    public String reactiveCount;

                    public static ReadListDTO objectFromData(String str) {
                        return new Gson().fromJson(str, ReadListDTO.class);
                    }

                    public static class BillReadDTO {
                        /**
                         * sumLastPqTxt2 : 8.11%
                         * totalAmount2 : 286.43
                         * sumSign2 : 1
                         * totalPq2 : 629
                         * sumLastPq2 : 0.0811
                         * sumSign2Msg : 支持正常结算
                         */
                        public String thisReadPq;
                        public String preNumber;
                        public String currentNumber;
                        public String type;
                        public String apDeductPq;
                        public String typeCode;

                        public static BillReadDTO objectFromData(String str) {
                            return new Gson().fromJson(str, BillReadDTO.class);
                        }
                    }
                }
            }
        }
    }

    public static class BeijingResponse {

    }
}
