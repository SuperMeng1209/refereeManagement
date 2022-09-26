package com.lyh.refereeManagement.entity;

import lombok.Data;

@Data
public class Train {
    private String userid;
    private String trainid;
    private String trainContent;
    private String useridList;
    private String isPass;
    private int status;
}
