package ku.cs.services;

public interface ConditionFilterer<T> {
    boolean match(T t);
}
