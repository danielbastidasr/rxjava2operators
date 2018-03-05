package co.danielbastidas.rxjavaoperators

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import co.danielbastidas.rxjavaoperators.operators.FlatMapExample
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class FlatMapExampleActivity : AppCompatActivity() {

    private lateinit var textViewBeforeMap:TextView
    private lateinit var textViewAfterMap:TextView
    private lateinit var textViewAfterFlatMap:TextView

    private var mapFlatMapExample:FlatMapExample = FlatMapExample()

    private var disposables: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flat_map_example)

        textViewBeforeMap = findViewById(R.id.before_map)
        textViewAfterMap = findViewById(R.id.after_map)
        textViewAfterFlatMap = findViewById(R.id.after_flatmap)

        textViewBeforeMap.text = mapFlatMapExample.before
        textViewAfterFlatMap.text = "Starting..."

        //Use all Observables
        disposables.add(mapObservable())
        disposables.add(flatMapObservable())

    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }

    private fun mapObservable():Disposable{
        return mapFlatMapExample.getMapExample()
                .subscribe{
                    string ->
                        textViewAfterMap.text = string
                }
    }
    private fun flatMapObservable():Disposable{
        return mapFlatMapExample.getFlatMapExample()

                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{
                    string ->
                        textViewAfterFlatMap.text = textViewAfterFlatMap.text.toString() + "\n"+string
                }
    }
}
