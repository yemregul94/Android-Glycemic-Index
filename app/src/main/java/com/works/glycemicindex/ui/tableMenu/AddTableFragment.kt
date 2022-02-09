package com.works.glycemicindex.ui.tableMenu

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.works.glycemicindex.adapters.TableAdapter
import com.works.glycemicindex.databinding.FragmentAddTableBinding
import com.works.glycemicindex.db.DB

class AddTableFragment : Fragment() {

    private var _binding: FragmentAddTableBinding? = null
    private val bind get() = _binding!!
    lateinit var db : DB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAddTableBinding.inflate(inflater, container, false)
        val root: View = bind.root
        updateView()


        bind.btnAdd.setOnClickListener {
            val tableName = bind.txtTableName.text.toString().uppercase()
            if (TextUtils.isEmpty(tableName)){
                bind.txtTableName.error = "Boş Bırakılamaz"
                bind.txtTableName.requestFocus()
                return@setOnClickListener
            }
            db.addTable(tableName)
            Toast.makeText(bind.root.context, "Tablo Eklendi", Toast.LENGTH_SHORT).show()
            bind.txtTableName.setText("")
            updateView()
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
        db = DB(bind.root.context)
        bind.tableList.layoutManager = LinearLayoutManager(bind.root.context, LinearLayoutManager.VERTICAL, false)
        val tables = db.allTable()
        bind.tableList.adapter = TableAdapter(tables)
    }
}