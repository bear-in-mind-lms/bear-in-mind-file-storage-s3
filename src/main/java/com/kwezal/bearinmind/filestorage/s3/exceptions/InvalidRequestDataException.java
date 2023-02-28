package com.kwezal.bearinmind.filestorage.s3.exceptions;

import java.util.List;
import lombok.Getter;

@Getter
public class InvalidRequestDataException extends AbstractException {

    public InvalidRequestDataException(
        final String propertyName,
        final Object propertyValue,
        final List<String> errorArguments
    ) {
        super(propertyName, propertyValue, ErrorCode.REQUEST_ARGUMENT_INVALID, errorArguments);
    }
}
