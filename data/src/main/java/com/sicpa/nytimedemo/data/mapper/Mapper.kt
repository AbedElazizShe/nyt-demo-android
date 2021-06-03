package com.sicpa.nytimedemo.data.mapper

interface Mapper<E, D> {

    fun mapFromEntity(type: E): D
}
