package com.example.tiroid

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tiroid.databinding.FragmentResultsBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class ResultsFragment : Fragment(R.layout.fragment_results) {
    lateinit var sp : SharedPreferences
    private var binding : FragmentResultsBinding? = null
    lateinit var mRecyclerView: RecyclerView
    lateinit var mAdapter: RecyclerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sp = requireActivity().applicationContext.getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bindingb = FragmentResultsBinding.bind(view)
        binding = bindingb

        fetch()
    }

    fun fetch(){
        val my : TiroidActivity = activity as TiroidActivity
        var mList = my.getList()
        val gson = Gson()
        val json = sp.getString("result list",null)
        val turnsType = genericType<List<Result>>()
        mList = gson.fromJson(json,turnsType)

        if ( mList == null ) {
            mList = mutableListOf()
        }

        mRecyclerView = binding!!.rvResults
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(context)
        mAdapter = RecyclerAdapter(requireContext(),mList)
        mRecyclerView.adapter = mAdapter
    }


    inline fun <reified T> genericType() = object: TypeToken<T>() {}.type
}