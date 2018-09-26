package com.microsoft.azure.hdinsight.common.classifiedexception

class SparkUserException : ClassifiedException {
    constructor(exp: Throwable?, msg: String) : super(exp, msg)

    override val title: String = "SparkUserException"
}

object SparkUserExceptionFactory : ClassifiedExceptionFactory() {
    override fun createClassifiedException(exp: Throwable?, message: String): ClassifiedException? {
        return SparkUserException(exp, message)
    }
}