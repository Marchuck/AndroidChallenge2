package pl.marczak.androidchallenge2

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.reset
import com.nhaarman.mockitokotlin2.verify
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import pl.marczak.androidchallenge2.model.MathResult
import pl.marczak.androidchallenge2.repository.CalculatorRepository
import java.math.BigDecimal

class MainPresenterTest : Spek({

    val errorMessage = "error evaluate expression"

    val view: MainView = mock {}

    val okExpression = "2+2*2"
    val badExpression = "22**2"

    val okDependency: CalculatorRepository = mock {
        on { calculate(okExpression) } doReturn MathResult(BigDecimal("6"), true, null)
    }
    val badDependency: CalculatorRepository = mock {
        on { calculate(badExpression) } doReturn MathResult(null, false, errorMessage)
    }

    val okPresenter = MainPresenter(okDependency)

    val badPresenter = MainPresenter(badDependency)

    given("good dependency") {
        beforeEachTest {
            reset(view)
            okPresenter.attachView(view)
        }

        on("evaluate valid expression") {
            okPresenter.calculate(okExpression)

            it("renders result") {
                verify(view).showResult("6")
            }
        }
    }

    given("bad dependency") {
        beforeEachTest {
            reset(view)
            badPresenter.attachView(view)
        }

        on("evaluate invalid expression") {
            badPresenter.calculate(badExpression)

            it("renders error result") {
                verify(view).showError(errorMessage)
            }
        }
    }

})
