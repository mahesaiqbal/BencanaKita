package com.mahesaiqbal.bencanakita.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.mahesaiqbal.bencanakita.R
import com.mahesaiqbal.bencanakita.model.map.Data
import com.mahesaiqbal.bencanakita.network.InstanceRetrofit
import com.mahesaiqbal.bencanakita.network.Service
import com.mahesaiqbal.bencanakita.ui.adapter.MapAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_map.*

class MapActivity : AppCompatActivity() {

    lateinit var service: Service

    var map: ArrayList<Data> = arrayListOf()

    private lateinit var year: String
    private lateinit var month: String
    private lateinit var mapAdapter: MapAdapter

    private var compositeDisposable: CompositeDisposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        year = intent.getStringExtra("year")
        month = intent.getStringExtra("month")

        compositeDisposable = CompositeDisposable()
        service = InstanceRetrofit.create()

        initData()
    }

    private fun initData() {
        compositeDisposable?.add(
            service.getPetaTerdampak(year, month)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()
                ).subscribe({
                    map = it.data as ArrayList<Data>
                    setMapData(map)
                }, {

                })
        )
    }

    private fun setMapData(map: ArrayList<Data>) {
        mapAdapter = MapAdapter(this, map)

        list_data.apply {
            layoutManager = LinearLayoutManager(this@MapActivity)
            adapter = mapAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable?.clear()
    }
}
