package pl.marczak.androidchallenge2

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import pl.marczak.androidchallenge2.model.Symbol
import pl.marczak.androidchallenge2.repository.CalculatorRepositoryImpl

class MainActivity : AppCompatActivity(), MainView {

    companion object {
        //todo: use dependency injection like koin or dagger2
        val presenter = MainPresenter(CalculatorRepositoryImpl())
    }

    lateinit var editText: EditText
    lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.input_field)
        resultTextView = findViewById(R.id.resultTextView)

        Symbol.values().map { symbol ->
            findViewById<Button>(symbol.res).setOnClickListener { _ ->
                editText.setText(editText.text.toString() + symbol.symbol)
            }
        }

        findViewById<Button>(R.id.reset).setOnClickListener {
            presenter.clearResult()
        }

        findViewById<Button>(R.id.result).setOnClickListener {
            presenter.calculate(editText.text.toString())
        }

        editText.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                editText.setSelection(editText.text.length)
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                presenter.currentInput = p0.toString()
            }

        })

        presenter.attachView(this)
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun showResult(message: String) {
        resultTextView.text = message
    }

    override fun setCurrentInput(it: String) {
        editText.setText(it)
    }


}
