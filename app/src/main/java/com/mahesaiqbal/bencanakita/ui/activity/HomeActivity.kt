package com.mahesaiqbal.bencanakita.ui.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.smarteist.autoimageslider.IndicatorAnimations
import kotlinx.android.synthetic.main.activity_home.*
import android.widget.Toast
import com.mahesaiqbal.bencanakita.R
import com.mahesaiqbal.bencanakita.model.sliderinformasi.Data
import com.mahesaiqbal.bencanakita.network.InstanceRetrofit
import com.mahesaiqbal.bencanakita.network.Service
import com.smarteist.autoimageslider.DefaultSliderView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class HomeActivity : AppCompatActivity() {

    lateinit var service: Service

    var sliderInformasi: ArrayList<Data> = arrayListOf()

    private var compositeDisposable: CompositeDisposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        compositeDisposable = CompositeDisposable()
        service = InstanceRetrofit.create()

        initData()

        info_btn.setOnClickListener {
            startActivity(Intent(this@HomeActivity, YearsActivity::class.java))
        }
    }

    private fun initData() {
        compositeDisposable?.add(
            service.getSliderInformasi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    sliderInformasi = it.data as ArrayList<Data>
                    setSliderViews(sliderInformasi)
                }, {
                    Toast.makeText(this, it.localizedMessage, Toast.LENGTH_SHORT).show()
                })
        )
    }

    private fun setSliderViews(dataSlider: ArrayList<Data>) {
        earthquake_map.setIndicatorAnimation(IndicatorAnimations.THIN_WORM)
        earthquake_map.scrollTimeInSec = 3

        for (i in 0..dataSlider.size) {

            val sliderView = DefaultSliderView(this)

            sliderView.imageUrl = "http://sibenta.hessananda.com/assets/img/informasi/" + dataSlider.get(i).gambar
            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP)
            sliderView.description = dataSlider.get(i).judul
            sliderView.setOnSliderClickListener {
                Toast.makeText(this, dataSlider.get(i).judul, Toast.LENGTH_SHORT).show()
            }

            earthquake_map.addSliderView(sliderView)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable?.clear()
    }
}
