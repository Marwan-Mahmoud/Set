# Set
### Set supports the following operations:
- **Union** of two sets (U)
 - Example: A U B
- **Intersection** two of sets (N)
 - Example: A N B
- **Difference** of two sets (-)
 - Example: A - B
- **Complement** of a set (!)
 - Example: ! A
 - **Subset** of two sets (C)
 - Example: A C B

All names of the sets are the 26 alphabet uppercase letters except the universe set which has the name "UNIV".  
Entering the name of the set prints it.  
Entering "EXIT" terminates the program.  

### Sample Run
```
Enter input in format: {String1,String2,String3,...}.
Universe = {abc,bb,z,dd,ef,a0}
Enter number of sets = 2
You must enter elements that are in the "Universe" set.
Set A = {abc,a0}
Set B = {z,dd,abc}
Enter expression: A U B
C = A U B = {abc, a0, z, dd}
Enter expression: C
C = {abc, a0, z, dd}
Enter expression: ! C
D = ! C = {bb, ef}
Enter expression: C N D
E = C N D = {}
Enter expression: UNIV - D
F = UNIV - D = {abc, z, dd, a0}
Enter expression: UNIV N A
G = UNIV N A = {abc, a0}
Enter expression: A N B
H = A N B = {abc}
Enter expression: ! H
I = ! H = {bb, z, dd, ef, a0}
Enter expression: H C UNIV
true
Enter expression: A C B
false
Enter expression: A C F
true
Enter expression: F C A
false
Enter expression: UNIV C A
false
Enter expression: EXIT

```
