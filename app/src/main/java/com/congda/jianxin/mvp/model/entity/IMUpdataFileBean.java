package com.congda.jianxin.mvp.model.entity;

/**
 * Created by csx on 2017/10/23.
 */

public class IMUpdataFileBean {
    public String status;
    public String message;
    public IMUpdataFile data;
    private String objectkey;
    private String ownurl;


    public String getObjectkey() {
        return objectkey;
    }

    public void setObjectkey(String objectkey) {
        this.objectkey = objectkey;
    }

    public String getOwnurl() {
        return ownurl;
    }

    public void setOwnurl(String ownurl) {
        this.ownurl = ownurl;
    }

    public class IMUpdataFile {
        private String fileName;
        private String filePath;
        private String thumbnailpath;
        private String fileType;
        private String objectkey;

        public String getObjectkey() {
            return objectkey;
        }

        public void setObjectkey(String objectkey) {
            this.objectkey = objectkey;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        public String getThumbnailpath() {
            return thumbnailpath;
        }

        public void setThumbnailpath(String thumbnailpath) {
            this.thumbnailpath = thumbnailpath;
        }

        public String getFileType() {
            return fileType;
        }

        public void setFileType(String fileType) {
            this.fileType = fileType;
        }
    }
}
