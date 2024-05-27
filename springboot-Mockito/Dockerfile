FROM registry-vpc.cn-hangzhou-finance.aliyuncs.com/hz_namespace/base:maven-3.6.1-jdk-8 as BUILDER
COPY ./ /usr/src
RUN cd /usr/src && mvn clean package -DskipTests --settings ./settings.xml
FROM registry-vpc.cn-hangzhou-finance.aliyuncs.com/hz_namespace/base:jdk8-231-centos
COPY --from=BUILDER /usr/src/target/*.jar /home/admin/
WORKDIR /home/admin
EXPOSE 8080
ENTRYPOINT ["sh","-c","java -jar -Duser.timezone=GMT+8 -Dspring.profiles.active=prod /home/admin/callcenter-soft-phone-1.0-SNAPSHOT.jar"]