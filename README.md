# TransitiveDependencies

Write some code that determines the full set of transitive dependencies
for a group of items. The code takes as input a set of lines where the
first token is the name of an item. The remaining tokens are the names
of things that this first item depends on. Given the following input, we
know that A directly depends on B and C, B depends on C and E, and so
on.

A B C <br />
B C E <br />
C G <br />
D A F <br />
E F <br />
F H


The program should use this data to calculate the full set of
dependencies.

The output of the program for the above input should look like:

A B C E F G H <br />
B C E F G H <br />
C G <br />
D A B C E F G H <br />
E F H <br />
F H <br />


Optional extra

Extend your program so that it can also be used to calculate inverse
dependencies

(ie, determine the set of items that depend on each item)
