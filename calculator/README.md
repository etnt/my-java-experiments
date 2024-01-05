# A simple calculator
> First time I've ever done something with Kotlin

## Compile and Run

```shell
# Compile
> mvn compile

# Run
> mvn clean install exec:java

# Run the unit tests
> mvn test
```

## Some notes

I had started to make a calculator in Java with the help
of the Github Co-Pilot; but after having finished the
Dungeons game in Java, I thought I should try another
language that runs on the JVM. So I started out by asking
the Co-Pilot to transform my Java program into Kotlin. 

Initially I tried to get the Gradle build system to work
on my Github Spaces but I gave up and returned to use Maven.

At that time I also tried to get the Co-Pilot to work
on a local installation of VSCode running on my Mac;
I got that to work and continued working from there. 

I added multi-digit numbers and precedence handling, which
I got to work after some minor struggle to get it right.

Then I added Unit tests, and again Co-Pilot made it easy.
But after I asked it to generate Unit tests for all the
exceptions I potentially could raise, I ended up struggling
with a crash due to the use of the `assertThrows`. The solution
finally was to use `assertFailsWith` instead.

Finally, I added an input loop, prompting for arithmetic expressions.

Again, I'm blown away by how quickly I could become productive
with no previous knowledge of Kotlin (although, 40y working with
software admittedly gives you some good base to start from...).

## Example

```shell
Enter an arithmetic expression (or press Enter to quit): 45 + 5 * 2
The answer is: 55.0
Enter an arithmetic expression (or press Enter to quit): 4 * ( 1 + 2 ) - 1 * 3
The answer is: 9.0
Enter an arithmetic expression (or press Enter to quit): 4*(1+2)-1*3
The answer is: 9.0
Enter an arithmetic expression (or press Enter to quit): 34 + (4 - 2
Error: Unbalanced parentheses in the expression.
Enter an arithmetic expression (or press Enter to quit): 34 + eee
Error: Invalid character in expression: 'e'
```


