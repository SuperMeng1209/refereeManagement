package com.lyh.refereeManagement.mapper;

import com.lyh.refereeManagement.entity.ToDo;
import com.lyh.refereeManagement.entity.ToMsg;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ToDoMapper {
    List<ToDo> findAllList(ToDo todo);

    ToDo findById(ToDo todo);

    int setMsg(ToMsg toMsg);
}
