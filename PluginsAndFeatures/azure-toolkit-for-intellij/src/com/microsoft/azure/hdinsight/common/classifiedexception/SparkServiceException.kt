package com.microsoft.azure.hdinsight.common.classifiedexception

import java.io.IOException

class SparkServiceException : ClassifiedException {
    constructor(exp: Throwable?, msg: String) : super(exp, msg)

    override val title: String = "SparkServiceException"
}

object SparkServiceExceptionFactory : ClassifiedExceptionFactory() {
    override fun createClassifiedException(exp: Throwable?, message: String): ClassifiedException? {
        return if (exp is IOException) SparkServiceException(exp, message) else null
    }
}
