package com.works.glycemicindex.ui.foodMenu

import android.R
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.works.glycemicindex.databinding.FragmentAddFoodBinding
import com.works.glycemicindex.db.DB

class AddFoodFragment : Fragment() {

    private var _binding: FragmentAddFoodBinding? = null
    private val bind get() = _binding!!
    lateinit var db : DB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAddFoodBinding.inflate(inflater, container, false)
        val root: View = bind.root

        db = DB(bind.root.context)
        val tableArr = db.getTableNames()

        val tableSpinner = ArrayAdapter(bind.root.context, R.layout.simple_spinner_item, tableArr)
        tableSpinner.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        bind.spinner.setAdapter(tableSpinner)



        bind.btnAdd.setOnClickListener {

                val newName = bind.txtName.text.toString()
                if (TextUtils.isEmpty(newName)){
                    bind.txtName.error = "Boş Bırakılamaz"
                    bind.txtName.requestFocus()
                    return@setOnClickListener
                }

                val newGlycemic = bind.txtGlycemic.text.toString()
                if (TextUtils.isEmpty(newGlycemic)){
                    bind.txtGlycemic.error = "Boş Bırakılamaz"
                    bind.txtGlycemic.requestFocus()
                    return@setOnClickListener
                }

                val newCarbo = bind.txtCarbo.text.toString()
                if (TextUtils.isEmpty(newCarbo)){
                    bind.txtCarbo.error = "Boş Bırakılamaz"
                    bind.txtCarbo.requestFocus()
                    return@setOnClickListener
                }

                val newCalorie = bind.txtCalorie.text.toString()
                if (TextUtils.isEmpty(newCalorie)){
                    bind.txtCalorie.error = "Boş Bırakılamaz"
                    bind.txtCalorie.requestFocus()
                    return@setOnClickListener
                }

            val newTable = bind.spinner.selectedItem.toString()

            db.addFood(newName, newGlycemic.toInt(), newCarbo.toFloat(), newCalorie.toInt(), newTable)

            Toast.makeText(bind.root.context, "Besin Eklendi", Toast.LENGTH_SHORT).show()
            bind.txtName.setText("")
            bind.txtGlycemic.setText("")
            bind.txtCarbo.setText("")
            bind.txtCalorie.setText("")
            bind.spinner.setSelection(0)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}