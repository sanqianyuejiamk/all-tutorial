package com.mengka.springboot.component;

import com.google.gson.Gson;
import com.mengka.springboot.domain.Note;
import lombok.extern.slf4j.Slf4j;

/**
 * @author mengka
 * @version 2021/4/17
 * @since
 */
@Slf4j
public class CommonFunction {

    public static void println(Note note){
        String noteJson = new Gson().toJson(note);
        log.info("-----------, noteJson = {}",noteJson);
    }
}
