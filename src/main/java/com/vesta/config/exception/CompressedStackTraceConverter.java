package com.vesta.config.exception;

import ch.qos.logback.classic.pattern.ThrowableProxyConverter;
import ch.qos.logback.classic.spi.IThrowableProxy;

public class CompressedStackTraceConverter extends ThrowableProxyConverter {
    @Override
    protected String throwableProxyToString(IThrowableProxy tp) {
        String original = super.throwableProxyToString(tp);

        // replace the new line characters with something,
        // use your own replacement value here
        return original.replaceAll("\n", " ~~ ");
    }
}
