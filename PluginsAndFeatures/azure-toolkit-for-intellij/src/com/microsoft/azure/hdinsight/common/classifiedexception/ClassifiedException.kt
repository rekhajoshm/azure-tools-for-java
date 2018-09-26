package com.microsoft.azure.hdinsight.common.classifiedexception

import com.microsoft.azure.hdinsight.common.logger.ILogger
import org.apache.commons.lang.exception.ExceptionUtils

abstract class ClassifiedException : Throwable, ILogger {
    final override val cause: Throwable?
    final override val message: String
    open val title: String = "ClassifiedException"

    constructor(exp: Throwable?, msg: String) {
        cause = exp
        message = msg
    }

    fun getStackTrace(): String {
        if (cause == null) {
            return message
        }

        return ExceptionUtils.getStackTrace(cause)
    }

    fun logStackTrace() {
        log().warn(title + ": " + getStackTrace())
    }
}