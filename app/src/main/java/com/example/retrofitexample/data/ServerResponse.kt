package com.example.retrofitexample.data

data class ServerResponse<out DATA, out ERROR>(val status: Status, val data: DATA?, val error: ERROR?) {
    /**
     * Indicates that response is successful ([status] == [Status.SUCCESS]).
     */
    val isSuccessful: Boolean
        get() = status == Status.SUCCESS

    enum class Status {
        SUCCESS, ERROR
    }

    companion object {
        fun <DATA, ERROR> success(data: DATA?): ServerResponse<DATA, ERROR> {
            return ServerResponse(Status.SUCCESS, data, null)
        }

        fun <DATA, ERROR> error(error: ERROR?): ServerResponse<DATA, ERROR> {
            return ServerResponse(Status.ERROR, null, error)
        }
    }
}