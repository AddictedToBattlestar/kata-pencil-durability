### April 20th - Notes on the work so far (commit: 5e69e5f)

I am feeling that the write method for PencilWriter should not be returning the written text from the page.
I am hoping that in future commits I can maybe introduce a "Paper" object into the PencilWriter which will be written
to and then can be examined by the tests in the assertions.  I think that direction would feel closer to how this would
behave in the real world.