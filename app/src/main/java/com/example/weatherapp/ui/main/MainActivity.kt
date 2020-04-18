package com.example.weatherapp.ui.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.*
import com.example.weatherapp.R
import com.example.weatherapp.base.BaseActivity
import com.example.weatherapp.model.Status
import com.example.weatherapp.model.dataclass.FindItem
import com.example.weatherapp.util.Util
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
    }
    private val adapter = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initAdapter()
        initLiveData()
        initListener()
    }

    private fun initAdapter() {
        rvMainWeather.layoutManager = LinearLayoutManager(this)
        rvMainWeather.itemAnimator = DefaultItemAnimator()
        val dividerItemDecoration = DividerItemDecoration(
            this,
            (rvMainWeather.layoutManager as LinearLayoutManager).orientation
        )
        rvMainWeather.addItemDecoration(dividerItemDecoration)
        val itemTouchHelper = ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                adapter.removeItem(viewHolder.adapterPosition)
            }
        })
        itemTouchHelper.attachToRecyclerView(rvMainWeather)
        rvMainWeather.adapter = adapter
    }

    private fun initLiveData() {
        viewModel.getFindResponseLiveData().observe(this, Observer { result ->
            when (result.status) {
                Status.LOADING -> startLoading()
                Status.SUCCESS -> {
                    adapter.setData(result.data!!.list as ArrayList<FindItem>)
                    completeLoading()
                }
                Status.ERROR -> errorLoading(result.throwable!!.message)
            }
        })
    }

    private fun initListener() {
        btnDiscoverWeather.setOnClickListener {
            viewModel.loadWeather(etLatitude.text.toString(), etLongitude.text.toString())
            Util.hideKeyboard(this)
        }
    }

    private fun startLoading() {
        rvMainWeather.visibility = View.GONE
        svErrorContainer.visibility = View.GONE
        pbMainWeather.visibility = View.VISIBLE
    }

    private fun completeLoading() {
        pbMainWeather.visibility = View.GONE
        svErrorContainer.visibility = View.GONE
        rvMainWeather.visibility = View.VISIBLE
    }

    private fun errorLoading(errorText: String?) {
        rvMainWeather.visibility = View.GONE
        pbMainWeather.visibility = View.GONE
        svErrorContainer.visibility = View.VISIBLE
        errorText?.let { tvErrorText.text = it }
    }
}
