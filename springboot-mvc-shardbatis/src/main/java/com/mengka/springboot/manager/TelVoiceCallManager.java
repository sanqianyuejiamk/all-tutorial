package com.mengka.springboot.manager;

import com.alibaba.fastjson.JSON;
import com.mengka.springboot.dao.domain.DSTelVoiceCall;
import com.mengka.springboot.dao.domain.DSTelVoiceCallExample;
import com.mengka.springboot.dao.persistence.DSTelVoiceCallMapper;
import com.mengka.springboot.model.Page;
import com.mengka.springboot.model.PageRequest;
import com.mengka.springboot.model.PageResult;
import com.mengka.springboot.util.DateUtil;
import com.mengka.springboot.util.StringUtils;
import com.mengka.springboot.util.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

/**
 * @author huangyy
 * @description
 * @date 2017/04/30.
 */
@Slf4j
@Service
public class TelVoiceCallManager {

    public static final int SYNC_VOICECALL_COUNT = 1000;

    @Autowired
    private DSTelVoiceCallMapper telVoiceCallMapper;

    /**
     *  分页查询通话记录
     *
     * @param userId 用户ID
     * @param mobile 手机号
     * @return
     */
    public PageResult<DSTelVoiceCall> listDSTelVoiceCall(String userId, String mobile, int currentPage){
        DSTelVoiceCall query = new DSTelVoiceCall();
        query.setMobile(mobile);
        query.setUserId(userId);

        PageRequest pageRequest = new PageRequest();
        pageRequest.setLimit(SYNC_VOICECALL_COUNT);
        pageRequest.setPage(currentPage);

        PageResult<DSTelVoiceCall> pageInfo = readByPage(query,pageRequest);
        log.info("listDSTelVoiceCall pageNum = {} ",pageInfo.getPageNum());
        return pageInfo;
    }

    /**
     *  分页查询通话记录
     *
     * @param query 支持userId(必填)、mobile查询
     * @param pageRequest 分页请求参数
     * @return
     */
    public PageResult<DSTelVoiceCall> readByPage(DSTelVoiceCall query, PageRequest pageRequest){
        PageResult<DSTelVoiceCall> pageResponse = null;
        try{
            //查询参数
            int limit = pageRequest.getLimit();
            int start = pageRequest.getPage()>0?(pageRequest.getPage()-1)*limit:1;
            DSTelVoiceCallExample example = getDSTelVoiceCallExampleQuery(query, pageRequest);

            //分页查询
            List<DSTelVoiceCall> list = null;
            Integer total = telVoiceCallMapper.countByExample(example);
            if(total!=null && total>0) {
                example.setPage(new Page(limit, start));
                list = telVoiceCallMapper.selectByExample(example);
            }
            log.info("tvoicecalMapper selectByExample list size = {}",list!=null?list.size():null);
            int totalPage = total%limit > 0? (total/limit + 1) : total/limit;

            //返回结果
            pageResponse = new PageResult<DSTelVoiceCall>();
            pageResponse.setCount(total);
            pageResponse.setPageNum(pageRequest.getPage());
            pageResponse.setPageSize(limit);
            pageResponse.setMaxPage(totalPage);
            pageResponse.setResult(list);
            pageResponse.setSuccess(true);
        }catch (Exception e){
            log.error("readByPage error! query = "+ JSON.toJSONString(query),e);
        }
        return pageResponse;
    }

    public DSTelVoiceCallExample getDSTelVoiceCallExampleQuery(DSTelVoiceCall query,PageRequest pageRequest){
        String userId = query.getUserId();
        String mobile = query.getMobile();
        Date sixMonth = DateUtil.addMonths(new Date(), -6);

        //查询参数
        DSTelVoiceCallExample example = new DSTelVoiceCallExample();
        DSTelVoiceCallExample.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(userId)){
            criteria.andUserIdEqualTo(userId);
        }
        if(StringUtils.isNotBlank(mobile)){
            criteria.andMobileEqualTo(mobile);
        }
        criteria.andTimeGreaterThanOrEqualTo(sixMonth);
        log.info("listDSTelVoiceCall params userId = {} , mobile = {} , time = {}",userId,mobile, TimeUtil.toDate(sixMonth,TimeUtil.FORMAT_YYYY_MM_DD_HH_MM_SS));
        return example;
    }
}
