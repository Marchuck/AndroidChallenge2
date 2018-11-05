package pl.marczak.androidchallenge2

import pl.marczak.androidchallenge2.model.MathResult
import pl.marczak.androidchallenge2.repository.CalculatorRepository
import java.math.BigDecimal

class MainPresenter(val repository: CalculatorRepository) {

    var view: MainView? = null

    var currentResult: MathResult? = null

    fun attachView(view: MainView?) {
        this.view = view

        currentResult?.let {
            renderResult(it)
        }
    }

    fun calculate(task: String) {
        val result = repository.calculate(task)
        renderResult(result)
    }

    private fun renderResult(result: MathResult) {
        currentResult = result
        if (result.isOk) {
            view?.showResult(result.value.toString())
        } else {
            view?.showError(result.errorMessage.toString())
        }
    }

    fun currentResult(value: String) {
        val result = MathResult(BigDecimal(value), true, null)
        renderResult(result)
    }
}