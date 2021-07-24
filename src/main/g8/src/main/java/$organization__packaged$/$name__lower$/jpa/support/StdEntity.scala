package $organization$.$name$
package jpa.support

import $organization$.$name$.utils.Spring

import java.time.LocalDateTime
import javax.persistence._
import scala.beans.BeanProperty

@MappedSuperclass
trait StdEntity[A <: StdEntity[A]] extends  Comparable[A] with Serializable {

  @Id
  @BeanProperty
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Long = _
  @BeanProperty
  var whenCreated: LocalDateTime = _
  @BeanProperty
  var whenUpdated: LocalDateTime = _

  def save: A = {
    whenUpdated = LocalDateTime.now()
    if (id<1) whenCreated = whenUpdated
    Spring.transactional(() => Spring[EntityManager].merge(this).asInstanceOf[A])
  }

  def remove(): Unit = Spring.transactional(() => Spring[EntityManager].remove(this))

  override def compareTo(o: A): Int = id compareTo o.id
}