package com.example.nextprevimages

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.nextprevimages.models.Record
import com.example.nextprevimages.models.RecordPOJO
import com.example.nextprevimages.utils.Status
import com.example.nextprevimages.utils.api.AppCreator
import com.example.nextprevimages.viewmodels.RecordViewModel
import com.example.nextprevimages.viewmodels.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mRecordViewModel: RecordViewModel
    private lateinit var mRecordResponse: RecordPOJO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initData()
        obtainListFromServer()

        nextBtn.setOnClickListener {
            handleBtnClick(it)
        }
        prevBtn.setOnClickListener {
            handleBtnClick(it)
        }
    }

    private fun obtainListFromServer() {
        mRecordViewModel.getAllRecord().observe(this)
        {
            when (it.status) {
                Status.SUCCESS -> {
                    mRecordResponse = it.data!!
                    getData()
                }
                Status.FAILURE -> {
                    Toast.makeText(
                        this,
                        "Failed to load the data ${it.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                Status.LOADING -> {
                    Toast.makeText(
                        this,
                        "Loading...",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun getData() {
        val description = mRecordResponse.getDescription()!!
        val link = mRecordResponse.getLink()!!

        mRecordViewModel.addNewRecord(Record(description, link))

        recordText.text = description
        Glide.with(this@MainActivity).load(link.replace("http", "https")).into(imageView)
    }

    private fun initData() {
        mRecordViewModel = ViewModelProvider(
            this,
            ViewModelFactory(AppCreator.getApiHelperInstance())
        ).get(RecordViewModel::class.java)
    }

    private fun handleBtnClick(view: View) {
        if (view.id == R.id.nextBtn) {
            prevBtn.isEnabled = true
            if (mRecordViewModel.listImg.lastIndex == mRecordViewModel.getCurrentIndex()) {
                obtainListFromServer()
            } else {
                try {
                    mRecordViewModel.incIndex()
                    val prevItem = mRecordViewModel.listImg[mRecordViewModel.getCurrentIndex()]
                    recordText.text = prevItem.description
                    Glide.with(this@MainActivity)
                        .load(prevItem.link.replace("http", "https")).into(imageView)
                } catch (e: Exception) {
                    nextBtn.isEnabled = false
                    prevBtn.isEnabled = false
                    println("Error " + e.message.toString())
                }
            }

        } else if (view.id == R.id.prevBtn) {
            mRecordViewModel.decIndex()
            if (mRecordViewModel.getCurrentIndex() == 0) {
                prevBtn.isEnabled = false
            }
            val prevItem = mRecordViewModel.listImg[mRecordViewModel.getCurrentIndex()]
            recordText.text = prevItem.description
            Glide.with(this@MainActivity)
                .load(prevItem.link.replace("http", "https")).into(imageView)
        }
    }
}