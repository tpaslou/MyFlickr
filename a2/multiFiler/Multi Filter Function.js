function makeMultiFilter(array) {
    "use strict";
 // What we track
    var originalArray = array, currentArray;
    currentArray = originalArray;
    return function arrayFilterer(pred, callback) {
 // If filter not a function return current Array
        if (typeof pred !== "function") {
            return currentArray;
        }
 // Filter out things
        currentArray = currentArray.filter(pred);
 // If callback is a function, execute callback
        if (typeof callback === "function") {
            callback.call(originalArray, currentArray);
        }
        return arrayFilterer;
    }
}
 // !!! THE CODE THAT FOLLOWS USES THE makeMultiFilter function
 // arrayFilterer can be used to repeatedly filter this input array
var arrayFilterer = makeMultiFilter([1, 2, 3]);

 // call arrayFilterer to filter out all the numbers not equal to 2
arrayFilterer(function (elem) {
    "use strict";
    return elem !== 2; // check if element is not equal to 2
}, function (currentArray) {
    "use strict";
    console.log(this); // prints [1,2 3]
    console.log(currentArray); // prints [1, 3]
});

 // call arrayFilterer filter out all the elements not equal to 3. No callback used
arrayFilterer(function (elem) {
    "use strict";
    return elem !== 3; // check if element is not equal to 3
});

 // call arrayFilterer with no filter should return the current array
var currentArray = arrayFilterer();
console.log("currentArray", currentArray); // prints [1] since we filtered out 2 and 3

 // Since arrayFilterer returns itself filters calls can be chained like:
function filterTwos(elem) {  "use strict"; return elem !== 2; }
function filterThrees(elem) {  "use strict"; return elem !== 3; }
var currentArray = makeMultiFilter([1, 2, 3])(filterTwos)(filterThrees)();
console.log("currentArray", currentArray); // prints [1] since we filtered out 2 and 3

 // Multiple active filters at the same time
var arrayFilterer1 = makeMultiFilter([1, 2, 3]);
var arrayFilterer2 = makeMultiFilter([4, 5, 6]);
console.log(arrayFilterer1(filterTwos)()); // prints [1,3]
console.log(arrayFilterer2(filterThrees)()); // prints [4,5,6]