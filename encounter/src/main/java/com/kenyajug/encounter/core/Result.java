package com.kenyajug.encounter.core;
public sealed interface Result<T> {
    record Success<T>(T value) implements Result<T>{}
    record Failure<T>(Throwable error) implements Result<T>{}
    public static <T> Result<T> success(T value){
        return new Success<>(value);
    }
    public static <T> Result<T> failure(String message){
        return new Failure<>(new Throwable(message));
    }
}
