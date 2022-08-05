package com.my_jobhunt.job_hunt.model;

public enum City {
    AllCity("All cities"),
    HaNoi("Hà Nội"),
    HoChiMinh("Hồ Chí Minh"),
    HaiPhong("Hải Phòng"),
    DaNang("Đà Nẵng"),
    Others("Others");

    public final String label;

    private City(String label) {
        this.label = label;
    }
}
