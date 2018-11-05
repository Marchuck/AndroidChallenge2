package pl.marczak.androidchallenge2.repository

import com.udojava.evalex.Expression
import pl.marczak.androidchallenge2.model.MathResult
import pl.marczak.androidchallenge2.model.Symbol
import java.math.BigDecimal
import java.util.*
import kotlin.collections.HashMap

class CalculatorRepositoryImpl : CalculatorRepository {

    val precision = 4

    val operators: HashMap<String, Symbol> = HashMap()

    init {
        for (sym in Symbol.values()) {
            operators[sym.symbol] = sym
        }
    }

    override fun calculate(task: String): MathResult {
        val expression = Expression(task)

        try {
            val decimal = calculateImpl(task)
            expression.setPrecision(precision)
            return MathResult(
                    value = decimal,
                    isOk = true,
                    errorMessage = null
            )

        } catch (x: Exception) {
            return MathResult(
                    value = null,
                    isOk = false,
                    errorMessage = x.message
            )
        }
    }

    private fun calculateImpl(task: String): BigDecimal {
        return Expression(task).eval()
    }
}