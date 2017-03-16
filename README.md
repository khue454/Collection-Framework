# Collection-Framework

## Content

### What is Java collection framework?
* What is an abstract data type?

### Hierarchy of Java collection framework?
* Interfaces in Java collection framework?
* Implementations of each interface: time complexity of basic operations, typical usages?

## References

https://docs.oracle.com/javase/8/docs/technotes/guides/collections/overview.html

http://www.javatpoint.com/collections-in-java

https://en.wikipedia.org/wiki/Abstract_data_type

## Exercise
Given interfaces: List, Set, Map

For each implementation of these interfaces, find a problem which we can use that implementation to solve in the most efficient way. 

For example: When we need to cache objects, ConcurrentHashMap can be used to exploit its concurrency capability.

```java
public class CacheManager {

    private final ConcurrentMap<String, Object> store;
    
    public CacheManager(int initCapacity) {
        this.store = new ConcurrentHashMap<>(initCapacity)
    }
    
	public <T> T get(String key, Callable<T> valueLoader) {
		if (this.store.containsKey(key)) {
			return (T) this.store.get(key);
		}
		else {
			synchronized (this.store) {
				if (this.store.containsKey(key)) {
					return (T) this.store.get(key);
				}
				T value = valueLoader.call();
				this.store.put(key, value);
				return value;
			}
		}
	}
    
}

```

