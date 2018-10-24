Problem 2 -- F1 Race
====================

The race for the Grand Prix title of Sofia is about to start and all the
pilots are in their positions! You are given an array of strings. The
first string contains all the pilots in the race, separated by
whitespace. The next strings contain actions with information about what
happens in the race. Your task is to write a JS function that prints the
final result of the race.

You may receive the following actions:

-   Join {pilot}

-   Crash {pilot}

-   Pit {pilot}

-   Overtake {pilot}

If you receive the **Join action**, you should **add** the pilot at last
position in the race, but only **if** he isn\`t in the race already.

If you receive the **Crash action**, the pilot crashes and you must
**remove** him **if** he is in the race.

If you receive the **Pit action**, the pilot stops in the pit and you
must **move** him one place down **if** he is in the race.

If you receive the **Overtake action**, the pilot overtakes the pilot in
front of him and you must **move** him one place up **if** he is in the
race.

Input / Consrtaints
-------------------

You will receive an **array of strings**.

-   In the **first input element,** you will receive all the pilots in
    the race -- sequence of pilot names, separated by space.

-   Each of the next elements will be an **action**.

-   There will always be at least one pilot in the race.

Output
------

-   As output you must print all the pilots, separated by **" \~ "**, at
    the end of the race.

Constraints
-----------

-   The **actions will always be valid.**

-   The **pilot will be a string containing only letters from the
    alphabet**.

<!-- -->

-   Allowed working **time** / **memory**: **100ms** / **16MB**.
