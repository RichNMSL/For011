package com.vo;

public class Accused {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    private String isLawyer;
    private String isEntrust;

    public String getLawyerName() {
        return lawyerName;
    }

    public void setLawyerName(String lawyerName) {
        this.lawyerName = lawyerName;
    }

    private String lawyerName;

    public String getIsLawyer() {
        return isLawyer;
    }

    public void setIsLawyer(String isLawyer) {
        this.isLawyer = isLawyer;
    }

    public String getIsEntrust() {
        return isEntrust;
    }

    public void setIsEntrust(String isEntrust) {
        this.isEntrust = isEntrust;
    }

    @Override
    public String toString() {
        return "Accused{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", isLawyer='" + isLawyer + '\'' +
                ", isEntrust='" + isEntrust + '\'' +
                ", lawyerName='" + lawyerName + '\'' +
                '}';
    }
}
