Reasoned TAPL
=============

-----------------------------------------------
[namin]((http://github.com/namin)'s fork README
-----------------------------------------------

I forked this project in order to learn about cKanren/core.logic, and
stubornly see for myself their limitations in expressing type
systems. I also intend to explore nominal logic programming à la
αKanren.

--------------------------------------------------------------
[webyrd](http://www.cs.indiana.edu/~webyrd/)'s original README
--------------------------------------------------------------

Translation of Benjamin C. Pierce's excellent 'Types and Programming Languages' (MIT Press, 2002) into miniKanren/cKanren (R6RS Scheme) and core.logic (Clojure).

This is an exercise to help me learn more about types, better understand the strengths and limitations of miniKanren, decide which constraints should be added to cKanren, and learn Clojure and core.logic

Thanks to Dan Friedman for suggesting this exercise years ago, and to David Nolen for help with core.logic, core.match, and general Clojuring.

### Update -- 29 Sept 2012 ###

Seems like a few people have found this project, which stalled when I hit a giant snag--I wanted to implement the set-theoretic approach used in some of Pierce's early programs.  It turns out that implementing general set operations in a pure logic system, when the domain is not already known, is an extremely difficult open problem.  I might try again using constraint logic programming over finite domains (CLP(FD)), which might work for the purpose of these exercises. But full set operations (union, intersection, difference, comparison, membership, etc.) when sets can be represented as *logic variables* is a much, much harder problem.

Anyway, I hope to get back to this project early next year.  The project has already helped me, since I'm always looking for problems miniKanren handles poorly.

--Will
