package com.bhavinpracticalinfosys.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.bhavinpracticalinfosys.R
import com.bhavinpracticalinfosys.api.RequestState
import com.bhavinpracticalinfosys.extension.getViewModel
import com.bhavinpracticalinfosys.ui.base.BaseActivity
import com.bhavinpracticalinfosys.utils.SnackBarHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private val viewModel by lazy {
        getViewModel<MainViewModel>()
    }

    private val adapter by lazy {
        FeedListAdapter()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setRecycleView()
        setupObservers()
        setToolbar("")
        setClickListener()
        viewModel.getFeeds()

    }

    private fun setRecycleView() {
        rv_feed.setHasFixedSize(true)
        rv_feed.layoutManager = LinearLayoutManager(this@MainActivity)
        rv_feed.itemAnimator = DefaultItemAnimator()
        rv_feed.adapter = adapter
    }

    private fun setClickListener() {
        txt_error.setOnClickListener { viewModel.retry() }
    }

    private fun View.setVisible(visible: Boolean) {
        visibility = if (visible) View.VISIBLE else View.GONE
    }

    private fun setToolbar(txt: String?) {
        setSupportActionBar(toolbar)
        supportActionBar?.title = txt ?: "Title"
        supportActionBar?.setDisplayShowTitleEnabled(true)
    }

    private fun setupObservers() {
        viewModel.feeds.observe(this, Observer {
            progress_bar.setVisible(it.status == RequestState.State.PROGRESS)
            txt_error.setVisible(
                it.status == RequestState.State.INTERNET_ERROR
                        || it.status == RequestState.State.API_ERROR
                        || it.status == RequestState.State.FAILURE
            )
            if (it.status == RequestState.State.SUCCESS) {
                it?.data?.let {
                    setToolbar(it.title)
                    adapter.setData(it.rows)
                }
            }
        })
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_refresh) {
            viewModel.getFeeds()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}