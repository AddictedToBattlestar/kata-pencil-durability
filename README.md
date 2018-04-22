### April 20th, commit: 5e69e5f

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

### April 22nd, commit 57c9bb3

It's weird to see the eraser change text written and leaving gaps ("Some simple text" --> "Some        text").  I am
doing that deliberately as I see that is the behavior that erasers have on paper in the real world.

Also, I really dislike having to implement the builder pattern to overcome various optional parameters in the 
constructor of the PencilWriter.  I was tempted repeatedly to just provide setters for those properties which would be 
simpler but not fitting the requirement of what is called out in the readme file for the kata.

I am really happy that I broke out the writing of the pencil and the retrieval of the written text.  I haven't yet gone
so far as to implement a Paper object as I mentioned on Friday but none the less I am much more comfortable with the
code written now.

It really feels like the future work on the eraser is going to evolve to where it will share logic with the pencil
degradation logic written earlier.