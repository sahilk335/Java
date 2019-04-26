# Immutable Class

## What’s The Problem?

When you have a collection of items, ordered in a specific way. Now, any change on any item will violate this order.

For example, having a priority queue implemented with heaps, If the user changed the values while they are in the queue, this might violate the heap data structure.

Therefore, we need to turnoff any changes on the current items. This is what’s called Immutability.

Immutability ensures the structure will always have a valid state.

## What’s An Immutable Data Type?
A data type is a set of values and possible operations on those values. An immutable data type can’t be changed once it’s created. The more restriction(like immutability) you have on a class, the better.

For example, In Java, String, Integer, Double are Immutable classes, while StringBuilder, Stack, and Java array are Mutable.


## 
You can maintain immutability by implementing the following:

- Define the class as final to avoid inheriting this class, and thus avoid overriding instance methods.
- All instance variables are private and final. Private means you can only change it inside the class. Final means you have to assign it once inside the constructor, and can’t be changed then.
- Don’t Share References To Mutable Objects:
- - [1] Copy mutable objects(external mutable objects passed to the constructor) into our instance variables. Why? For example, If you assigned an instance variable to a reference variable points to an array(mutable object), that array values might change.
- - [2] Similarly, Instance methods don’t return an instance variable references a mutable object. Return a copy of that mutable object instead. Why? Again, because the client can alter that mutable object.
- Instance methods don’t change instance variables. They don’t alter the values of instance variables. Create a new object of current class instead.

So here’s an example to implement Vector; an immutable data type.

```java

public final class Vector { 
    private final int N;
    private final double[] data;
    public Vector(double[] data){   
        this.N = data.length;   
        this.data = new double[N];   
        for(int i = 0; i < N; i++) 
            this.data[i] = data[i];    // copy mutable object
    } 

    // get vector data array
    public double[] get(){
        double[] result = new double[this.N];
        for (int i = 0; i < N; i++)
            result[i] = data[i];
        return result;     // return a copy
     }
    // add an extra value to every entry in the vector data array
    public Vector add(double extra) {
        Vector c = new Vector(this.data); // create a new Vector obj
        for (int i = 0; i < N; i++)
            c.data[i] = this.data[i] + extra;
        return c;         
    }
}
```
>  The bottom line is, make sure your data type isn’t exposed, unchangeable, and can be viewed only.
