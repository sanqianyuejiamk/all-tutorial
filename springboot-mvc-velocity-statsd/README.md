# springboot-mvc-velocity-statsd

## Download
https://github.com/etsy/statsd/releases
```
cp exampleConfig.js Config.js
nohup node stats.js Config.js &
```

### How To Configure StatsD to Collect Arbitrary Stats for Graphite
https://www.digitalocean.com/community/tutorials/how-to-configure-statsd-to-collect-arbitrary-stats-for-graphite-on-ubuntu-14-04

```
{
  graphitePort: 2003
, graphiteHost: "localhost"
, port: 8125
, graphite: {
    legacyNamespace: false
  }
}
```

### Docker image for Graphite & Statsd
https://github.com/hopsoft/docker-graphite-statsd
https://github.com/CastawayLabs/graphite-statsd

```
docker run -d\
 --name graphite\
 --restart=always\
 -p 80:80\
 -p 2003-2004:2003-2004\
 -p 2023-2024:2023-2024\
 -p 8125:8125/udp\
 -p 8126:8126\
 hopsoft/graphite-statsd
```

#### Includes the following components

* [Nginx](http://nginx.org/) - reverse proxies the graphite dashboard
* [Graphite](http://graphite.readthedocs.org/en/latest/) - front-end dashboard
* [Carbon](http://graphite.readthedocs.org/en/latest/carbon-daemons.html) - back-end
* [Statsd](https://github.com/etsy/statsd/wiki) - UDP based back-end proxy