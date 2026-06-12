package com.kjug.boottask;

import java.util.List;
public record ApiResponse<T>(
        T data,
        String message,
        String error
) {
}
