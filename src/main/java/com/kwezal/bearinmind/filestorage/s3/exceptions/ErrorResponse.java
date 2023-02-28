package com.kwezal.bearinmind.filestorage.s3.exceptions;

import java.util.List;
import org.springframework.lang.Nullable;

public record ErrorResponse(String code, @Nullable List<String> arguments) {}
