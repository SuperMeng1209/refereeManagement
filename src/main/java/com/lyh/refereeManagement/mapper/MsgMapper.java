package com.lyh.refereeManagement.mapper;

import com.lyh.refereeManagement.entity.Msg;
import com.lyh.refereeManagement.entity.ToMsg;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MsgMapper {
    List<Msg> findAll(Msg msg);

    int updateStatus(Msg msg);

    int addMsg(Msg msg);

    int addToMsg(ToMsg toMsg);
}
