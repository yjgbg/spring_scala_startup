package $organization$

import org.springframework.core.convert.ConversionService
import org.springframework.jdbc.core.{BeanPropertyRowMapper, JdbcTemplate}
import $organization$.$name$.jpa.support.StdEntity

import javax.persistence.EntityManager
import scala.language.experimental.macros
import scala.reflect.ClassTag

package object $name$ {
  def clazzOf[A:ClassTag]: Class[A] = implicitly[ClassTag[A]].runtimeClass.asInstanceOf[Class[A]]
  val Spring = utils.Spring
  implicit class StringOps(string: String) {
    def execute():Unit = Spring[JdbcTemplate].execute(string)
    def query[A:ClassTag]: java.util.List[A] =
      Spring[JdbcTemplate].query(string, BeanPropertyRowMapper.newInstance(clazzOf[A],Spring[ConversionService]))
  }
  implicit class LongOps(primaryKey: Long) {
    def findById[A <: StdEntity[A] :ClassTag]:Option[A] =
      Option(Spring[EntityManager].find(clazzOf[A],primaryKey))
  }
}
