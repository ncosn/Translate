package com.sgcc.yzd.translate.model;

import java.util.List;

public class TranslationResponse {
    /**
     * "result": {"trans_result": [{"dst": "你好","src": "hello"}],"from": "en","to": "zh"},
     * "log_id": 1413395986911332328
     */
    private Result result;
    private long log_id;//唯一的log id，用于问题定位

    /**
     * "error_msg": "translate internal error",
     * "error_code": 31102
     */
    private String error_msg;//错误消息体
    private String error_code;//错误码

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public long getLogId() {
        return log_id;
    }

    public void setLogId(long log_id) {
        this.log_id = log_id;
    }

    public String getErrorMsg() {
        return error_msg;
    }

    public void setErrorMsg(String error_msg) {
        this.error_msg = error_msg;
    }

    public String getErrorCode() {
        return error_code;
    }

    public void setErrorCode(String error_code) {
        this.error_code = error_code;
    }


    public class Result {
        /**
         * "trans_result": [{"dst": "你好","src": "hello"}],
         * "from": "en",
         * "to": "zh"
         */
        private List<TransResult> trans_result;//翻译结果
        private String from;//源语种方向
        private String to;//目标语种方向

        public List<TransResult> getTransResult() {
            return trans_result;
        }

        public void setTransResult(List<TransResult> trans_result) {
            this.trans_result = trans_result;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public class TransResult {
            /**
             * "dst": "你好",
             * "src": "hello"
             */
            private String dst;//译文
            private String src;//翻译原文

            public String getDst() {
                return dst;
            }

            public void setDst(String dst) {
                this.dst = dst;
            }

            public String getSrc() {
                return src;
            }

            public void setSrc(String src) {
                this.src = src;
            }
        }
    }
}
