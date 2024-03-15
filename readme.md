<h2> CS 61B FALL 2020 </h2>

- [1. Intro Hello World Java](#1-intro-hello-world-java)
- [2. Defineing and Using Classes](#2-defineing-and-using-classes)
- [3. References, Recursion, and Lists](#3-references-recursion-and-lists)
- [4. SLLists, Nested Classes, Sentinel Nodes](#4-sllists-nested-classes-sentinel-nodes)
- [5. DLLists, Arrays](#5-dllists-arrays)
  
#### 1. Intro Hello World Java

#### 2. Defineing and Using Classes

- Static v.s. Non-static
  - Static methods are invoked using the class name, e.g. `Dog.makeNoise()`;
  - Instance methods are invoked using an instance name, e.g. `maya.makeNoise()`;
  - Static methods can’t access “my” instance variables, because there is no “me”.
  - Some classes are never instantiated. For example, `Math`.
  - A variable or method defined in a class is also called a member of that class. 
  - Static members are accessed using class name, e.g. `Dog.binomen`.
  - Non-static members cannot be invoked using class name: ~~`Dog.makeNoise()`~~
  - Static methods must access instance variables via a specific instance, e.g. `d1`. 
- public static void main(String[] args)
  - Command Line Arguments
  - Convert string to integer: `Integer.parseInt(args[0])`
- Using Libraries
  - The built-in Java libraries (e.g. `Math`, `String`, `Integer`, `List`, `Map`)
  - The Princeton standard library (e.g. `StdDraw`, `StdAudio`, `In`)

#### 3. References, Recursion, and Lists

- Primitive Types
  - 8 primitive types in Java: `byte`, `short`, `int`, `long`, `float`, `double`, `boolean`, `char`
  - when declaring a variable of a certain type
    - Your computer sets aside exactly enough bits to hold a thing of that type.
    - Java creates an internal table that maps each variable name to a location.
    - Java does NOT write anything into the reserved boxes.
      - For safety, Java will not let access a variable that is uninitialized.
  - The Golden Rule of Equals (GRoE)
      - Given variables $y$ and $x$: $y = x$ copies all the bits from $x$ into $y$.
- Reference Types
  - Everything else, including arrays, is a reference type.
  - Class Instantiations
    - When we instantiate an Object (e.g. Dog, Walrus, Planet):
      - Java first allocates a box of bits for each instance variable of the class and fills them with a default value (e.g. 0, null).
      - The constructor then usually fills every such box with some other value.
    - Can think of new as returning the address of the newly created object.
      - Addresses in Java are 64 bits.
      - Example (rough picture): If object is created in memory location 2384723423, then new returns 2384723423.
  - Reference Type Variable Declarations
    - When we declare a variable of any reference type (Walrus, Dog, Planet):
      - Java allocates exactly a box of size 64 bits, no matter what type of object.
      - These bits can be either set to:
        - Null (all zeros).
        - The 64 bit “address” of a specific instance of that class (returned by new).
    - The 64 bit addresses are meaningless to us as humans, so we’ll represent:
      - All zero addresses with “null”.
      - Non-zero addresses as arrows.
      - This is sometimes called “box and pointer” notation.
  - Reference Types Obey the Golden Rule of Equals
    - In terms of our visual metaphor, we “copy” the arrow by making the arrow in the b box point at the same instance as a.
- Parameter Passing
  - Passing parameters obeys the same rule: Simply **copy the bits** to the new scope.
- Instantiation of Arrays
  - Declaration creates a 64 bit box intended only for storing a reference to an int array. **No object is instantiated**. (declaration)
  - Instantiates a new Object, in this case an int array. Object is anonymous! (instantiation)
  - Puts the address of this new Object into the 64 bit box named a. (assignment)
- IntList and Linked Data Structures
- Old Deprecated Slides
  - Java is “Pass by Value”
    - The exact contents of the container in the outside world are delivered to the containers in the function. If the container has an arrow, so be it.
  - If a class doesn’t define equals, and you call equals, what happens?
    - It defaults to the implementation of equals in the “object” class (more on this later), but it uses `==`!

#### 4. SLLists, Nested Classes, Sentinel Nodes

- From IntList to SLList
- Public vs. Private Nested Classes
  - Use the `private` keyword to prevent code in other classes from using members (or constructors) of a class.
  - Nothing to do with protection against hackers, spies, and other evil entities.
  - Why Nested Classes?    
    - Nested Classes are useful when a class doesn’t stand on its own and is obviously subordinate to another class.
    - In my opinion, probably makes sense to make `IntNode` a nested private class.
  - Static Nested Classes
    - Static classes cannot access outer class’s instance variables or methods.
    - Results in a minor savings of memory.
    - Analogy: Static methods had no way to access “my” instance variables. Static classes cannot access “my” outer class’s instance variables. 
    - Unimportant note: For private nested classes, access modifiers are irrelevant.
- addLast() and size()
- Sentinel Nodes

#### 5. DLLists, Arrays

- Doubly Linked Lists
- Generic Lists
  - Java allows us to defer type selection until declaration.
- Arrays
  - Unlike classes, arrays do not have methods.
  - Like classes, arrays are (almost always) instantiated with new.
  - Arraycopy: `System.arraycopy(b, 0, x, 3, 2);`
    - Takes 5 parameters: Source array, Start position in source, Target array, Start position in target, Number to copy. 
- 2D Arrays
- Arrays vs. Classes
  - Arrays and Classes can both be used to organize a bunch of memory boxes.
    - Array boxes are accessed using [] notation.
    - Class boxes are accessed using dot notation.
    - Array boxes must all be of the same type.
    - Class boxes may be of different types.
    - Both have a fixed number of boxes.
  - Array indices can be computed at runtime.
  - Class member variable names CANNOT be computed and used at runtime.
  - The only (easy) way to access a member of a class is with hard-coded dot notation.





 