# RxJava Operators
We have used the following operators with its own example or possible use case:
- Transformational Operators such us: flatmap, concat, switchmap, zip ...
- Conditional and Boolean Operators: amb, defaultIfEmpty, exist, isEmpty...

## Use cases

### TransformationalOperators

We can see some examples implementing solutions to common problems using transformational operators:


#### Wait for 4 Observables to finish and execute when they finished a function.

- Implemented Zip operation for this purpose
- mergeStringListsOnceFinished finally is the Function4 that is in charge of waiting for the observables.
- getStrings(String A , String B) simply simulates an operation and then send List of 2 strings . [A,B]

  Zip -> combine the emissions of multiple Observables together via a specified function and emit single items for each combination based on the results of this function

#### Replace/Add/Modify the state of an Observable with Map and FlatMap

- Map is used for the following purpose : Observable("Item Before map")  -> Map Function -> Observable("String Transformed with Map Operator")

- FlatMap is used for the following purpose : Observable("Item Before map")  -> FlatMap Function -> Observable("Item Before map +", "Item Before map ++", "Item Before map +++")
