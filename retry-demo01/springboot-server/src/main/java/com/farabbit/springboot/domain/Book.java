package com.farabbit.springboot.domain;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.farabbit.springboot.config.JpaConverterJson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mengka
 * @Date 2021-11-28 15:32
 */
@Data
@Entity
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private Integer price;

    private String tenantId;

//    private Date createdAt;

    /**
     *  属性
     */
    //@Transient
    @Convert(converter = JpaConverterJson.class)
    @Column(name = "content")
    private Map<String, Object> data = new HashMap<String, Object>();

    public Book(Long id, String name){
        this.id=id;
        this.name=name;
    }

    public void setContent(String json){
        data = JSON.parseObject(json,new TypeReference<Map<String, Object>>() {
        });
    }

    public String getContent(){
        return JSON.toJSONString(data);
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }

    public void addFeature(String key,Object value){
        this.data.put(key,value);
    }

    public Object getFeature(String key){
        return this.data!=null?this.data.get(key):null;
    }
}
