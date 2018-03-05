package co.danielbastidas.rxjavaoperators.operators


import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import java.util.concurrent.TimeUnit


class FlatMapExample {

    val before:String  = "Item Before map "

    /*
        Observable("Item Before map")  -> Map Function -> Observable("String Transformed with Map Operator")
     */
     fun getMapExample(): Observable<String> {

        return Observable.just(before)
                .map { _ ->
                        "String Transformed with Map Operator"
                }
    }

    /*
        Observable("Item Before map")  -> FlatMap Function -> Observable("Item Before map +", "Item Before map ++", "Item Before map +++")
     */
    fun getFlatMapExample(): Observable<String> {
        return Observable.just(before)
                .flatMap {
                    str ->
                            Observable.just(str+"+", str+"++", str+"+++")
                                    .zipWith(
                                            Observable.interval(1, TimeUnit.SECONDS),
                                            BiFunction {string:String, _:Any -> string}
                                    )
                }

    }

}