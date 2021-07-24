package $organization$.$name$

object TypeLevel {
  trait Nat

  trait _0 extends Nat

  trait Succ[A <: Nat] extends Nat

  type _1 = Succ[_0]
  type _2 = Succ[_1]

  trait GT[A <: Nat, B <: Nat]

  implicit def BASE[A <: Succ[_ <: Nat]] = new GT[A, _0] {}

  implicit def ADV[A <: Nat, B <: Nat](implicit gt: GT[A, B]) = new GT[Succ[A], Succ[B]] {}

  object GT {
    def apply[A <: Nat, B <: Nat](implicit lt: GT[A, B]) = lt
  }

  val x: GT[_1, _0] = GT.apply
  val y: GT[_2, _0] = GT.apply
  val z: GT[_2, _1] = GT.apply
  // val w:GT[_1,_2] = GT.apply compile error
  // val w:GT[_0,_1] = GT.apply compile error
  // val w:GT[_0,_2] = GT.apply compile error

  trait +[A <: Nat, B <: Nat] {
    type C
  }

  trait HList[+A]

  trait HNil[+A] extends HList[A]

  trait ::[+Last, Init <: HList[Last]] extends HList[Last] {
    def init: Init
    def last: Last
  }
  object HList {
    def apply[A]: HNil[A] = new HNil[A] {}
  }

  implicit class HListOps[HL <: HList[H],H](hList: HL) {
    def ++(h: H): H :: HL = new ::[H,HL] {
      def init = hList
      def last = h
    }
  }

  val hNil:HNil[Int] = HList[Int]
  val hList = hNil.++(1)// ++ 2 ++ 3 ++ 4 ++ 5 ++ 6 ++ 7 ++ 8
//  hList.init.init.last

//  trait Split[HL <: HList[I],L <: HList[I],R <: HList[I] , I]
//  object Split {
//    implicit def BASIC[A] = new Split[HNil[A],HNil[A],HNil[A],A]{}
//    implicit def ADV[HL <: HList[A],L<: HList[A],R<: HList[A],A](implicit split: Split[HL,L,R,A]) =
//      new Split[HNil[A::HNil[A]],HNil[A],HNil[A],A]{}
//  }
}
