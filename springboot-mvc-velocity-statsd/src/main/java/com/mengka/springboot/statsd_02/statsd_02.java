package com.mengka.springboot.statsd_02;

import com.timgroup.statsd.NonBlockingStatsDClient;
import com.timgroup.statsd.StatsDClient;

/**
 * @author huangyy
 * @version cabbage-forward2.0,2018-03-08
 * @since cabbage-forward2.0
 */
public class statsd_02 {

    private static final StatsDClient statsd = new NonBlockingStatsDClient(
            "my.prefix",                          /* prefix to any stats; may be null or empty string */
            "statsd-host",                        /* common case: localhost */
            8125,                                 /* port */
            new String[]{"tag:value"}            /* Datadog extension: Constant tags, always applied */
    );

    public static void main(String[] args) {
        statsd.incrementCounter("foo");
        statsd.recordGaugeValue("bar", 100);
        statsd.recordGaugeValue("baz", 0.01); /* Datadog extension: support for floating-point gauges */
        statsd.recordHistogramValue("qux", 15);    /* Datadog extension: histograms */
        statsd.recordHistogramValue("qux", 15.5);  /* ...also floating-point */

        /*
         expects times in milliseconds
         */
        statsd.recordExecutionTime("bag", 25, "cluster:foo"); /* Datadog extension: cluster tag */
    }
}
