package com.sicpa.nytimedemo.remote.mapper

interface EntityMapper<M, E> {

    fun mapFromModel(model: M): E
}
