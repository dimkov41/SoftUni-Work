Problem 4 -- F1 Championship
============================

Write a JavaScript program that **calculate and display** F1 teams and
pilots points in specific format.

As **input** you will receive an array of strings.

Each string will consist of the following information with format:

"Team name -\> Pilot name -\> Pilot points"

Team name will always be valid string, Pilot name will also be valid
string, Pilot points will be a number. Your task is to print the result
like the example below. Every pilot can take points from more than one
championship. Every team will have only two pilots, no more, no less.
Every pilot will be only in one team.

### Input

You will receive **one argument --** an **array strings** as shown
above.

### Output

Print on the **console** the **top 3 teams** with biggest sum of **pilot
points** as the **first criteria** and after that sort the **pilots in
every team** depending on their points.

### Constraints

-   The **number** of **points** in the **input argument** will be valid
    positive number

-   There **will** be **no invalid** **input**

-   There **will** be **no teams or pilots with equal points**

### Examples

+-------------------------------------------+
| **Input**                                 |
+===========================================+
| \[\"Ferrari -\> Kimi Raikonnen -\> 25\",  |
|                                           |
| \"Ferrari -\> Sebastian Vettel -\> 18\",  |
|                                           |
| \"Mercedes -\> Lewis Hamilton -\> 10\",   |
|                                           |
| \"Mercedes -\> Valteri Bottas -\> 8\",    |
|                                           |
| \"Red Bull -\> Max Verstapen -\> 6\",     |
|                                           |
| \"Red Bull -\> Daniel Ricciardo -\> 4\"\] |
+-------------------------------------------+
| **Output**                                |
+-------------------------------------------+
| Ferrari: 43                               |
|                                           |
| \-- Kimi Raikonnen -\> 25                 |
|                                           |
| \-- Sebastian Vettel -\> 18               |
|                                           |
| Mercedes: 18                              |
|                                           |
| \-- Lewis Hamilton -\> 10                 |
|                                           |
| \-- Valteri Bottas -\> 8                  |
|                                           |
| Red Bull: 10                              |
|                                           |
| \-- Max Verstapen -\> 6                   |
|                                           |
| \-- Daniel Ricciardo -\> 4                |
+-------------------------------------------+
