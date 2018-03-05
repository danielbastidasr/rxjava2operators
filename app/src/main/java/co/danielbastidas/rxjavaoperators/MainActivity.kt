package co.danielbastidas.rxjavaoperators

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class RxjavaMainActivity : AppCompatActivity() {

    private lateinit var buttonZip:Button
    private lateinit var buttonFlatMap:Button

    private var disposables: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rxjava_main)

        buttonZip = findViewById(R.id.button_zip_example)
        buttonFlatMap = findViewById(R.id.button_map_flatmap)

        //Initialise clicks
        disposables.add(observeClickZipExample())
        disposables.add(observeClickFlatMapExample())

    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }

    //ButtonsObservables
    private fun observeClickZipExample(): Disposable {
        return RxView.clicks(buttonZip).subscribe(
                {
                    startZipExampleActivity()
                }
        )
    }
    private fun observeClickFlatMapExample(): Disposable {
        return RxView.clicks(buttonFlatMap).subscribe(
                {
                    startFlatMapExampleActivity()
                }
        )
    }

    //ACTIVITIES WITH THE EXAMPLE
    private fun startZipExampleActivity(){
        var intent = Intent(this, ZipExampleActivity::class.java)
        startActivity(intent)
    }
    private fun startFlatMapExampleActivity(){
            var intent = Intent(this, FlatMapExampleActivity::class.java)
            startActivity(intent)
    }
}

