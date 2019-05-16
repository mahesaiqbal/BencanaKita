package com.mahesaiqbal.bencanakita.network

import com.mahesaiqbal.bencanakita.model.NewInfo.NewInfo
import com.mahesaiqbal.bencanakita.model.SliderInformasi.SliderInformasi
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {
    @GET("slider_informasi")
    fun getSliderInformasi(): Observable<SliderInformasi>

    @GET("informasi")
    fun getInformasiBaru(@Query("tahun") tahun: String, @Query("bulan") bulan: String): Observable<NewInfo>
}