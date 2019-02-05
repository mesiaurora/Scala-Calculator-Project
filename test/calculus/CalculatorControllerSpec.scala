package calculus

import org.scalatest.concurrent.{IntegrationPatience, ScalaFutures}
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneServerPerSuite
import play.api.test.FakeRequest


class CalculatorControllerSpec extends PlaySpec with GuiceOneServerPerSuite with ScalaFutures with IntegrationPatience {

  "CalculatorController" should {

    "Return correct values" in {

      val controller : CalculatorController = app.injector.instanceOf(classOf[CalculatorController])
      val request = FakeRequest()
      val result = controller.calculus("1+1").apply(request)
      // Test successful request
      assert(result.value.get.isSuccess)
    }

  }
}
