package com.lyh.refereeManagement.api;

import com.lyh.refereeManagement.entity.Msg;
import com.lyh.refereeManagement.entity.ToDo;
import com.lyh.refereeManagement.entity.ToMsg;
import com.lyh.refereeManagement.mapper.MsgMapper;
import com.lyh.refereeManagement.mapper.ToDoMapper;
import com.lyh.refereeManagement.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("/toDoApi")
public class ToDoApi {

    @Autowired
    private ToDoMapper toDoMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MsgMapper msgMapper;

    @PostMapping("findAll")
    @ResponseBody
    public List<ToDo> findAll(@RequestBody ToDo todo){
        return toDoMapper.findAllList(todo);
    }

    @PostMapping("addMsg")
    @ResponseBody
    public int addMsg(@RequestBody ToMsg toMsg){
        String userid = userMapper.findByRealName(toMsg);
        if(userid == null){
            return -1;
        }else{
            toMsg.setUserid(userid);
            UUID uuid = UUID.randomUUID();
            toMsg.setUuid(uuid.toString());
            toDoMapper.setMsg(toMsg);
            Msg msg = new Msg();
            msg.setUserid(Integer.parseInt(userid));
            msg.setStatus("未读");
            msg.setMsg("管理员为您创建一条选派信息");
            return msgMapper.addMsg(msg);
        }
    }
}
