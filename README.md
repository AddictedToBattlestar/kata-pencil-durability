### April 20th - Notes on the work so far (commit: 5e69e5f)

I am feeling that the write method for PencilWriter should not be returning the written text from the page.
I am hoping that in future commits I can maybe introduce a "Paper" object into the PencilWriter which will be written
to and then can be examined by the tests in the assertions.  I think that direction would feel closer to how this would
behave in the real world.

... a bit later that day

- If the pencil is given two sets of text in succession to write where the first text terminates with a space, should the
pencil writer continue to provide a space between the two sets of text?
- How should tabs be treated when it comes to pencil durability?  I am guessing this is not a concern because how would a
pencil write a tab in the first place but I am not sure if this is a bad assumption.
- If uppercase characters cost 2 points of durability, perhaps punctuation marks such as periods should cost a fraction 
of a point?