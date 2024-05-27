package com.mengka.springboot.elink;

/**
 * @Author wujie
 * @Class EventData
 * @Description
 * @Date 2021/3/25 13:47
 */
public class EventData<T> {
    public final static String EVENT_CALL_OUT_COMING = "EVENT_CALL_OUT_COMING";
    //已经接通
    public final static String EVENT_CALL_CONNECTED = "EVENT_CALL_CONNECTED";
    //已经挂断
    public final static String EVENT_CALL_TERMINATED = "EVENT_CALL_TERMINATED";
    //拨打记录事件
    public final static String EVENT_CALL_RECORD = "EVENT_CALL_RECORD";
    //话单事件
    public final static String EVENT_CALL_INFO = "EVENT_CALL_INFO";

    private T data;
    private String type;



    public static <T> EventData<T> of(String eventType, T data) {
        EventData<T> eventData = new EventData<>();
        eventData.data = data;
        eventData.type = eventType;
        return eventData;
    }

    public T getData() {
        return data;
    }


    public String getType() {
        return type;
    }




    public void setData(T data) {
        this.data = data;
    }

    public void setType(String type) {
        this.type = type;
    }
}