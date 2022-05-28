package com.simple.simpletestapp.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class AppDispatchers @Inject constructor(val IO: CoroutineDispatcher = Dispatchers.IO) {
    constructor() : this(Dispatchers.IO)
}
