package co.danielbastidas.rxjavaoperators

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import co.danielbastidas.rxjavaoperators.operators.ZipExample
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class ZipExampleActivity : AppCompatActivity() {
    lateinit var textView: TextView
    private var disposables: CompositeDisposable = CompositeDisposable()

    private lateinit var transformationalOperators: ZipExample

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zip_example)

        transformationalOperators= ZipExample(this)
        textView = findViewById(R.id.alertTitle)
        textView.text = "Starting..."

        //Execute Example
        disposables.add(exampleZip())

    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }

    private fun exampleZip(): Disposable {
        return transformationalOperators.getZipObservableFrom()
                // After a second print the result of the 4 async operations
                .delay(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe { t -> textView.text = textView.text.toString()+"\n"+"Finished :"+t.toString()}
    }
}
