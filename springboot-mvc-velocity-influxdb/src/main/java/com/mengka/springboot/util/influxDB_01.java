package com.mengka.springboot.util;

import com.alibaba.fastjson.JSON;
import lombok.extern.log4j.Log4j2;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * [influxdb-java]
 *  https://github.com/infiniteautomation/influxdb-java
 *
 */
@Log4j2
public class influxDB_01 {

    public static void main(String[] args) throws Exception {
        InfluxDB influxDB = InfluxDBFactory.connect("http://127.0.0.1:8086", "root", "root");

        String dbName = "aTimeSeries";
//        influxDB.createDatabase(dbName);

        // Flush every 2000 Points, at least every 100ms
        influxDB.enableBatch(2000, 100, TimeUnit.MILLISECONDS);


        BatchPoints batchPoints = BatchPoints
                .database(dbName)
                .tag("async", "true")
//                .retentionPolicy("default")
                .consistency(InfluxDB.ConsistencyLevel.ALL)
                .build();

        Point point1 = Point.measurement("cpu")
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .addField("host", "serverA")
                .addField("region", "us_west")
                .addField("value", 1.19)
                .build();

        batchPoints.point(point1);
        influxDB.write(batchPoints);

//        influxDB.write(dbName, "default", point1);



        Query query = new Query("SELECT host,region,value FROM cpu", dbName);
        QueryResult queryResult = influxDB.query(query);
        log.info("-----------, queryResult = {}",JSON.toJSONString(queryResult));

        /**
         *
         *  [{
         * 	"columns": ["time", "host", "region", "value"],
         * 	"name": "cpu",
         * 	"values": [
         * 		["2018-06-08T07:58:18.753Z", "serverB", "us_west", 0.77],
         * 		["2018-06-08T08:13:03.75Z", "serverB", "us_west", 0.88],
         * 		["2018-06-08T08:15:28.161Z", "serverA", "us_west", 0.99],
         * 		["2018-06-08T08:17:43.525Z", "serverA", "us_west", 1.19]
         * 	]
         * }]
         *
         */
        List<QueryResult.Result> results = queryResult.getResults();
        QueryResult.Result r1 = results.get(0);
        List<QueryResult.Series> series = r1.getSeries();
        log.info("-----------, series = {}",JSON.toJSONString(series));
    }
}
