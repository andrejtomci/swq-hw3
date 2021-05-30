# Brain Method Check For checkstyle

This checkstyle custom rule is made from a "master check" the BrainMethod, and some "puppet checkers":

* Cyclomatic complexity check
* Logic nesting depth check
* Method length check
* Variable quantity check - counts parameters and locL variables

The puppet checkers have to implement the interface `SimpleCheckerInterface` which requires two additional `acceptVisitor` methods. This is due to the fact that some checkers might need to set some options or update their fileContent, but not all. Due to this We cosnidered adding methods to the interface, but that would make it broader than needed. If we added these methods to the implementing classes we would violate `DIP` and we would need to use `instanceof` in the `BrainMethodCheck`. So we rather opted for the visitor solution.

This means if we want to add something a new option to the puppet checker, we just Implement a new visitor, and add the required method to the **one implementing class**.

If we want to add a new checker to our main, we just implement the Interface all checkers use.

The checker can be tested with the files/archives provided in the `runnable` folder. It contains the checkstyle and custom check `.jar`, a configuration file for checkstyle, and two test `.java` files.

On linux `test.sh` does the testing automatically.
