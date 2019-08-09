package com.jq.spb;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpbApplication {

   /* public static void main(String[] args) {
        SpringApplication.run(SpbApplication.class, args);
    } */
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SpbApplication.class);
         application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }

   /* public static void main(String[] args) {
        System.exit(SpringApplication.exit(SpringApplication.run(SpbApplication.class, args)));
    }*/
   /* public static void main(String[] args) {
        new SpringApplicationBuilder()
                .parent(SpbApplication.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }*/
   /*@Bean
    public ExitCodeGenerator exitCodeGenerator() {

        return new ExitCode();
    }*/

}
