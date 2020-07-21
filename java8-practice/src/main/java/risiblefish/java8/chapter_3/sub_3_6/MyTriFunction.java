package risiblefish.java8.chapter_3.sub_3_6;

public interface MyTriFunction<T,U,V,R> {
    R apply(T t, U u, V v);
}
