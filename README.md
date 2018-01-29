# Passenger satisfaction optimization algorithm 

To build and call this program, respectively, run:
 
    ./gradlew jar   
    java -jar build/libs/satisfaction.jar examples/spec1.txt 

Where `examples/spec1.txt` is the input file. Formats are described above.  

## The main classes

The main classes are the `Specification` that manages the parsing of input file, the `DistributionCalculator` that generates the `Distribution` of passengers and the `SatisfactionIndexCalculator` that calculates the satisfaction of passengers. 

## Input file

The input must be a text file which represents the specification of a passenger booking. This file must adhere to following these rules:
  - The file must have at least the first line.
  - The first line must be two integers indicating the number of rows and the number of columns of the plain.
  - Each subsequent line represents the specification of a group of users. Each word in the line represents the specification of a single user. The format of those words is a natural number optionally followed by the 'W' letter. The number is the passenger number. The 'W' letter indicates the preference of a user to be in window seats.     

## Output

THe output is the matrix with the distribution, in addition to the satisfaction index.

## Comments

Some improvements could be done to this algorithm. For example, to prioritize groups with window preference, and then tray to maintain the order of input specification file.   
