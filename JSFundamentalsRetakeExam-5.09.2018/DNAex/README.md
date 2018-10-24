Problem 03 -- DNAex
===================

*Every living creature on Earth has genomes in it body. The genome is
the total genetic material of an organism and includes both the genes
and non-coding sequences. The genome size, and the number of genes it
encodes varies widely between organisms (e.g. viruses and viroids have
the smallest amount of genomes).*

The next task of candidate-intern Tanya is called „Genome" and you have
to help. You will receive different amount of **encrypted** genes each
on a separate line. Until you receive a line "**Stop!**" you have to
decrypt the information given and save some data that you will need
later on. Each valid information about a gene should consist of:

-   **Name of the gene** may contain some of the following characters
    between its letters (!@\#\$?). Example for valid names:
    "!\@pro?\#line!\#" (proline)

-   **Length of the name** with a "=" before it. (e.g. =12; =5...)

-   **Count of genes** with a "\--" before it. (e.g. \--800; \--142)

-   **Organism that it belongs to with** "\<\<" before it. (e.g.
    \<\<cat; \<\<dog)

**Note: the info will be in that exact order. If something is missing
the input is invalid and you should ignore it. If the length of the name
does not match with the actual name given, the input is considered
invalid and you should ignore it.**

Examples for **valid** input:

"!cad\$\$he!rins!@=9\--30229\<\<human"

"!\@leu?\#cine!\#=7\--800\<\<cat"

Examples of **invalid** input:

"bx!=4\--421\<\<bison" -- the length 4 does not match with the actual
length

"\#nms!n\--126\<\<dog" -- the length is missing

**Store** the information about the genes and print all the organisms
you have encountered with their **total** amount of genes.

Input
-----

-   You will receive encrypted lines of input storing information about
    a gene until you receive "**Stop!**"

-   all names will always be **lower-case** characters, **only Latin
    letters**

-   the input will always be **valid** (no whitespaces)

Output
------

-   Print every organism with their total amount of genes (genome) in
    **descending order**

-   For every organism print "**{organism name} has genome size of
    {genes count}**"

-   If genes count equal -\> **save the order** of the input

Constraints
-----------

-   all numbers will be in range \[1 - 10000\]

-   name will be string \[1 - 1000\]
