package com.mengka.springboot.component;

import com.google.code.shardbatis.strategy.ShardStrategy;
import com.mengka.springboot.dao.domain.DSTelVoiceCall;
import com.mengka.springboot.dao.domain.DSTelVoiceCallExample;
import com.mengka.springboot.util.ShardUtil;
import com.mengka.springboot.util.StringUtils;
import java.util.Iterator;
import java.util.List;

/**
 * @author huangyy
 * @description
 * @data 2017/03/18.
 */
public class OTSVoiceCallStrategy implements ShardStrategy {

    public OTSVoiceCallStrategy(){}

    public String getTargetTableName(String tableName, Object param, String methodName) {
        String suffix = "";
        if (param instanceof DSTelVoiceCall) {
            DSTelVoiceCall example = (DSTelVoiceCall) param;
            if (example != null && example.getUserId() != null) {
                suffix = ShardUtil.getShardSuffix(example.getUserId(), 20);
            }
        } else if (param instanceof DSTelVoiceCallExample) {
            DSTelVoiceCallExample example1 = (DSTelVoiceCallExample) param;
            if (example1 != null && example1.getOredCriteria() != null) {
                List criterias = example1.getOredCriteria();
                Iterator var8 = criterias.iterator();

                while (var8.hasNext()) {
                    DSTelVoiceCallExample.Criteria criteria = (DSTelVoiceCallExample.Criteria) var8.next();
                    List criterions = criteria.getAllCriteria();
                    if (criterions != null) {
                        Iterator var11 = criterions.iterator();

                        while (var11.hasNext()) {
                            DSTelVoiceCallExample.Criterion criterion = (DSTelVoiceCallExample.Criterion) var11.next();
                            String condition = criterion.getCondition();
                            if ("User_ID =".equalsIgnoreCase(condition)) {
                                Object value = criterion.getValue();
                                if (value != null) {
                                    suffix = ShardUtil.getShardSuffix(value.toString(), 20);
                                    break;
                                }
                            }
                        }
                    }

                    if (!StringUtils.isEmpty(suffix)) {
                        break;
                    }
                }
            }
        }

        return tableName + suffix;
    }
}
