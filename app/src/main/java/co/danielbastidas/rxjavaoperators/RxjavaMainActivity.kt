package co.danielbastidas.rxjavaoperators

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class RxjavaMainActivity : AppCompatActivity() {

    lateinit var textView:TextView

    var disposables:CompositeDisposable = CompositeDisposable()

    lateinit var transformationalOperators:TransformationalOperators

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rxjava_main)

        transformationalOperators= TransformationalOperators(this )
        textView = findViewById(R.id.alertTitle)
        textView.text = ""

        // After 5 seconds print the result of the 4 async operations
        disposables.add(exampleZip())


    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }


    private fun exampleZip(): Disposable {
        return transformationalOperators.getZipObservableFrom()
                .delay(5, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe { t -> textView.text = textView.text.toString()+"\n"+"Finished :"+t.toString()}
    }




}
