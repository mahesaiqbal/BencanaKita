package com.mahesaiqbal.bencanakita.ui.activity

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
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

    private lateinit var progressDialog: ProgressDialog

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
        progressDialog = ProgressDialog.show(this, "Loading", "Loading Data...", true, true)
        compositeDisposable?.add(
            service.getPetaTerdampak(year, month)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()
                ).subscribe({
                    if (it.error == false) {
                        map = it.data as ArrayList<Data>
                        setMapData(map)
                        progressDialog.dismiss()
                    } else {
                        Toast.makeText(this, "Data tidak tersedia pada tahun ${year} bulan ${month}", Toast.LENGTH_SHORT).show()
                        progressDialog.dismiss()
                    }
                }, {
                    Toast.makeText(this, "Data tidak tersedia pada tahun ${year} bulan ${month}", Toast.LENGTH_SHORT).show()
                    progressDialog.dismiss()
                })
        )
    }

    private fun setMapData(map: ArrayList<Data>) {
        mapAdapter = MapAdapter(this, map, { data -> itemClicked(data) })

        list_data.apply {
            layoutManager = LinearLayoutManager(this@MapActivity)
            adapter = mapAdapter
        }
    }

    private fun itemClicked(data: Data) {
        val intent = Intent(this, MapDetailActivity::class.java)
        intent.putExtra("title", data.judul)
        intent.putExtra("img", "http://sibenta.hessananda.com/assets/img/peta_terdampak/${data.gambar}")
        intent.putExtra("desc", data.konten)
        startActivity(intent)
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
