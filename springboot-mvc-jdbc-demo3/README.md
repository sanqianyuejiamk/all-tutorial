
## DEMO

### STEP-01

```
mvn clean package -Dmaven.test.skip=true
```

### STEP-02

```
java -jar target/spring-boot-jdbc-1.0.jar
```

>DATASOURCE = HikariDataSource (HikariPool-1)
 [Usage] java xxx.jar {insert name email | display}
 
### STEP-03

```
java -Dspring.datasource.initialize=false -jar target/spring-boot-jdbc-1.0.jar insert xiafeng xiafeng@farabbit.com
```

>DATASOURCE = HikariDataSource (null)
 Add customer...
 Done!
 
### STEP-04

```
java -Dspring.datasource.initialize=false -jar target/spring-boot-jdbc-1.0.jar display
```

>DATASOURCE = HikariDataSource (null)
 Display all customers...
 Customer{id=5, name='xiafeng', email='xiafeng@farabbit.com', date=2020-05-12}
 Done!
 
### 文章

```
https://mkyong.com/spring-boot/spring-boot-jdbc-mysql-hikaricp-example/?utm_source=mkyong.com&utm_medium=referral&utm_campaign=sidebar-related&utm_content=link1&__cf_chl_captcha_tk__=8b7a599d44342cc400562ca4e904323b9a4039b5-1589274560-0-AZ7dUByIFAl_abRFSuNAXjINnRmTTNqwiuSfYEUawzGjBXFS1scqS4N-49ZgSbuDw0WWJv_fzAg7jHGfRtdcjTuJRSgrll7kpWfmQyk_AA-AKvmv6fovoSIisMr96rdCD_uhVJn1RAUg096hh_wXE76FM8US8zTT6Q0Z5qWOfPVca82AaYmjVJ0omw3p9Ej5mSrA0hjjdICZIw1Tb1_bHMiKEgkQf2RM68MFj9exBGE_KIKR0LqZFCL6mlVZNLFuNsYErYtlduXtJJ0o_ZtvOb7xXuiXO9MoNG_T82D4uyjlKMqKDj8jDdAqRjdI4byfnnutQ-Wa-UTxjDasNN7ugsu1PrHb4WBstnRE4rfOdgnpnli88IkRm3z-nZXN31UWG1RimxHNgzOlpf4dP8jltI6xSmHgORVmBt71JhfPa-5WI85ut9t70b8p97hqqZU-ficI9kLFtX7oXWZl_Eoe57nf4XurwsIzYoRQUu6MORKNwgfIPRpPoh6kvwDZn6epVFjyAQsYlLO9ywuXyJrvxJ_duJ9NzknNy1q5sD3pXaAh
```