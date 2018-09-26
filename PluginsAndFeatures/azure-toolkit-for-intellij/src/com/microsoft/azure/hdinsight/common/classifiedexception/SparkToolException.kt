package com.microsoft.azure.hdinsight.common.classifiedexception

import org.apache.commons.lang.exception.ExceptionUtils

const val ToolPackageSuffix: String = "com.microsoft.azure.hdinsight"

class SparkToolException : ClassifiedException {
    constructor(exp: Throwable?, msg: String) : super(exp, msg)

    override val title: String = "SparkToolException"
}

object SparkToolExceptionFactory : ClassifiedExceptionFactory() {
    override fun createClassifiedException(exp: Throwable?, message: String): ClassifiedException? {
        val stackTrace = if (exp != null) ExceptionUtils.getStackTrace(exp) else message
        return if (stackTrace.contains(ToolPackageSuffix)) SparkToolException(exp, message) else null
    }
}