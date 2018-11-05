package pl.marczak.androidchallenge2.model

import android.support.annotation.IdRes
import pl.marczak.androidchallenge2.R

enum class Symbol(val symbol: String, @IdRes val res: Int) {

    ADD("+", R.id.add),

    SUBSTRACT("-", R.id.substract),

    DIVIDE("/", R.id.divide),

    MULTIPLY("*", R.id.multiply),

    LEFT_BRACKET("(", R.id.left_bracket),

    RIGHT_BRACKET(")", R.id.right_bracket);

}