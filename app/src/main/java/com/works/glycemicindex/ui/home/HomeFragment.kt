package com.works.glycemicindex.ui.home

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.InputType
import android.text.TextUtils
import android.util.Log
import android.view.*
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.works.glycemicindex.R
import com.works.glycemicindex.adapters.HomeAdapter
import com.works.glycemicindex.databinding.FragmentHomeBinding
import com.works.glycemicindex.db.DB


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val bind get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,

    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = bind.root
        setHasOptionsMenu(true)

            val fab: View = bind.floatingActionButton
            fab.setOnClickListener { view ->

            val checkLayout = LinearLayout(bind.root.context)
                checkLayout.orientation = LinearLayout.VERTICAL

            val rangeLayout = LinearLayout(bind.root.context)
                rangeLayout.orientation = LinearLayout.HORIZONTAL

            var minTxt = EditText(bind.root.context)
                minTxt.hint = "Min Değer"
                minTxt.inputType = InputType.TYPE_CLASS_NUMBER
                minTxt.gravity = Gravity.CENTER
            var maxTxt = EditText(bind.root.context)
                maxTxt.hint = "Max Değer"
                maxTxt.inputType = InputType.TYPE_CLASS_NUMBER
                maxTxt.gravity = Gravity.CENTER

                rangeLayout.addView(minTxt)
                rangeLayout.addView(maxTxt)
                rangeLayout.gravity = Gravity.CENTER

            DB(bind.root.context).getTableNames().forEachIndexed { index, str ->
                val checkBox = CheckBox(bind.root.context)
                checkBox.id = index
                checkBox.text = str
                checkBox.isChecked = true
                checkLayout.addView(checkBox)
            }

            val customLayout = LinearLayout(bind.root.context)
                customLayout.orientation = LinearLayout.VERTICAL

            customLayout.addView(rangeLayout)
            customLayout.addView(checkLayout)

            val alert = AlertDialog.Builder(bind.root.context)
            alert.setTitle("Filtreleme Penceresi")
            alert.setView(customLayout)
            alert.setNegativeButton("İptal", DialogInterface.OnClickListener { dialogInterface, i ->  })
            alert.setPositiveButton("Uygula", DialogInterface.OnClickListener { dialogInterface, i ->
                var min = minTxt.text.toString()
                var max = maxTxt.text.toString()
                val filterStr = ArrayList<String>()

                DB(bind.root.context).getTableNames().forEachIndexed { index, s ->
                    val item = checkLayout.get(index) as CheckBox
                    if(item.isChecked){
                        filterStr.add(item.text.toString())
                    }
                }

                if (TextUtils.isEmpty(min)){
                    min = "0"
                }
                if (TextUtils.isEmpty(max)){
                    max = "999"
                }

                val filterFoods = DB(bind.root.context).filterMultipleFood(filterStr, min.toInt(), max.toInt())
                bind.foodList.adapter = HomeAdapter(filterFoods)
            })
            alert.show()
        }

        return root
    }

    override fun onResume() {
        super.onResume()
        updateView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun updateView() {
        val db = DB(bind.root.context)
        bind.foodList.layoutManager = LinearLayoutManager(bind.root.context, LinearLayoutManager.VERTICAL, false)
        val foods = db.allFood()
        bind.foodList.adapter = HomeAdapter(foods)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(com.works.glycemicindex.R.menu.home, menu)

        val menuItem = menu.findItem(com.works.glycemicindex.R.id.action_search)
        val searchView = menuItem.actionView as SearchView

        searchView.requestFocus()
        searchView.queryHint = "Arama Metni Giriniz"


        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(text: String?): Boolean {
                Log.d("SearchBar", "onQueryTextChange: $text")

                val filterFoods = DB(bind.root.context).findFood(text!!)
                bind.foodList.adapter = HomeAdapter(filterFoods)
                return true
            }
        })
    }

}