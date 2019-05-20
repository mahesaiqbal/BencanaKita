package com.mahesaiqbal.bencanakita.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.mahesaiqbal.bencanakita.R
import com.mahesaiqbal.bencanakita.model.dokumentasifoto.Data
import com.mahesaiqbal.bencanakita.network.InstanceRetrofit
import com.mahesaiqbal.bencanakita.network.Service
import com.mahesaiqbal.bencanakita.ui.adapter.DokumentasiFotoAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_documentation_detail.*

class DocumentationDetailActivity : AppCompatActivity() {

    lateinit var service: Service

    var dokumentasiDetail: ArrayList<Data> = arrayListOf()

    private lateinit var dokumentasiId: String
    private lateinit var dokumentasiFotoAdapter: DokumentasiFotoAdapter

    private var compositeDisposable: CompositeDisposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_documentation_detail)

        dokumentasiId = intent.getStringExtra("dokumentasi_id")

        compositeDisposable = CompositeDisposable()
        service = InstanceRetrofit.create()

        initData()
    }

    private fun initData() {
        compositeDisposable?.add(
            service.getDokumentasiFoto(dokumentasiId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.error == false) {
                        dokumentasiDetail = it.data as ArrayList<Data>
                        setDokumentasiDetailData(dokumentasiDetail)
                    } else {
                        Toast.makeText(this, "Data tidak tersedia", Toast.LENGTH_SHORT).show()
                    }
                }, {
                    Toast.makeText(this, "Data tidak tersedia", Toast.LENGTH_SHORT).show()
                })
        )
    }

    private fun setDokumentasiDetailData(dokumentasiDetail: ArrayList<Data>) {
        dokumentasiFotoAdapter = DokumentasiFotoAdapter(this, dokumentasiDetail)

        list_data.apply {
            layoutManager = LinearLayoutManager(this@DocumentationDetailActivity)
            adapter = dokumentasiFotoAdapter
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
