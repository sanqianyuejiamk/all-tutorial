package com.mengka.springboot.builder;

import com.alibaba.nacos.common.util.Md5Utils;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author: [wujie]
 * @createTime: [2019/6/20 17:05]
 * @description: 为了防止 API 调用过程中被黑客恶意篡改，调用任何一个 API 都需要携带签名。
 *  对所有 API 请求参数，根据参数名称的 ASCII 码表的顺序排序（即字母升序）。如：foo=1, bar=2,
 * foo_bar=3, foobar=4 排序后的顺序是 bar=2, foo=1, foo_bar=3, foobar=4。
 *  将排序好的参数名和参数值拼装在一起，根据上面的示例得到的结果为：
 * bar2foo1foo_bar3foobar4。
 *  把拼装好的字符串采用 utf-8 编码，使用签名算法对编码后的字节流进行摘要。如果使用 MD5 算
 * 法，则需要在拼装的字符串前后加上 appSecret 后，再进行摘要，如：
 * md5(appSecretbar2foo1foo_bar3foobar4appSecret)；
 */
public class ElinkParameterMapBuilder {
    private final Map<String, String> map;
    private final SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");

    /**
     * @param map 不存在会补入默认值
     */
    public ElinkParameterMapBuilder(String key, String secret, Map map) {
        this.map = map;
        this.map.putIfAbsent("appKey", key);
        this.map.putIfAbsent("reqId", UUID.randomUUID().toString());
        this.map.putIfAbsent("timeStamp", sf.format(new Date()));
        this.map.putIfAbsent("sign", Md5Utils.getMD5(generateString(secret), "utf-8"));

    }


    /**
     * @param key    key
     * @param secret 密钥
     * @param kv     键,值,键,值... 请保证严格顺序 kv是单数时会忽略最后一个
     */
    public ElinkParameterMapBuilder(String key, String secret, String... kv) {
        super();
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < kv.length; i += 2) {
            map.put(kv[i], kv[i + 1]);
        }
        this.map = map;
        this.map.putIfAbsent("appKey", key);
        this.map.putIfAbsent("reqId", UUID.randomUUID().toString());
        this.map.putIfAbsent("timeStamp", sf.format(new Date()));
        this.map.putIfAbsent("sign", Md5Utils.getMD5(generateString(secret), "utf-8"));
    }

    public Map<String, String> build() {
        return this.map;
    }





    /**
     * key按照ascii码排序 加入value拼接
     *
     * @param appSecret 密钥
     * @return
     */
    private String generateString(String appSecret) {
        Set<String> set = map.keySet();
        List<String> list = new ArrayList<>(set);
        list.sort((str1, str2) -> str1.compareTo(str2) < 0 ? -1 : 1);
        StringBuilder sb = new StringBuilder(appSecret);
        list.forEach(s -> sb.append(s + map.get(s)));
        return sb.append(appSecret).toString();
    }

}