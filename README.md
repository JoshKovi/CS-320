# CS-320
This is a piece of the overall projects completed throughout the course.


How can I ensure that my code, program, or software is functional and secure?

I can use automated unit testing to ensure my code is functional and secure. By intentionally trying
to break my code with unit tests, and validating that the code performs appropriately whether that
be in edge cases or normal operation I can have a degree of confidence my code will perform as expected.
While it is unlikelyy that a complex piece of software will every be deployed completely bug free or completely
secure, unit testing can drastically improve those odds, allowing you to test each bit of code for failure points
or security issues. Additionally, after unit tests are initially written for a MVP (minimuum viable product) those
unit tests can be updated efficiently ensuring that each update does not break the software in unexpected ways.

How do I interpret user needs and incorporate them into a program?

The most important thing in understanding user needs is understanding the intent behind them.As an example, while
a user requirement may not specifically require a phone number to be validated as 10 digits , its a good bet that 
the intent of that requirement is to have a 10 digit number, rather than someone spelling out each number. It is 
also important to realize early on when user requirements are too vague or when agreed upon functionality does not
make sense to implement. For example, a user may want to be able to store credit card information in your software,
buut at the same time only intends to accept paypal payments, or has no intent on recieiving payments in the 
foreseable future. At this point of recognition, it is important to go back and clarify these requirements, as the
user may want functionality that was not initially described, or may have accidently included a requirement that 
was scrapped prior to setting the requirements. Another thing is to ensure you have adequete detail of the user need before
moving forward in the development and testing process. For example, if you recieve a bug report that says that your payment
system will not accept a payment from a particular user, it would be wise to check to ensure there was no user error involved
(for example entering the wrong zip code, or the wrong expiration date) before spending countless hours trying to find a bug that 
either does not exist, or is a result of conflicting software (such as google auto inputing the wrong card for payments). 

How do I approach designing software?

I think that a mix of test driven development and tested development is a good approach to designing software. Either way,
the first priority is to gather and review/anaylze software requirements. If working on a something like a new feature for an existing 
application it may make more sense to develop the test cases prior to the code, and vice versa in the case of working on an initial 
proof of concept/prototype of a technology, where you may want to test the idea before testing the code. I think it is critical to remain
flexible in this regard because, as a software developer your role is to solve problems with code, not necessarily to code for a soluution. 
If a certain design philosophy works for 1 project, that does not mean that will be the best solution for the next project. I equate it to 
finding the technology stack that best handles your project, not using a particular tech stack just because you like it even when it does not
make sense. There is a reason that graphically intense games are typically buuilt in C/C++ and interactive websites use JavaScript and JS Frameworks.
While you can build a website in C++, it does not really make sense to do so, just like building a big game in JS is possible, but definitely not an
ideal solution in almost any case.

