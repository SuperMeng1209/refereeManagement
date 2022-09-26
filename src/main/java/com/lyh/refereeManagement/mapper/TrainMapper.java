package com.lyh.refereeManagement.mapper;

import com.lyh.refereeManagement.entity.Train;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TrainMapper {
    int addTrain(Train train);

    List<Train> findAllList();

    int findStatus(Train train);

    String findByUserId(Train train);

    int addUserList(Train train);

    int addPass(Train train);
}
