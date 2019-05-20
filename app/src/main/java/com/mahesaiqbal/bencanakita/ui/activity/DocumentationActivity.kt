package com.mahesaiqbal.bencanakita.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.mahesaiqbal.bencanakita.R
import com.mahesaiqbal.bencanakita.model.dokumentasi.Data
import com.mahesaiqbal.bencanakita.network.InstanceRetrofit
import com.mahesaiqbal.bencanakita.network.Service
import com.mahesaiqbal.bencanakita.ui.adapter.DokumentasiAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_documentation.*

class DocumentationActivity : AppCompatActivity() {

    lateinit var service: Service

    var dokumentasi: ArrayList<Data> = arrayListOf()

    private lateinit var year: String
    private lateinit var month: String
    private lateinit var dokumentasiAdapter: DokumentasiAdapter

    private var compositeDisposable: CompositeDisposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_documentation)

        year = intent.getStringExtra("year")
        month = intent.getStringExtra("month")

        compositeDisposable = CompositeDisposable()
        service = InstanceRetrofit.create()

        initData()
    }

    private fun initData() {
        compositeDisposable?.add(
            service.getDokumentasi(year, month)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.error == false) {
                        dokumentasi = it.data as ArrayList<Data>
                        setDokumentasiData(dokumentasi)
                    } else {
                        Toast.makeText(this, "Data tidak tersedia pada tahun ${year} bulan ${month}", Toast.LENGTH_SHORT).show()
                    }
                }, {
                    Toast.makeText(this, "Data tidak tersedia pada tahun ${year} bulan ${month}", Toast.LENGTH_SHORT).show()
                })
        )
    }

    private fun setDokumentasiData(dokumentasi: ArrayList<Data>) {
        dokumentasiAdapter = DokumentasiAdapter(this, dokumentasi, { data -> itemClicked(data) })

        list_data.apply {
            layoutManager = LinearLayoutManager(this@DocumentationActivity)
            adapter = dokumentasiAdapter
        }
    }

    private fun itemClicked(data: Data) {
        val intent = Intent(this, DocumentationDetailActivity::class.java)
        intent.putExtra("dokumentasi_id", data.dokumentasi_id)
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
