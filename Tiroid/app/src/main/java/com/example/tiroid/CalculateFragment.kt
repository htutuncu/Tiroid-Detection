package com.example.tiroid

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.tiroid.databinding.FragmentCalculateBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class CalculateFragment : Fragment(R.layout.fragment_calculate) {
    lateinit var sp : SharedPreferences
    private var binding : FragmentCalculateBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sp = requireActivity().applicationContext.getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bindingb = FragmentCalculateBinding.bind(view)
        binding = bindingb

        binding!!.tvNameWelcome.text = sp.getString("name","")

        binding!!.llCalculate.setOnClickListener(View.OnClickListener {
            calculateResult()
        })
    }

    inline fun <reified T> genericType() = object: TypeToken<T>() {}.type

    fun calculateResult():Unit{
        var result : String = ""
        val tst = binding!!.tilTst.editText!!.text.toString().toDouble()
        val tsh = binding!!.tilTsh.editText!!.text.toString().toDouble()
        val t3 = binding!!.tilT3.editText!!.text.toString().toDouble()
        val madtsh = binding!!.tilMadtsh.editText!!.text.toString().toDouble()
        val my : TiroidActivity = activity as TiroidActivity
        var mList = my.getList()
        val gson = Gson()
        val json = sp.getString("result list",null)
        if (json!=null) {
            val turnsType = genericType<List<Result>>()
            mList = gson.fromJson(json as String,turnsType)
        }

        if ( mList == null ) {
            mList = mutableListOf()
        }

        if (tst > 13.9) {
            result = "Hyper"
        } else {
            if (tsh > 4){
                result = "Hypo"
            } else {
                if ( t3 <= 99.5) {
                    if (tst>10.75) {
                        result = "Hyper"
                    } else {
                        result = "Normal"
                    }
                } else {
                    if (madtsh > 6.3) {
                        if (tst > 6.6) {
                            result = "Normal"
                        } else {
                            result = "Hypo"
                        }
                    } else {
                        result = "Normal"
                    }
                }
            }
        }
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Tiroid Tanısı")
        builder.setMessage(result)
        builder.setPositiveButton("Tamam"){dialogInterface, which ->
        }
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(true)
        alertDialog.show()

        val resul = Result(tst,tsh,t3,madtsh,result)
        mList.add(resul)
        setList(mList)

    }

    fun setList(mList:MutableList<Result>):Unit{
        var editor = sp.edit()
        val gson : Gson = Gson()
        val json = gson.toJson(mList)
        editor.putString("result list",json)
        editor.apply()
    }

}