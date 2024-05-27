package com.mkyong;

import com.google.gson.Gson;
import com.mkyong.dao.CustomerRepository;
import com.mkyong.model.Customer;
import com.mkyong.model.User;
import com.mkyong.repository.mapper.UserMapper;
import com.zaxxer.hikari.HikariDataSource;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;
import java.util.UUID;

import static java.lang.System.exit;

@Slf4j
@SpringBootApplication
public class SpringBootConsoleApplication implements CommandLineRunner {

    @Autowired
    DataSource dataSource;

    @Autowired
    private CustomerRepository customerRepository;

    @Resource
    private UserMapper userMapper;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringBootConsoleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("DATASOURCE = " + dataSource);

        // If you want to check the HikariDataSource settings
        HikariDataSource newds = (HikariDataSource)dataSource;
        System.out.println("DATASOURCE = " + newds.getMaximumPoolSize());


        User insertUser = new User().setName("x11").setAge(10).setEmail("x11@farabbit.com");
        insertUser.setReqstNo(UUID.randomUUID().toString().replace("-",""));
        userMapper.insert(insertUser);

        /**
         *  例1：
         *    insertFill默认字段填充
         */
        User user = userMapper.selectById(1001L);
        log.info("---selectById--- {}",new Gson().toJson(user));

        /**
         *  例2：
         *    自定义sql查询
         */
        Long idStr = 1386285477095510018L;
        User user2 = userMapper.select("select * from user where id = "+idStr);
        log.info("---自定义sql查询--- {}",new Gson().toJson(user2));


        /**
         *  例3：
         *    内置组件，批量插入
         */
        List<User> batchUsers = Lists.newArrayList();
        batchUsers.add(User.builder().name("x31").age(10).email("x31@farabbit.com").build());
        batchUsers.add(User.builder().name("x32").age(12).email("x34@farabbit.com").build());
        batchUsers.add(User.builder().name("x33").age(13).email("x33@farabbit.com").build());
        userMapper.insertBatchSomeColumn(batchUsers);


        if (args.length <= 0) {
            System.err.println("[Usage] java xxx.jar {insert name email | display}");

            System.out.println("Display all customers...");
            List<Customer> list = customerRepository.findAll();
            list.forEach(x -> System.out.println(x));

        } else {
            if (args[0].equalsIgnoreCase("insert")) {
                System.out.println("Add customer...");
                String name = args[1];
                String email = args[2];
                customerRepository.addCustomer(name, email);
            }

            if (args[0].equalsIgnoreCase("display")) {
                System.out.println("Display all customers...");
                List<Customer> list = customerRepository.findAll();
                list.forEach(x -> System.out.println(x));
            }
            System.out.println("Done!");
        }
        //exit(0);
    }

    @Bean
    public OpenAPI customOpenAPI(@Value("${application-description}") String appDesciption, @Value("${application-version}") String appVersion) {

        return new OpenAPI()
                .info(new Info()
                        .title("sample application API")
                        .version(appVersion)
                        .description(appDesciption)
                        .termsOfService("http://swagger.io/terms/")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}