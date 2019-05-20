package com.mahesaiqbal.bencanakita.network

import com.mahesaiqbal.bencanakita.model.dokumentasi.Dokumentasi
import com.mahesaiqbal.bencanakita.model.dokumentasifoto.DokumentasiFoto
import com.mahesaiqbal.bencanakita.model.map.Map
import com.mahesaiqbal.bencanakita.model.newinfo.NewInfo
import com.mahesaiqbal.bencanakita.model.sliderinformasi.SliderInformasi
import com.mahesaiqbal.bencanakita.model.video.Video
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {
    @GET("slider_informasi")
    fun getSliderInformasi(): Observable<SliderInformasi>

    @GET("informasi")
    fun getInformasiBaru(@Query("tahun") tahun: String, @Query("bulan") bulan: String): Observable<NewInfo>

    @GET("peta_terdampak")
    fun getPetaTerdampak(@Query("tahun") tahun: String, @Query("bulan") bulan: String): Observable<Map>

    @GET("video")
    fun getVideo(@Query("tahun") tahun: String, @Query("bulan") bulan: String): Observable<Video>

    @GET("dokumentasi")
    fun getDokumentasi(@Query("tahun") tahun: String, @Query("bulan") bulan: String): Observable<Dokumentasi>

    @GET("dokumentasi_foto")
    fun getDokumentasiFoto(@Query("dokumentasi_id") dokumentasiId: String): Observable<DokumentasiFoto>
}