import scala.reflect.mirror._

object Test extends App {
  val toolbox = mkToolBox()

  val tree1 = Block(
    Import(Select(Ident(newTermName("scala")), newTermName("Predef")), List(ImportSelector(nme.WILDCARD, -1, null, -1))),
    Apply(Select(Literal(Constant(1)), newTermName("$minus$greater")), List(Literal(Constant(2))))
  )
  val ttree1 = toolbox.typeCheck(tree1, withImplicitViewsDisabled = false)
  println(ttree1)

  try {
    val tree2 = Block(
      Import(Select(Ident(newTermName("scala")), newTermName("Predef")), List(ImportSelector(nme.WILDCARD, -1, null, -1))),
      Apply(Select(Literal(Constant(1)), newTermName("$minus$greater")), List(Literal(Constant(2))))
    )
    val ttree2 = toolbox.typeCheck(tree2, withImplicitViewsDisabled = true)
    println(ttree2)
  } catch {
    case ex: Throwable =>
      println(ex)
  }
}