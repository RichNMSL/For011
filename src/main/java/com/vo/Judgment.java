package com.vo;

public class Judgment {
    private String zipName;
    private String fileName;
    private String title;
    private String branch;
    private String code;
    private String is_easy;

    public String getZipName() {
        return zipName;
    }

    public void setZipName(String zipName) {
        this.zipName = zipName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIs_easy() {
        return is_easy;
    }

    public void setIs_easy(String is_easy) {
        this.is_easy = is_easy;
    }

    @Override
    public String toString() {
        return "Judgment{" +
                "zipName='" + zipName + '\'' +
                ", fileName='" + fileName + '\'' +
                ", title='" + title + '\'' +
                ", branch='" + branch + '\'' +
                ", code='" + code + '\'' +
                ", is_easy='" + is_easy + '\'' +
                '}';
    }
}
