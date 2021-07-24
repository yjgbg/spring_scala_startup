package $organization$.$name$
package utils

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.data.jpa.repository.Modifying
import org.springframework.stereotype.Component

import javax.transaction.Transactional
import scala.reflect.ClassTag

@Component
class Spring(val applicationContext: ApplicationContext) {
  @Autowired
  def injectSelf(beanHook: Spring): Unit = Spring.SELF = beanHook

  @Modifying
  @Transactional
  def transactional[A](operator: () => A): A = operator()
}

object Spring {
  private var SELF: Spring = _

  def apply[A: ClassTag]: A = SELF.applicationContext.getBean(clazzOf[A])

  def transactional[A](operator: () => A): A = SELF.transactional(operator)
}
