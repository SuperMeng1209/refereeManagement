package com.lyh.refereeManagement.api;

import com.lyh.refereeManagement.entity.Train;
import com.lyh.refereeManagement.mapper.TrainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/trainApi")
public class TrainApi {

    @Autowired
    private TrainMapper trainMapper;

    @PostMapping("addTrain")
    @ResponseBody
    public int addTrain(@RequestBody Train train){
        UUID uuid = UUID.randomUUID();
        train.setTrainid(uuid.toString());
        train.setStatus(1);
        return trainMapper.addTrain(train);
    }

    @PostMapping("findAllList")
    @ResponseBody
    public List<Train> findAllList(){
        return trainMapper.findAllList();
    }

    @PostMapping("pass")
    @ResponseBody
    public int pass(@RequestBody Train train){
        return trainMapper.addPass(train);
    }

    @PostMapping("train")
    @ResponseBody
    public int train(@RequestBody Train train){
        int status = trainMapper.findStatus(train);
        if(status == 0){
            return -1;
        }else
        if(status == 1){
            String userList = trainMapper.findByUserId(train);
            String[] list = userList.split(",");
            int str = 0;
            for(int i = 0;i<list.length;i++){
                if(list[i].equals(train.getUserid())){
                    str = 1;
                }
            }
            if(str == 0){
                if(userList.length()<1){
                    userList += train.getUserid();
                }else{
                    userList += ','+train.getUserid();
                }
                train.setUseridList(userList);
                return trainMapper.addUserList(train);
            }else{
                return -2;
            }
        }else{
            return -999;
        }
    }
}
