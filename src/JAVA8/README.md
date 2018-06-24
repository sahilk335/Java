# 1. Functional Interfaces

1. It must contain only 1 abstract methods
2. It can contain any number of default or static methods
3. Annotation @FunctionalInterface can be given to make sure it is functional interface
4. There are many default functional interfaces that are provided by Java 8 , example - 
	Predicate,BiPredicate,Supplier,Functional,BiFunctional,Unaryoperator

#2. Method Reference Operator :: for functional Interfaces

There are following types of method references in java:

1. Reference to a static method.
Syntax :
```
ContainingClass::staticMethodName
```
2. Reference to an instance method.
```
Syntax:

containingObject::instanceMethodName
```
3. Reference to a constructor.
Syntax:

```
ClassName::new
```