package com.mengka.springboot.schedule;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.mengka.springboot.commons.CreditApplyDetailDO;
import com.mengka.springboot.component.ZookeeperLock;
import com.mengka.springboot.domain.Note;
import com.mengka.springboot.repository.NoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Slf4j
@Component
public class VjApplyDetailJob {

    private static final String zkPath = "/creditdata/applyDetail/%s";

    @Autowired
    private ZookeeperLock zookeeperLock;

    @Autowired
    private NoteRepository noteRepository;

    public void syncApplyDetail(){
        Iterable<Note> noteIterable = noteRepository.findAll();
        List<Note> notes = Lists.newArrayList(noteIterable);
        if(CollectionUtils.isEmpty(notes)){
            log.debug("syncApplyDetail notes list isEmpty!");
            return;
        }
        log.info("syncApplyDetail selectByStatus list size = {}",notes.size());
        for(Note note : notes){
            final String path = String.format(zkPath,note.getId());
            InterProcessMutex lock = null;
            try {
                log.info("VJ syncApplyDetail applyDetailProcessor run! id = {}",note.getId());
                lock = zookeeperLock.creatInterProcessMutex(path);
                zookeeperLock.acquire(lock);
                //三方数据处理
//                applyDetailProcessor.run(creditApplyDetailDO);
                String json = new Gson().toJson(note);
                log.info("----------, 三方数据处理任务开始  json = {}",json);

            }catch (Exception e){
                log.error("applyDetailProcessor run error! applyDetailId = {}", note.getId(),e);
            }finally {
                zookeeperLock.release(lock, path);
            }
        }
    }

    @Async
    public void execute(CreditApplyDetailDO creditApplyDetailDO) {
        log.info("VjApplyDetailJob execute..");
        final String path = String.format(zkPath,creditApplyDetailDO.getId());
        try {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    log.info("VJ applyDetail applyDetailProcessor run! Id = {}",creditApplyDetailDO.getId());
                    InterProcessMutex lock = null;
                    try {
                        lock = zookeeperLock.creatInterProcessMutex(path);
                        zookeeperLock.acquire(lock);
                        //三方数据处理
//                        applyDetailProcessor.run(creditApplyDetailDO);
                        log.info("----------, 三方数据处理任务开始。。。");

                    }catch (Exception e){
                        log.error("VjApplyDetailJob execute error! applyDetailId = "+creditApplyDetailDO.getId(), e);
                    }finally {
                        zookeeperLock.release(lock, path);
                    }
                }
            }).start();
        } catch (Exception e) {
            log.error("VjApplyDetailJob error! applyDetailId = "+creditApplyDetailDO.getId(), e);
        }
        log.info("VjApplyDetailJob return..");
    }
}
