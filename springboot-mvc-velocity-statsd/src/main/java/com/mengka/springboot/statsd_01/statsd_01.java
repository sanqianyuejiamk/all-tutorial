package com.mengka.springboot.statsd_01;

import com.mengka.springboot.component.StatsdClient;

/**
 *  https://github.com/etsy/statsd/blob/master/examples/StatsdClient.java
 *
 * @author huangyy
 * @version cabbage-forward2.0,2018-03-08
 * @since cabbage-forward2.0
 */
public class statsd_01 {

    public static void main(String[] args) throws Exception {
        StatsdClient client = new StatsdClient("statsd.example.com", 8125);
        // increment by 1
        client.increment("foo.bar.baz");
        // increment by 10
        client.increment("foo.bar.baz", 10);
        // sample rate
        client.increment("foo.bar.baz", 10, .1);

        // To enable multi metrics (aka more than 1 metric in a UDP packet) (disabled by default)
        client.enableMultiMetrics(true);  //disable by passing in false
        // To fine-tune udp packet buffer size (default=1500)
        client.setBufferSize((short) 1500);
        // To force flush the buffer out (good idea to add to your shutdown path)
        client.flush();
    }
}
