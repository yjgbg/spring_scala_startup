package $organization$.$name$
package jpa.support

import org.springframework.data.jpa.repository.{JpaRepository, JpaSpecificationExecutor}
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
trait StdRepo[A <: StdEntity[A]] extends JpaRepository[A, Long] with JpaSpecificationExecutor[A] {
}
