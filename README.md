Download the repository

Run the **whole** test suite, not individual test classes.
`gradle app:testDebugUnitTest`

Observe either `MainActivityTest` or `HomeScreenTest → auto view model` test fails, depending on the execution order.
Do not enable parallel run.
