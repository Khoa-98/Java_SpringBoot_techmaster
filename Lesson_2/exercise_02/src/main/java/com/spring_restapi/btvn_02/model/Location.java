package com.spring_restapi.btvn_02.model;

public enum Location {
    HANOI("Ha Noi"),
    HAIPHONG("Hai Phong"),
    DANANG("Da Nang"),
    HOCHIMINH("Ho Chi Minh");

    private String value;

    private Location(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    

}
