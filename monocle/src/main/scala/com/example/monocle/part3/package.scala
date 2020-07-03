package com.example.monocle

import monocle.syntax._

package object part3 {
  final implicit class ApplyLensOps[Source, Target](
      private val self: ApplyLens[Source, Source, Target, Target]
    ) extends AnyVal {
    def source: Source =
      self.s

    def setToIf(condition: Boolean)(target: Target): Source =
      if (condition)
        self.set(target)
      else
        source

    def setToIfDefined(target: Option[Target]): Source =
      target.fold(source)(self.set)
  }
}
