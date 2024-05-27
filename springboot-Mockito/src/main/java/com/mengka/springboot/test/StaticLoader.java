package com.mengka.springboot.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @Author wujie
 * @Class StaticLoader
 * @Description
 * @Date 2021/1/19 9:58
 */
public class StaticLoader {
    private final static Logger logger = LoggerFactory.getLogger(StaticLoader.class);
    private final Map<String, String> pathAndTarget = new HashMap<>();
    private final Map<String, Long> lastUpdate = new HashMap<>();
    private boolean isStop;

    private boolean needUpdate(File originFile) {
        Long l = lastUpdate.get(originFile.getAbsolutePath());
        if (l == null) {
            return true;
        } else {
            return !l.equals(originFile.lastModified());
        }
    }

    private void setLastUpdateTime(File originFile) {
        String absolutePath = originFile.getAbsolutePath();
        long l = originFile.lastModified();
        lastUpdate.put(absolutePath, l);
    }

    private static void copyFile(File originFile, File targetFile) {
        logger.info("copy file [{}=>{}]", originFile.getAbsolutePath(), targetFile.getAbsolutePath());
        try (FileChannel inputChannel = new FileInputStream(originFile).getChannel();
             FileChannel outputChannel = new FileOutputStream(targetFile).getChannel()
        ) {
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
        } catch (Exception e) {
            logger.info("出现错误");
        }
    }


    private void addWatch(String originPath, String targetPath) {
        pathAndTarget.put(originPath, targetPath);
    }

    public void stop() {
        this.isStop = true;
    }

    public void startWatch() {
        Queue<File> queue = new LinkedList<>();
        boolean isInit = true;
        while (!isStop) {
            for (Map.Entry<String, String> entry : pathAndTarget.entrySet()) {
                String k = entry.getKey();
                String v = entry.getValue();
                File file = new File(k);
                if (file.canRead()) {
                    queue.offer(file);
                }
                while (!queue.isEmpty()) {
                    File poll = queue.poll();
                    if (poll.isDirectory()) {
                        File[] files = poll.listFiles();
                        for (File f : files) {
                            if (f.canRead()) {
                                queue.offer(f);
                            }
                        }
                    } else {
                        String absolutePath = poll.getAbsolutePath();
                        absolutePath = absolutePath.replace("\\", "/");
                        k = k.replace("\\", "/");
                        v = v.replace("\\", "/");
                        String path = absolutePath.replaceFirst(k, v);
                        if (isInit) {
                            setLastUpdateTime(poll);
                        }
                        if (!isInit && needUpdate(poll)) {
                            copyFile(poll, new File(path));
                            setLastUpdateTime(poll);
                        }
                    }


                }
            }
            isInit = false;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                logger.info("出现错误");
            }
        }
    }


    public static void main(String[] args)  {
        StaticLoader staticLoader = new StaticLoader();
        staticLoader.addWatch("C:/Users/Hex/IdeaProjects/callcenter-soft-phone/src/main/resources/static/", "C:/Users/Hex/IdeaProjects/callcenter-soft-phone/target/classes/static/");
        staticLoader.startWatch();
    }
}