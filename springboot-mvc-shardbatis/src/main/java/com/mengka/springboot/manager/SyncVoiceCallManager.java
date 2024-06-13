package com.mengka.springboot.manager;

import com.mengka.springboot.dao.domain.DSTelVoiceCall;
import com.mengka.springboot.model.PageResult;
import com.mengka.springboot.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huangyy
 * @description
 * @date 2017/04/30.
 */
@Slf4j
@Service
public class SyncVoiceCallManager {

    @Autowired
    private TelVoiceCallManager telVoiceCallManager;

    public void syncVoiceCall(String userId,String mobile){
        if(StringUtils.isBlank(userId)||StringUtils.isBlank(mobile)){
            return;
        }
        log.info("syncVoiceCall mysql==>OTS userId = {}, mobile = {} , currentPage = 1",userId,mobile);
        //查询通话记录
        PageResult<DSTelVoiceCall> pageInfo = telVoiceCallManager.listDSTelVoiceCall(userId, mobile,1);
        List<DSTelVoiceCall> telVoiceCallList = pageInfo.getResult();
        if(CollectionUtils.isEmpty(telVoiceCallList)){
            log.info("syncVoiceCall telVoiceCallList is empty! userId = {}, mobile = {}",userId,mobile);
            return;
        }
        int currentPage = 1;
        while(currentPage<=pageInfo.getMaxPage()){
            //同步mysql=>OTS
            syncVoiceCall(telVoiceCallList);
            currentPage++;
            log.info("syncVoiceCall mysql==>OTS userId = {}, mobile = {} , currentPage = {}",userId,mobile,currentPage);
            //获取下一页
            pageInfo = telVoiceCallManager.listDSTelVoiceCall(userId, mobile,currentPage);
            telVoiceCallList = pageInfo.getResult();
        }
    }

    public void syncVoiceCall(List<DSTelVoiceCall> telVoiceCallList) {
        for (DSTelVoiceCall telVoiceCall : telVoiceCallList) {
            log.info("syncVoiceCall telVoiceCall = {}",telVoiceCall);
        }
    }
}
