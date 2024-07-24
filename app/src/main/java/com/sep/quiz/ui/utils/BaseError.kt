package com.sep.quiz.ui.utils

import android.content.Context
import com.sep.quiz.R

sealed class BaseError {

    data object Network : BaseError()
    data object NotFound : BaseError()
    data object AccessDenied : BaseError()
    data object ServiceUnavailable : BaseError()
    data object Canceled : BaseError()

    data object NoLocationProvided : BaseError()

    data class Unknown(val message: String?) : BaseError()


    data object InvalidInput : BaseError()
    data object InvalidAuth:  BaseError()
    data object ServerError: BaseError()
    data object TooManyRequest: BaseError()
    data object CodeIsUsed: BaseError()
    data object CodeIsInvalid: BaseError()
    data object CodeIsExpired: BaseError()
    data object NoteAllowed: BaseError()

    fun toErrorMessage(context: Context): String {
        return when (this) {
            AccessDenied -> context.getString(R.string.accessDenied)
            Canceled -> context.getString(R.string.requestCanceled)
            Network -> context.getString(R.string.networkError)
            NotFound -> context.getString(R.string.notFound)
            ServiceUnavailable -> context.getString(R.string.serviceUnavailable)

            InvalidInput -> context.getString(R.string.invalidInput)
            NoLocationProvided -> context.getString(R.string.noLocationProvided)
            InvalidAuth -> context.getString(R.string.invalid_auth)
            ServerError -> context.getString(R.string.serverError)
            TooManyRequest -> context.getString(R.string.tooManyRequest)
            CodeIsExpired -> context.getString(R.string.codeIsExpired)
            CodeIsInvalid -> context.getString(R.string.codeIsInvalid)
            CodeIsUsed -> context.getString(R.string.codeIsUsed)
            NoteAllowed -> context.getString(R.string.notAllowed)

            is Unknown -> message ?: context.getString(R.string.unknownError)
            else -> "Unknown"
        }
    }
}
