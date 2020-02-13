## STARS

### Description
Through the CLI (with some basic GUI functionality), this program solves a set of problems provided as part of a piece of coursework at the University of Hertfordshire. The Java IDE used was BlueJ, which is why the formatting of the files is unconventional. In order to run this locally you will need to install BlueJ.

### Error handling
Although some checking was done on certain methods, both the CLI and the GUI interactions with the underlying methods are susceptible to crashing due to a lack of error handling. The decision was made not to include this for ‘formatting’ errors e.g. the user entering a string instead of an integer, as it was not a part of the specification and could easily be added in a future version.

Error handling was, however, implemented for some of the later methods such as ‘convertLoyalty’, where checks were put in place to assert that the ‘Card’ passed to the function was of the correct type before being downcast to that type. This handling was done in the ‘ResortManager’ class as well as the interfaces, as it was decided that this was the most thorough way to handle the error whilst bearing in mind future editions of the code. 

### Approach to testing
The testing of the application was based on running through all of the options offered by the CLI, as this was determined to be a thorough investigation and a good basis for exploring many different areas of the application. Testing was done by running parts of the code in the ‘MyTester’ class and outputting the results to the screen. 


