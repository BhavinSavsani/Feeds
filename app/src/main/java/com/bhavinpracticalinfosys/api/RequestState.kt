package com.bhavinpracticalinfosys.api


data class RequestState<T>(
    var status: State,
    var data: T? = null,
    var error: String? = null
) {
    enum class State {
        PROGRESS,
        INTERNET_ERROR,
        SUCCESS,
        FAILURE,
        API_ERROR
    }

    companion object {
        fun <T> progress(): RequestState<T> {
            return RequestState(State.PROGRESS, null)
        }

        fun <T> internetError(): RequestState<T> {
            return RequestState(State.INTERNET_ERROR, null)
        }

        fun <T> success(response: T): RequestState<T> {
            return RequestState(State.SUCCESS, response)
        }

        fun <T> apiError(response: T): RequestState<T> {
            return RequestState(State.API_ERROR, response)
        }

        fun <T> error(error: String?): RequestState<T> {
            return RequestState(State.FAILURE, null, error)
        }
    }
}