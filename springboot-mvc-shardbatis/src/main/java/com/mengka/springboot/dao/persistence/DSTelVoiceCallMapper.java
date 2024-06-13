package com.mengka.springboot.dao.persistence;

import com.mengka.springboot.dao.domain.DSTelVoiceCall;
import com.mengka.springboot.dao.domain.DSTelVoiceCallExample;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @author huangyy
 * @description
 * @date 2017/04/30.
 */
public interface DSTelVoiceCallMapper {
    int countByExample(DSTelVoiceCallExample var1);

    int deleteByExample(DSTelVoiceCallExample var1);

    int insert(DSTelVoiceCall var1);

    int insertSelective(DSTelVoiceCall var1);

    List<DSTelVoiceCall> selectByExample(DSTelVoiceCallExample var1);

    int updateByExampleSelective(@Param("record") DSTelVoiceCall var1, @Param("example") DSTelVoiceCallExample var2);

    int updateByExample(@Param("record") DSTelVoiceCall var1, @Param("example") DSTelVoiceCallExample var2);
}
