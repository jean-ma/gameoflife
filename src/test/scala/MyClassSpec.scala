
import org.scalatest.FlatSpec

class MyClassSpec extends FlatSpec {
  "Comparison to 43" should "fail" in {
    assert(Test.a == 43)
  }
}