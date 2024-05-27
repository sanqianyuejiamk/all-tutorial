package com.mengka.springboot.config;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

/**
 * @author adan
 */
public class SqlLogFilter extends Filter<ILoggingEvent> {
    private final static String STR = "com.cic.common.mybatis.log.DruidSqlLogInterceptor";

    Level level;

    @Override
    public FilterReply decide(ILoggingEvent event) {
        if(STR.equals(event.getLoggerName())&&event.getLevel().equals(level)) {

            return FilterReply.ACCEPT;

        }
        return FilterReply.DENY;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}