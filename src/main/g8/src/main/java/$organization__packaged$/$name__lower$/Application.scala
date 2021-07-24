package $organization$.$name$

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
class Application

object Application extends App {
  SpringApplication.run(classOf[Application], args: _*)
}