package com.kenyajug.encounter.core;
import java.util.Objects;
import java.util.function.Function;
public sealed interface Result<T> {
    default boolean isSuccess() {
        return this instanceof Success<?>;
    }
    default boolean isFailure() {
        return this instanceof Failure<?>;
    }
    record Success<T>(T value) implements Result<T>{}
    record Failure<T>(Exception error) implements Result<T>{}
    static <T> Result<T> success(T value) {
        return new Success<>(Objects.requireNonNull(value));
    }
    static <T> Result<T> failure(Exception error) {
        return new Failure<>(Objects.requireNonNull(error));
    }
    static <T> Result<T> failure(String message) {
        return new Failure<>(new RuntimeException(message));
    }
    static <T> Result<T> of(CheckedSupplier<T> supplier) {
        try {
            return success(supplier.get());
        } catch (Exception e) {
            return failure(e);
        }
    }
    default <U> Result<U> map(Function<? super T, ? extends U> mapper) {
        return switch (this) {
            case Success<T> s -> success(mapper.apply(s.value()));
            case Failure<T> f -> failure(f.error());
        };
    }

    default <U> Result<U> flatMap(Function<? super T, ? extends Result<U>> mapper) {
        return switch (this) {
            case Success<T> s -> mapper.apply(s.value());
            case Failure<T> f -> failure(f.error());
        };
    }

    default <U> U fold(Function<? super T, ? extends U> onSuccess,
                       Function<? super Exception, ? extends U> onFailure) {
        return switch (this) {
            case Success<T> s -> onSuccess.apply(s.value());
            case Failure<T> f -> onFailure.apply(f.error());
        };
    }
    default Result<T> recover(Function<? super Exception, ? extends T> handler) {
        return switch (this) {
            case Success<T> s -> this;
            case Failure<T> f -> success(handler.apply(f.error()));
        };
    }
    default T orElseThrow() {
        return switch (this) {
            case Success<T> s -> s.value();
            case Failure<T> f -> {
                throw new RuntimeException(f.error());
            }
        };
    }
    @FunctionalInterface
    interface CheckedSupplier<T> {
        T get() throws Exception;
    }
}
