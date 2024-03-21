<h2> CS 61B FALL 2020 </h2>

- [1. Intro Hello World Java](#1-intro-hello-world-java)
- [2. Defineing and Using Classes](#2-defineing-and-using-classes)
- [3. References, Recursion, and Lists](#3-references-recursion-and-lists)
- [4. SLLists, Nested Classes, Sentinel Nodes](#4-sllists-nested-classes-sentinel-nodes)
- [5. DLLists, Arrays](#5-dllists-arrays)
- [6. ALists, Resizing, vs. SLists](#6-alists-resizing-vs-slists)
- [7. Testing](#7-testing)
- [8. Inheritance, Implements](#8-inheritance-implements)
- [9. Extends, Casting, Higher Order Functions](#9-extends-casting-higher-order-functions)
- [10. Subtype Polymorphism vs. HoFs](#10-subtype-polymorphism-vs-hofs)
- [11. Exceptions, Iterators, Object Methods](#11-exceptions-iterators-object-methods)
- [12. Coding in the Real World, Review](#12-coding-in-the-real-world-review)
- [13. Asymptotics I](#13-asymptotics-i)
- [14. Disjoint Sets](#14-disjoint-sets)
- [15. Asymptotics II](#15-asymptotics-ii)
- [16. ADTs, Sets, Maps, BSTs](#16-adts-sets-maps-bsts)
- [19. Range Searching and Multi-Dimensional Data](#19-range-searching-and-multi-dimensional-data)
- [20. Hashing](#20-hashing)
  
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

#### 6. ALists, Resizing, vs. SLists

- A Last Look at Linked Lists
- Naive Array Lists
- Resizing Arrays
  - This is how the Python list is implemented
    ```java
    public void addLast(int x) {
      if (size == items.length) {
      resize(size * RFACTOR);
      }
      items[size] = x;
      size += 1;
    }

    ```
  - Memory Efficiency
    - Define the “usage ratio” R = size / items.length
    - Typical solution: Half array size when R < 0.25.
- Generic ALists
  - When creating an array of references to Glorps:
    - `(Glorp []) new Object[cap]`;
    - Causes a compiler warning, which you should ignore.
  - Why not just `new Glorp[cap]`;
    - Will cause a “generic array creation” error.
  - Nulling Out Deleted Items
    - Java only destroys unwanted objects when the last reference has been lost.
    - Keeping references to unneeded objects is sometimes called loitering.
- Obscurantism in Java


#### 7. Testing

- Some languages support sub-indexing into arrays. Java does not. **No way to get address of the middle of an array.**
- For example: `sort(x[1:]);` ← Would be nice, but not possible!


#### 8. Inheritance, Implements

- Method Overloading in Java
  - Java allows multiple methods with same name, but different parameters.
- Hypernyms, Hyponyms, and Interface Inheritance
- Overriding vs. Overloading
  - If a “subclass” has a method with the exact same signature as in the “superclass”, we say the subclass overrides the method.
  - `@Override Annotation`: The only effect of this tag is that the code won’t compile if it is not actually an overriding method.
  - Why use `@Override`
    - Protects against typos. If you say `@Override`, but it the method isn’t actually overriding anything, you’ll get a compile error.
    - Reminds programmer that method definition came from somewhere higher up in the inheritance hierarchy.
- Interface Inheritance
  - Specifying the capabilities of a subclass using the implements keyword is known as interface inheritance.
    - Interface: The list of all method signatures.
    - Inheritance: The subclass “inherits” the interface from a superclass.
    - Specifies what the subclass can do, but not how.
    - Subclasses must override all of these methods!
    - Will fail to compile otherwise.
  - If X is a superclass of Y, then memory boxes for X may contain Y.
- Implementation Inheritance: Default Methods
  - Interface inheritance:
    - Subclass inherits signatures, but NOT implementation.
  - For better or worse, Java also allows implementation inheritance.
    - Subclasses can inherit signatures AND implementation.
  - Use the **default** keyword to specify a method that subclasses should inherit from an interface.
  - If you don’t like a default method, you can override it.
- Static and Dynamic Type, Dynamic Method Selection
  - Every variable in Java has a “compile-time type”, a.k.a. “static type”.
    - This is the type specified at **declaration**. Never changes!
  - Variables also have a “run-time type”, a.k.a. “dynamic type”.
    - This is the type specified at **instantiation** (e.g. when using new).
    - Equal to the type of the object being pointed at.
  - Suppose we call a method of an object using a variable with: compile-time type X, run-time type Y.
    - Then if Y **overrides** the method, Y’s method is used instead.
    - This is known as “dynamic method selection”.
- More Dynamic Method Selection, Overloading vs. Overriding
- Interface vs. Implementation Inheritance
  - In both cases, we specify “is-a” relationships, not “has-a”.


#### 9. Extends, Casting, Higher Order Functions

- Implementation Inheritance: Extends
  - When a class is a hyponym of an interface, we used **implements**.
  - If you want one class to be a hyponym of another class, you use **extends**.
  - Because of extends, RotatingSLList inherits all members of SLList:
    - All instance and static variables.
    - All methods.
    - All nested classes.
    - Constructors are not inherited.
  - Java syntax disallows `super.super`.
  - Constructors are not inherited. However, the rules of Java say that **all constructors must start with a call to one of the super class’s constructors**. `super()`
  - If you don’t explicitly call the constructor, Java will *automatically* do it for you.
  - If you want to use a super constructor other than the no-argument constructor, can give parameters to super.
  - As it happens, every type in Java is a descendant of the Object class.
  - extends should only be used for **is-a** (hypernymic) relationships!
- Encapsulation
  - **Module**: A set of methods that work together as a whole to perform some task or set of related tasks. 
  - A module is said to be **encapsulated** if its implementation is completely hidden, and it can be accessed only through a documented interface.
  - Java is a great language for enforcing abstraction barriers with syntax.
  - Implementation inheritance (e.g. extends) breaks encapsulation!
- Type Checking and Casting
  - Method calls have compile-time type equal to their declared type.
  - Casting is a powerful but dangerous tool.
    - Tells Java to treat an expression as having a different compile-time type.
    - In example below, effectively tells the compiler to ignore its type checking duties.
    - Does not actually change anything: sunglasses don’t make the world dark.
- Dynamic Method Selection and Casting Puzzle
  - Casting causes no change to the bird variable, nor to the object the bird variable points at!
  - The compiler chooses the most specific matching method signature from **the static type of the invoking class**.
  - Since there is no **overriding**, no dynamic method selection occurs.
- Higher Order Functions (A First Look)
  - Higher Order Function: A function that treats another function as data. e.g. takes a function as input.
  - Old School (Java 7 and earlier). Fundamental issue: Memory boxes (variables) cannot contain pointers to functions.
  - In Java 8, new types were introduced: now can can hold references to methods.
- **Implementation Inheritance Cheatsheet**
  - VengefulSLList extends SLList means a VenglefulSLList is-an SLList. Inherits all members!
    - Variables, methods, nested classes.
    - Not constructors.
    - Subclass constructor must invoke superclass constructor first.
    - Use super to invoke overridden superclass methods and constructors.
  - Invocation of overridden methods follows two simple rules:
    - Compiler plays it safe and only lets us do things allowed by **static** type.
    - For overridden methods the actual method invoked is based on **dynamic** type <span style="color: red">(Does not apply to overloaded methods!)</span> of invoking expression, `e.g. Dog.maxDog(d1, d2).bark()`;
    - Can use **casting** to overrule compiler type checking.

#### 10. Subtype Polymorphism vs. HoFs

- Dynamic Method Selection Puzzle
  - What if subclass has a static method with the same signature as a superclass method?
    - For static methods, we do not use the term overriding for this.
    - These two practices above are called “hiding”.
    - As promised, the version of the hidden static method that gets invoked is the one in the superclass, and the version of the overridden instance method that gets invoked is the one in the subclass.
- Subtype Polymorphism
- Comparables
  - Advantages
    - Lots of built in classes implement Comparable (e.g. String).
    - Lots of libraries use the Comparable interface (e.g. Arrays.sort)
    - Avoids need for casts.
    ```java
    public class Dog implements Comparable<Dog> {
      public int compareTo(Dog uddaDog) {
        return this.size - uddaDog.size;
      }
    }

    Dog[] dogs = new Dog[]{d1, d2, d3};
    Dog largest = Collections.max(Arrays.asList(dogs));
    ```
- Comparators
  ```java
  public class Dog implements Comparable<Dog> {
    private String name;
    private int size;
  
    public static class NameComparator implements Comparator<Dog> {
      public int compare(Dog d1, Dog d2) {
          return d1.name.compareTo(d2.name);
      }
    }
    ...
  }

  Comparator<Dog> cd = new Dog.NameComparator();
  if (cd.compare(d1, d3) > 0) {
      d1.bark();
  } else {
      d3.bark();
  }
  ```
- Comparable and Comparator Summary
  - Interfaces provide us with the ability to make **callbacks**:
    - Sometimes a function needs the help of another function that might not have been written yet.
      - Example: max needs compareTo
      - The helping function is sometimes called a “callback”.
  - Some languages handle this using explicit function passing.
  - In Java, we do this by wrapping up the needed function in an interface (e.g. `Arrays.sort` needs `compare` which lives inside the `comparator` interface)
  - `Arrays.sort` “calls back” whenever it needs a comparison.
    - Similar to giving your number to someone if they need information.


#### 11. Exceptions, Iterators, Object Methods

- Lists and Sets in Java
  - We built a list from scratch, but Java provides a built-in `List` interface and several implementations, e.g. `ArrayList`.
  - Demo code
    ```java
    // ArrayList
    import java.util.List;
    import java.util.ArrayList;
    
    public class SimpleBuiltInListExample {
      public static void main(String[] args) {
        List<Integer> L = new ArrayList<>();
        L.add(5);
        L.add(10);
        L.add(15);
        System.out.println(L);
      }
    }
    // Set
    Set<String> S = new HashSet<>();
    S.add("Tokyo");
    S.add("Beijing");	
    S.add("Lagos");
    S.add("São Paulo");
    System.out.println(S.contains("Tokyo"));
    ```
- Exceptions
- Iteration
  - To support the enhanced for loop:
    - Add an `iterator()` method to your class that returns an `Iterator<T>`.
    - The `Iterator<T>` returned should have a useful `hasNext()` and `next()` method.
    - Add implements `Iterable<T>` to the line defining your class.
- Object Methods:
- Equals and toString()
  - ArraySet toString
    - Adding even a single character to a string creates an entirely new string. It’s because Strings are “immutable”.
    - Intuition: Append operation for a StringBuilder is fast.
  - Equals vs. ==
    - `==` compares the bits. For references, `==` means “referencing the same object.”
    - `.equals` for classes. Requires writing a `.equals` method for your classes.
      - Default implementation of `.equals` uses `==` (probably not what you want).
    - BTW: Use Arrays.equal or Arrays.deepEquals for arrays
      - When comparing arrays in Java, it's recommended to use `Arrays.equals()` for one-dimensional arrays and `Arrays.deepEquals()` for multi-dimensional arrays or arrays with nested elements.
      - `Arrays.deepEquals()` compares the "deep" contents of the arrays, meaning it will recursively compare nested arrays or other non-primitive elements.
    - The code below is pretty close to what a standard equals method looks like.
      ```java
      @Override
      public boolean equals(Object o) {
        if (o == null) { return false; }
        if (this == o) { return true; } // optimization
        if (this.getClass() != o.getClass()) { return false; }
        ArraySet<T> other = (ArraySet<T>) o;
        if (this.size() != other.size()) { return false; }
          for (T item : this) {
            if (!other.contains(item)) {
              return false;
            }
          }
        return true;
      }
      ```

#### 12. Coding in the Real World, Review

- Programming in the Real World

#### 13. Asymptotics I

- Intuitive Runtime Characterizations
- Worst Case Order of Growth
  - **Justification**: When comparing algorithms, we often care only about the worst case [but we will see exceptions in this course]. 
  - Simplifications:
    - Only consider the worst case.
    - Pick a representative operation (a.k.a. the cost model).
    - Ignore lower order terms.
    - Ignore multiplicative constants.
- Simplified Analysis
- Big-Theta
- Big O Notation

#### 14. Disjoint Sets

- Quick Find
- Quick Union
- Weighted Quick Union
  - Track tree size (**number** of elements).
  - New rule: Always link root of **smaller** tree to **larger** tree.
  - We used the number of items in a tree to decide upon the root.
    - Worst case performance for HeightedQuickUnionDS is asymptotically the same! Both are $Θ(\log (N))$.
    - Resulting code is more complicated with no performance gain.
  - Performance Summary
    | Implementation | Constructor | connect | isConnected |
    | --- | --- | --- | --- |
    |ListOfSetsDS | $\Theta(N)$ | $O(N)$ | $O(N)$ |
    |QuickFindDS | $\Theta(N)$ | $\Theta(N)$ | $\Theta(1)$ |
    |QuickUnionDS | $\Theta(N)$ | $O(N)$ | $O(N)$ |
    |WeightedQuickUnionDS | $\Theta(N)$ | $O(\log N)$ | $O(\log N)$ |
- Path Compression 
  - Clever idea: When we do `isConnected(15, 10)`, tie all nodes seen to the root.
  - Path compression results in a union/connected operations that are very very close to amortized constant time (amortized constant means “constant on average”)..
  - A tighter bound: $O(N + M \alpha (N))$, where $\alpha$ is the inverse Ackermann function.
- Summary
  - The ideas that made our implementation efficient:
    - Represent sets as connected components (don’t track individual connections).
  - ListOfSetsDS: Store connected components as a List of Sets (slow, complicated).
  - QuickFindDS: Store connected components as set ids.
  - QuickUnionDS: Store connected components as parent ids.
  - WeightedQuickUnionDS: Also track the size of each set, and use size to decide on new tree root.
  - WeightedQuickUnionWithPathCompressionDS: On calls to connect and isConnected, set parent id to the root for all items seen.
  - Performance
    | Implementation | Runtime |
    | --- | --- |
    |ListOfSetsDS | $O(NM)$ |
    |QuickFindDS | $\Theta(NM)$ |
    |QuickUnionDS | $O(NM)$ |
    |WeightedQuickUnionDS | $O(N+M\log N)$ |
    |WeightedQuickUnionDSWithPathCompression| $O(N+M\alpha (N))$ |

#### 15. Asymptotics II

#### 16. ADTs, Sets, Maps, BSTs

- Abstract Data Types
  - An Abstract Data Type (ADT) is defined only by its operations, not by its implementation.
  - Interfaces in Java aren’t purely abstract as they can contain some implementation details, e.g. `default` methods.
  - Maps also known as associative arrays, associative lists (in Lisp), symbol tables, dictionaries (in Python).
  ```java
    Map<String, Integer> m = new TreeMap<>();
    String[] text = {"sumomo", "mo", "momo", "mo",
                    "momo", "no", "uchi"};
    for (String s : text) {
      int currentCount = m.getOrDefault(s, 0);
      m.put(s, currentCount + 1);
    }
    ```
  - Among the most important interfaces in the java.util library are those that extend the Collection interface ![](./src/lec16_1.png) 
- Binary Search Trees
- BST Definitions
  - A binary search tree is a rooted binary tree with the BST property.
  - **BST** Property. For every node X in the tree:
    - Every key in the **left** subtree is **less** than X’s key.
    - Every key in the **right** subtree is **greater** than X’s key.
- BST Operations: Search
- BST Operations: Insert
- BST Operations: Delete
  - Deleting from a BST: Deletion with two Children (Hibbard)
- Sets vs. Maps, Summary
  - Java provides Map, Set, List interfaces, along with several implementations.
  - We’ve seen two ways to implement a Set (or Map): ArraySet and using a BST.
    - ArraySet: $Θ(N)$ operations in the worst case.
    - BST: $Θ(\log N)$ operations in the worst case if tree is balanced.
- BST Implementation Tips 
  - When inserting, always set left/right pointers, even if nothing is actually changing.
  - Avoid “arms length base cases”. Don’t check if left or right is null!

#### 17. B-Trees (2-3, 2-3-4 Trees)

- BST Tree Height
  - Height varies dramatically between “bushy” and “spindly” trees.
- Height, Depth, and Performance
  - The **“depth” of a node** is how far it is from the root
  - The **“height” of a tree** is the depth of its deepest leaf
  - The **“average depth”** of a tree is the average depth of a tree’s nodes
  - Nice Property. Random trees have $Θ(\log N)$ average depth and height.
- B-trees / 2-3 trees / 2-3-4 trees
  - Avoiding Imbalance through Overstuffing
    - Avoid new leaves by “overstuffing” the leaf nodes.
    - “Overstuffed tree” always has balanced height, because leaf depths never change. Height is just max(depth).
    - Height is balanced, but we have a new problem: Leaf nodes can get too juicy.
    - Set a limit L on the number of items, say L=3.
    - If any node has more than L items, give an item to parent.
      - Pulling item out of full node splits it into left and right.
    - Perfect Balance
      - If we split the root, every node gets pushed down by exactly one level.
      - If we split a leaf node or internal node, the height doesn’t change.
  - B-trees of order L=3 (like we used today) are also called a 2-3-4 tree or a 2-4 tree. 
    - “2-3-4” refers to the number of children that a node can have, e.g. a 2-3-4 tree node may have 2, 3, or 4 children.
  - B-trees of order L=2 are also called a 2-3 tree.
  - B-Trees are most popular in two specific contexts:
    - Small L (L=2 or L=3):
      - Used as a conceptually simple balanced search tree (as today).
    - L is very large (say thousands).
      - Used in practice for databases and filesystems (i.e. systems with very large records).
- B-Tree Bushiness Invariants
  - All leaves must be the same distance from the source.
  - A non-leaf node with k items must have exactly k+1 children.
  - These invariants guarantee that our trees will be bushy.
- B-Tree Runtime Analysis
  - Height of a B-Tree with Limit L
    - Height: Between $\log_{L+1}(N)$ and $\log_2(N)$
    - Overall height is therefore $Θ(\log N)$
  - Runtime for contains
    - Worst case number of nodes to inspect: $H + 1$
    - Worst case number of items to inspect per node: $L$
    - Overall runtime: $O(HL)$
    - Since $H = Θ(\log N)$, overall runtime is $O(L \log N)$.
    - Since $L$ is a constant, runtime is therefore $O(\log N)$.
  - Runtime for add
- Deletion (optional)

#### 18. Red Black Trees

- BST Structure and Tree Rotation
- Red-Black Trees
  - A BST with left glue links that represents a 2-3 tree is often called a “Left Leaning Red Black Binary Search Tree” or LLRB.
    - LLRBs are normal BSTs! 
    - There is a 1-1 correspondence between an LLRB and an equivalent 2-3 tree.
    - The red is just a convenient fiction. Red links don’t “do” anything special.
  - What is the maximum height of the corresponding LLRB?
    - Total height is **H (black)** + <span style="color: red">H + 1 (red)</span> = 2H + 1.
  - Some handy LLRB properties:
    - No node has two red links [otherwise it’d be analogous to a 4 node, which are disallowed in 2-3 trees].
    - Every path from root to a ~~leaf~~(This should say “a null reference”, not “a leaf”.) has same number of black links [because 2-3 trees have the same number of links to every leaf]. LLRBs are therefore balanced.
- Maintaining 1-1 Correspondence Through Rotations 
  - #Task1: Insertion Color: Use red! In 2-3 trees new values are ALWAYS added to a leaf node (at first).
  - #Task2: Insertion on the Right: Right links aren’t allowed, so rotateLeft(E).
  - New Rule: Representation of Temporary 4-Nodes. **Temporarily violates “no red right links”.**
  - #Task3: Double Insertion on the Left: Rotate Z right.
  - #Task4: Splitting Temporary 4-Nodes: Flip the colors of all edges touching B.
    - Note: This doesn’t change the BST structure/shape.
  - Summary:
    - When inserting: Use a red link.
    - If there is a right leaning “3-node”, we have a Left Leaning Violation.
      - <u>Rotate left</u> the appropriate node to fix.
    - If there are two consecutive left links, we have an Incorrect 4 Node Violation.
      - <u>Rotate right</u> the appropriate node to fix.
    - If there are any nodes with two red children, we have a Temporary 4 Node.
      - <u>Color flip</u> the node to emulate the split operation.
      - We have a right leaning 3-node (B-S). We can fix with <u>rotateLeft</u>.
    - One last detail: Cascading operations.
      - It is possible that a rotation or flip operation will cause an additional violation that needs fixing. 
      - We have a right leaning 3-node (B-S). We can fix with rotateLeft(b).
- LLRB Runtime and Implementation
  - Amazingly, turning a BST into an LLRB requires only 3 clever lines of code.
  ```java
  private Node put(Node h, Key key, Value val) {
    if (h == null) { return new Node(key, val, RED); }
  
    int cmp = key.compareTo(h.key);
    if (cmp < 0)      { h.left  = put(h.left,  key, val); }
    else if (cmp > 0) { h.right = put(h.right, key, val); }
    else              { h.val   = val;                    }
  
    if (isRed(h.right) && !isRed(h.left))      { h = rotateLeft(h);  }
    if (isRed(h.left)  &&  isRed(h.left.left)) { h = rotateRight(h); }
    if (isRed(h.left)  &&  isRed(h.right))     { flipColors(h);      } 
  
    return h;
  }
  ```
- Search Tree Summary

#### 19. Range Searching and Multi-Dimensional Data

- Range-Finding and Nearest
  - It turns out that a BST can efficiently support the `select`, `rank`, `subSet`, and `nearest` operations.
- Multi-dimensional Data
  - Could just pick one, but you’re losing some of your information about ordering. Results in suboptimal pruning.
- QuadTrees
  - Every Node has **four** children:
    - Top left, a.k.a. northwest.
    - Top right, a.k.a. northeast.
    - Bottom left, a.k.a. southwest.
    - Bottom right, a.k.a. southeast. 
  - Quadtrees are a form of “spatial partitioning”.
    - Space is more finely divided in regions where there are more points.
    - Results in better runtime in many circumstances.
  - Quadtrees allow us to prune when performing a rectangle search.
- Higher Dimensional Data
  - One approach: Use an Oct-tree or Octree.
  - k-d tree example for 2-d:
    - Basic idea, root node partitions entire space into left and right (by x).
    - All depth 1 nodes partition subspace into up and down (by y).
    - All depth 2 nodes partition subspace into left and right (by x).
- Uniform Partitioning
  - All of our approaches today boil down to spatial partitioning.
    - Uniform partitioning (perfect grid of rectangles).
    - Quadtrees and KdTrees: Hierarchical partitioning. 
      - Each node “owns” 4 and 2 subspaces, respectively.
      - Space is more finely divided into subspaces where there are more points.
- Summary and Applications
  - Set operations can be more complex than just contains, e.g.:
    - **Range Finding**: What are all the objects inside this (rectangular) subspace?
    - **Nearest**: What is the closest object to a specific point?
      - Note: Can be generalized to k-nearest.
  - The most common approach is **spatial partitioning**:
    - **Quadtree**: Generalized 2D BST where each node “owns” 4 subspaces.
    - **K-d Tree**: Generalized k-d BST where each node “owns” 2 subspaces.
      - Dimension of ownership cycles with each level of depth in tree.
    - **Uniform Partitioning**: Partition space into uniform chunks.
  - Spatial partitioning allows for **pruning** of the search space.

#### 20. Hashing

- Data Indexed Arrays
- DataIndexedEnglishWordSet
- DataIndexedStringSet
- Integer Overflow and Hash Codes
- Hash Tables: Handling Collisions
  - Each bucket in our array is initially empty. When an item x gets added at index h:
    - If bucket h is empty, we create a new list containing x and store it at index h.
    - If bucket h is already a list, we add x to this list <u>if it is not already present.</u>
- Hash Table Performance
  - One example strategy: When $\frac{N}{M} \geq 1.5$, then double $M$. 
    - Resizing takes $Θ(N)$ time. Have to redistribute all items!
    - Most add operations will be $Θ(1)$. Some will be $Θ(N)$ time (to resize).
      - Similar to our ALists, as long as we resize by a multiplicative factor, the average runtime will still be $Θ(1)$.
      - Note: We will eventually analyze this in more detail.
- Hash Tables in Java
  - Two Important Warnings When Using `HashMaps`/`HashSets`
    - Warning #1: Never store objects that can change in a HashSet or HashMap!
      - If an object’s variables changes, then its hashCode changes. May result in items getting lost.
    - Warning #2: Never override equals without also overriding hashCode.
      - Can also lead to items getting lost and generally weird behavior.
      - HashMaps and HashSets use equals to determine if an item exists in a particular bucket.
  - use `Math.floorMod(x, 4)` to get valid index.
- Good HashCodes (Extra)
  - A typical hash code base is a small prime.
    - Why prime?
      - Never even: Avoids the overflow issue on previous slide.
      - Lower chance of resulting hashCode having a bad relationship with the number of buckets: See study guide problems and hw3.
    - Why small?
      - Lower cost to compute.
- Summary
