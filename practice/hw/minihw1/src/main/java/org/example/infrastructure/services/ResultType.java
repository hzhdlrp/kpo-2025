package org.example.infrastructure.services;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ResultType<T> {
    int code;
    T result;
}
