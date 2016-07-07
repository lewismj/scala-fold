package tests

import org.specs2._
import fold._

class FoldSpecs extends Specification {
  def is = sequential ^ s2"""Fold Specs
            $lengthSpec

            $isEmptySpec1
            $isEmptySpec2

            $sumSpec

            $allSpec1
            $allSpec2
            $allSpec3
            $allSpec4
            $allSpec5
            $allSpec6
            $allSpec7

            $anySpec1
            $anySpec2
            $anySpec3
            $anySpec4
            $anySpec5
            $anySpec6
            $anySpec7

            $headSpec1
            $headSpec2
         """

  def lengthSpec = Foldl.length[String, Int].foldl(Seq("1", "2", "3", "4"))  must_== 4

  def isEmptySpec1 = Foldl.isEmpty[String].foldl(Seq[String]())  must_== true
  def isEmptySpec2 = Foldl.isEmpty[String].foldl(Seq[String](""))  must_== false

  def sumSpec = Foldl.sum[Int].foldl(Seq(1, 1, 1)) must_== 3

  def allSpec1 = Foldl.all(identity: Boolean => Boolean).foldl(Seq(true, true, true)) must_== true
  def allSpec2 = Foldl.all(identity: Boolean => Boolean).foldl(Seq[Boolean]()) must_== true
  def allSpec3 = Foldl.all(identity: Boolean => Boolean).foldl(Seq[Boolean](true, false)) must_== false
  def allSpec4 = Foldl.all((x: Int) => x % 2 == 0).foldl(Seq(2, 4, 6)) must_== true
  def allSpec5 = Foldl.all((x: Int) => x % 2 == 0).foldl(Seq(2, 3, 6)) must_== false
  def allSpec6 = Foldl.all((x: Int) => x % 2 == 0).foldl(Seq()) must_== true
  def allSpec7 = Foldl.all(Function.const(true)).foldl(Seq()) must_== true

  def anySpec1 = Foldl.any(identity: Boolean => Boolean).foldl(Seq(true, true, false)) must_== true
  def anySpec2 = Foldl.any(identity: Boolean => Boolean).foldl(Seq[Boolean]()) must_== false
  def anySpec3 = Foldl.any(identity: Boolean => Boolean).foldl(Seq[Boolean](false, false)) must_== false
  def anySpec4 = Foldl.any((x: Int) => x % 2 == 0).foldl(Seq(1, 2, 3)) must_== true
  def anySpec5 = Foldl.any((x: Int) => x % 2 == 0).foldl(Seq(1, 1, 3)) must_== false
  def anySpec6 = Foldl.any((x: Int) => x % 2 == 0).foldl(Seq()) must_== false
  def anySpec7 = Foldl.any(Function.const(true)).foldl(Seq()) must_== false

  def headSpec1 = Foldl.head.foldl(Seq[String]("1", "2")) must_== Some("1")
  def headSpec2 = Foldl.head.foldl(Seq[String]()) must_== None


}
