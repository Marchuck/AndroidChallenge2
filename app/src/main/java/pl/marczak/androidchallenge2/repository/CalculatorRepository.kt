package pl.marczak.androidchallenge2.repository

import pl.marczak.androidchallenge2.model.MathResult

interface CalculatorRepository {

    fun calculate(task: String) : MathResult

}