package com.kwezal.bearinmind.filestorage.s3.exceptions;

import java.util.List;
import java.util.Map;
import lombok.Getter;
import org.springframework.lang.Nullable;

@Getter
public abstract class AbstractException extends RuntimeException {

    private final Map<String, Object> properties;

    private final String errorCode;

    @Nullable
    private final List<String> errorArguments;

    protected AbstractException(
        final Map<String, Object> properties,
        final String errorCode,
        final List<String> errorArguments
    ) {
        super("properties=" + properties);
        this.properties = properties;
        this.errorCode = errorCode;
        this.errorArguments = errorArguments;
    }

    protected AbstractException(
        final String propertyName,
        final Object propertyValue,
        final String errorCode,
        final List<String> errorArguments
    ) {
        this(Map.of(propertyName, propertyValue), errorCode, errorArguments);
    }
}
