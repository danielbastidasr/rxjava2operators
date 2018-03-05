package co.danielbastidas.rxjavaoperators.operators

import android.app.Activity
import io.reactivex.Observable
import android.util.Log
import co.danielbastidas.rxjavaoperators.ZipExampleActivity
import io.reactivex.functions.Function4
import io.reactivex.schedulers.Schedulers


class ZipExample(private val activity: Activity) {

    //Creates a Observable that emits [str1,str2] after a random time of "computation"
    private fun getStrings(str1: String, str2: String): Observable<List<String>> {
        return Observable.create { emitter ->
            val activityZipActivity: ZipExampleActivity = activity as ZipExampleActivity

            val strings = ArrayList<String>()
            strings.add(str1)
            strings.add(str2)

            //simulating a heavy duty computational expensive operation(Randomly)

            var rand = Math.random()
            var time = 1

            time = if (rand < 0.3334)
                1
            else if (0.3334 < rand && rand < 0.66667)
                2
            else 3

            if(!emitter.isDisposed){
                try {
                    Thread.sleep((time*1000).toLong())
                    //Inform the activity that we finished with the operation
                    activityZipActivity.runOnUiThread(
                            { activityZipActivity.textView.text = activityZipActivity.textView.text.toString()+"\n"+"Finished :"+strings.toString() }
                    )
                    Log.d("Finished:",strings.toString())
                    emitter.onNext(strings)

                } catch (ex: InterruptedException) {
                    Log.d("Couldn't finish:",strings.toString()+" was interrupted!")
                }
            }
        }
    }

    private fun mergeStringListsOnceFinished(): Function4<List<String>, List<String>, List<String>, List<String>, List<String>> {
        return Function4 { strings, strings2, strings3, strings4 ->

            val finalStrings = ArrayList<String>()

            strings.forEach({
                finalStrings.add(it)
            })

            strings2.forEach({
                finalStrings.add(it)
            })

            strings3.forEach({
                finalStrings.add(it)
            })

            strings4.forEach({
                finalStrings.add(it)
            })

            finalStrings
        }
    }

    fun getZipObservableFrom():Observable<List<String>>{
        return Observable.zip(
                getStrings("One", "Two").subscribeOn(Schedulers.newThread()),
                getStrings("Three", "Four").subscribeOn(Schedulers.newThread()),
                getStrings("Five", "Six").subscribeOn(Schedulers.newThread()),
                getStrings("Seven", "Eight").subscribeOn(Schedulers.newThread()),
                mergeStringListsOnceFinished()
        )
    }

}