package com.lyh.refereeManagement.entity;

import lombok.Data;

@Data
public class Msg {
    private String msgid;
    private int userid;
    private String msg;
    private String status;
}
