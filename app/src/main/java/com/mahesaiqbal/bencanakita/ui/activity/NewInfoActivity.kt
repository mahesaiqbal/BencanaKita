package com.mahesaiqbal.bencanakita.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.mahesaiqbal.bencanakita.R
import com.mahesaiqbal.bencanakita.model.NewInfo.Data
import com.mahesaiqbal.bencanakita.network.InstanceRetrofit
import com.mahesaiqbal.bencanakita.network.Service
import com.mahesaiqbal.bencanakita.ui.adapter.NewInfoAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_new_info.*

class NewInfoActivity : AppCompatActivity() {

    lateinit var service: Service

    var newInfo: ArrayList<Data> = arrayListOf()

    private lateinit var year: String
    private lateinit var month: String
    private lateinit var newInfoAdapter: NewInfoAdapter

    private var compositeDisposable: CompositeDisposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_info)

        year = intent.getStringExtra("year")
        month = intent.getStringExtra("month")

        compositeDisposable = CompositeDisposable()
        service = InstanceRetrofit.create()

        initData()
    }

    private fun initData() {
        compositeDisposable?.add(
            service.getInformasiBaru(year, month)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    newInfo = it.data as ArrayList<Data>
                    setNewInfoData(newInfo)
                }, {
                    Toast.makeText(this, it.localizedMessage, Toast.LENGTH_SHORT).show()
                })
        )
    }

    private fun setNewInfoData(newInfo: ArrayList<Data>) {
        newInfoAdapter = NewInfoAdapter(this, newInfo)

        list_data.apply {
            layoutManager = LinearLayoutManager(this@NewInfoActivity)
            adapter = newInfoAdapter
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
