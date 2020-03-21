package com.congda.jianxin.mvp.model.entity;

public class IMImageViewBean {
        private String url;
        private String key;

        public IMImageViewBean(String key) {
            this.key = key;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
}
