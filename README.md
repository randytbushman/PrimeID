# PrimeFactorizer
Concurrent Programming class.

# Assignment 1

The PrimeFactorizer is a tool used to calculate the primes in a given interval. All prime numbers shall be stored in a list, while all composite numbers shall be stored in a map. 

The user only needs to run the main method in the Driver class. The program will help you from there.

PLEASE GRADE HARSHLY SO THAT I CAN LEARN THE BEST WAY TO IMPLEMENT THIS

This program comes equipped with 4 modes:

    1.) Single Threaded
    2.) Multi Threaded (Unbounded)
    3.) Multi Threaded (Bounded Threadpool)
    4.) Multi Threaded (Bounded Threadpool Callable)
    
The computation time is returned as output each time the user selects a mode. To show correctness or to debug, the user enter the Factorizer classes and uncomment the print statements inside to show the contents of the collections.

Feel free to check out the prime sieve code as well! It is not part of the official project as it produced slower results than my previous solution. Documentation in that class is limited as it is not part of the official program.

# Assignment 2

Essentially the same as Assignment 1, however, no prime data structures or factor maps are created. This assignment also has a GUI which allows for cancellation during calculation. All the primes from a given range are tallied, then displayed in real time on the GUI during computation.


# References
Please note that while sources with code were used to create this program, the implementation is done entirely by me and my own design and tradecraft. I used only the numerical theorems and definitions and not the code for reference.

[1] 	F. Maths, "All Prime Numbers are Nearly Divisible by 6 !," 7 December 2018. [Online]. Available: https://www.youtube.com/watch?v=5CUvf675-6o&ab_channel=FlammableMaths. [Accessed 29 October 2021].

[2] 	"Most efficient way to find factors of a number - C++ and Python Code," Rookieslab.com, 14 January 2017. [Online]. Available: https://www.rookieslab.com/posts/most-efficient-way-to-find-all-factors-of-a-number-python-cpp. [Accessed 29 October 2021].
