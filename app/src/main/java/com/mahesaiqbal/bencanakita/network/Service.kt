package com.mahesaiqbal.bencanakita.network

import com.mahesaiqbal.bencanakita.model.SliderInformasi
import io.reactivex.Observable
import retrofit2.http.GET

interface Service {
    @GET("slider_informasi")
    fun getSliderInformasi(): Observable<SliderInformasi>
}