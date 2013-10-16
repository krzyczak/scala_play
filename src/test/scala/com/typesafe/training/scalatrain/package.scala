package com.typesafe.training

package object scalatrain {

  def isIncreasing[T <: Ordered[T]](as: Seq[T]): Boolean =
    as.zip(as.tail).forall({ case (first, second) => first < second })

}
