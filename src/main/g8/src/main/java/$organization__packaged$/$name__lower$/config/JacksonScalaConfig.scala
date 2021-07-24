package $organization$.$name$
package config

import com.fasterxml.jackson.module.scala._
import org.springframework.boot.SpringBootConfiguration
import org.springframework.context.annotation.Bean

@SpringBootConfiguration
class JacksonScalaConfig {
  @Bean
  def scalaModule: DefaultScalaModule.type = DefaultScalaModule
}
