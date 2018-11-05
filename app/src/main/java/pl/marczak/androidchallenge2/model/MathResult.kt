package pl.marczak.androidchallenge2.model

import java.math.BigDecimal

data class MathResult(val value: BigDecimal?,
                      val isOk: Boolean,
                      val errorMessage: String?)
