package pl.marczak.androidchallenge2

import pl.marczak.androidchallenge2.model.MathResult
import pl.marczak.androidchallenge2.repository.CalculatorRepository
import java.math.BigDecimal
import java.text.DecimalFormat

class MainPresenter(val repository: CalculatorRepository) {

    val decimalFormat = DecimalFormat("0.0000")


    var view: MainView? = null

    var currentResult: MathResult? = null
    var currentInput: String? = null

    fun attachView(view: MainView?) {
        this.view = view

        currentResult?.let {
            renderResult(it)
        }
        currentInput?.let {
            view?.setCurrentInput(it)
        }
    }

    fun calculate(task: String) {
        val result = repository.calculate(task)
        renderResult(result)
    }

    private fun renderResult(result: MathResult) {
        currentResult = result
        if (result.isOk) {
            view?.showResult(result.value!!.formatWell())
        } else {
            view?.showError(result.errorMessage.toString())
        }
    }

    fun BigDecimal.formatWell() = decimalFormat.format(this)

    fun clearResult() {
        currentResult = null
        view?.setCurrentInput("")
        view?.showResult("")
    }
}