package pl.marczak.androidchallenge2

interface MainView {

    fun showError(message: String)

    fun showResult(message: String)

    fun setCurrentInput(it: String)

}