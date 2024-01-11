package com.auth.authentication.records;

import lombok.Builder;

import java.util.Arrays;
import java.util.Objects;

@Builder
public record UniversalResponse (
        int status,
        String message,
        Object... params) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UniversalResponse that = (UniversalResponse) o;
        return status == that.status &&
                Objects.equals(message, that.message) &&
                Arrays.deepEquals(params, that.params);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(status, message);
        result = 31 * result + Arrays.deepHashCode(params);
        return result;
    }

    @Override
    public String toString() {
        return "UniversalResponse{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", params=" + Arrays.deepToString(params) +
                '}';

}}