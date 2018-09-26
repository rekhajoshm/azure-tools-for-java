package com.microsoft.azure.hdinsight.common.classifiedexception

import com.microsoft.azure.hdinsight.common.logger.ILogger

const val EmptyLog: String = "Exception without detail message"

object ClassifiedExceptionGenerator : ILogger {
    fun createClassifiedException(exp: Throwable?, message: String?): ClassifiedException? {

        ///check whether context info is enough to create a classified exception
        if (exp == null && message == null) {
            log().warn("Cannot create classified exception since have not enough context")
            return null
        }

        val rootCause: Throwable? = (exp?.cause) ?: exp
        val msg: String = (rootCause?.message) ?: (message as String)

        return SparkServiceExceptionFactory.createClassifiedException(rootCause, msg)
                ?: SparkToolExceptionFactory.createClassifiedException(rootCause, msg)
                ?: SparkUserExceptionFactory.createClassifiedException(rootCause, msg)
    }
}

abstract class ClassifiedExceptionFactory {
    abstract fun createClassifiedException(exp: Throwable?, message: String): ClassifiedException?
}