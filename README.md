# PrimeFactorizer: A Comprehensive Prime Calculation Tool

## Overview

PrimeFactorizer is a tool built to compute prime numbers within a defined range. The application not only discerns prime numbers but also distinguishes composite numbers, storing them separately for ease of reference. The prime numbers are stored in a list, while the composite numbers are organized in a map.

## Getting Started

To commence the operation of PrimeFactorizer, simply run the main method located in the 'Driver' class. The application is designed to guide users through the process from thereon.

## Features

PrimeFactorizer is equipped with four distinct operational modes to cater to various computational needs:

    1.) Single Threaded: Operates the entire process on a single thread.
    2.) Multi Threaded (Unbounded): Executes the process on multiple threads without any limits.
    3.) Multi Threaded (Bounded Threadpool): Performs the process on a limited number of threads.
    4.) Multi Threaded (Bounded Threadpool Callable): Runs the process on a limited number of threads, with the ability to return a result.

Upon selection of a mode, the application promptly returns the computation time. For debugging purposes or to verify the correctness of the operation, users can navigate to the Factorizer classes and uncomment the print statements to view the contents of the collections.

## Additional Content

Included in the repository is the source code for a prime sieve. Though it isn't an official part of the PrimeFactorizer project due to slower performance results, it serves as an interesting study for those interested in prime calculation methods. Please note that the documentation for this class is minimal as it's not part of the core application.

## Enhanced Version

An enhanced version of PrimeFactorizer is available, offering a Graphical User Interface (GUI) and real-time results display. This version, while performing the same prime calculations, does not create any prime data structures or factor maps. A key feature of this enhanced version is the ability to cancel computations mid-process via the GUI. The results are tallied and displayed on the GUI in real-time during the computation process.

## References

This program is a product of extensive research and personal ingenuity. While various sources have been referenced for numerical theorems and definitions, the implementation is the result of independent design and craftsmanship. The referenced sources are as follows:

[1] F. Maths, "All Prime Numbers are Nearly Divisible by 6 !," 7 December 2018. [Online]. Available: [Link](https://www.youtube.com/watch?v=5CUvf675-6o&ab_channel=FlammableMaths). 

[2] "Most efficient way to find factors of a number - C++ and Python Code," Rookieslab.com, 14 January 2017. [Online]. Available: [Link](https://www.rookieslab.com/posts/most-efficient-way-to-find-all-factors-of-a-number-python-cpp).
