package com.mengka.springboot.manager;

import com.mengka.springboot.utils.TimeUtil;
import org.springframework.stereotype.Service;
import java.util.Date;

/**
 * @author mengka
 * @version 2021/4/18
 * @since
 */
@Service
public class NoteManager {

    public String getNoteDescribe(){
        String content = "it a note Describe..["+TimeUtil.toDate(new Date(),TimeUtil.FORMAT_YYYY_MM_DD_HH_MM_SS);
        return content;
    }
}
