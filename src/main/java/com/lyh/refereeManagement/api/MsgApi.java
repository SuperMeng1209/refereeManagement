package com.lyh.refereeManagement.api;

import com.lyh.refereeManagement.entity.Msg;
import com.lyh.refereeManagement.entity.ToMsg;
import com.lyh.refereeManagement.mapper.MsgMapper;
import com.lyh.refereeManagement.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/msgApi")
public class MsgApi {

    @Autowired
    private MsgMapper msgMapper;

    @Autowired
    private UserMapper userMapper;

    @PostMapping("findAll")
    @ResponseBody
    public List<Msg> findAll(@RequestBody Msg msg){
        return msgMapper.findAll(msg);
    }

    @PostMapping("updateStatus")
    @ResponseBody
    public int updateStatus(@RequestBody Msg msg){
        return msgMapper.updateStatus(msg);
    }

    @PostMapping("addMsg")
    @ResponseBody
    public int addMsg(@RequestBody Msg msg){
        return msgMapper.addMsg(msg);
    }

    @PostMapping("addToMsg")
    @ResponseBody
    public int addToMsg(@RequestBody ToMsg toMsg){
        String userid = userMapper.findByRealName(toMsg);
        if(userid == null){
            return -1;
        }else{
            toMsg.setUserid(userid);
            UUID uuid = UUID.randomUUID();
            toMsg.setUuid(uuid.toString());
            return msgMapper.addToMsg(toMsg);
        }
    }
}
