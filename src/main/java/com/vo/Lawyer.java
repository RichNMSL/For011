package com.vo;

public class Lawyer {
    private String code;
    private String name;
    private String is_entrust;

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

    public String getIs_entrust() {
        return is_entrust;
    }

    public void setIs_entrust(String is_entrust) {
        this.is_entrust = is_entrust;
    }

    @Override
    public String toString() {
        return "Lawyer{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", is_entrust='" + is_entrust + '\'' +
                '}';
    }
}
