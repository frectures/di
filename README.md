# Dependency Inversion and Dependency Injection

Dependency *Inversion* is a SOLI**D** principle concerned with dependencies between layers.

- **S**ingle Responsibility
- **O**pen-Closed
- **L**iskov Substitution
- **I**nterface Segregation
- **D**ependency Inversion

Dependency *Injection* solves an object instantiation problem arising from Dependency Inversion.

## Before

![](img/inversion1.svg)

```java
public class BookService {
    private BookRepositoryH2 bookRepository;

    public BookService() {
        this.bookRepository = new BookRepositoryH2();
    }

    // ...
}
```

## After

![](img/inversion2.svg)

> **Dependency Inversion:** Replace a uses-relationship from layer A to layer B  
> with an implements-relationship from layer B to layer A.

```java
public class BookService {
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // ...
}

// ...

/* ... */ new BookService(new BookRepositoryH2()) /* ... */
```

> **Dependency Injection:** Instead of instantiating your own dependencies,  
> accept your dependencies as constructor parameters.

## Spring

![](img/inversion3.svg)

```java
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
}

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    // ...
}
```

Actually, constructor injection is preferred over `@Autowired` for Service testing. Let's explore why...
