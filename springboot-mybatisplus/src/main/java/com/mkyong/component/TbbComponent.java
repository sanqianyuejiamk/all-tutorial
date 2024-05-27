package com.mkyong.component;

import com.mkyong.utils.TimeUtil;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author mengka
 * @Date 2022-02-10 19:19
 */
@Component(value = "TBB_COMPONENT")
public class TbbComponent extends AbsComponent{

    public String foo(){
        return "[Just for test...tbb"+ TimeUtil.toDate(new Date(),TimeUtil.FORMAT_YYYY_MM_DD_HH_MM_SS);
    }
}
