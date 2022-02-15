package com.works.glycemicindex.ui.tableMenu

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.works.glycemicindex.R
import com.works.glycemicindex.databinding.ActivityTableDetailBinding
import com.works.glycemicindex.db.DB

class TableDetail : AppCompatActivity() {
    lateinit var bind : ActivityTableDetailBinding
    lateinit var db : DB
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        bind = ActivityTableDetailBinding.inflate(layoutInflater)
        setContentView(bind.root)
        supportActionBar?.hide()

        window.statusBarColor = ContextCompat.getColor(this, R.color.black)


        db = DB(this)
        val tableID = intent.getIntExtra("tableID", 0)
        val tableName  = intent.getStringExtra("tableName")

        bind.txtTableName.setText(tableName)

        bind.btnTableUpdate.setOnClickListener {
            val newName = bind.txtTableName.text.toString()
            if (TextUtils.isEmpty(newName)){
                bind.txtTableName.error = "Boş Bırakılamaz"
                bind.txtTableName.requestFocus()
                return@setOnClickListener
            }

            val alert = AlertDialog.Builder(bind.root.context)
            alert.setTitle("Güncelleme İşlemi!")
            alert.setMessage("Güncellemek istediğinizden emin misiniz? " +
                    "\n\nEski Tablo Adı: ${tableName}\nYeni Tablo Adı: ${newName}")

            alert.setNegativeButton("İptal", DialogInterface.OnClickListener { dialogInterface, i ->  })
            alert.setPositiveButton("Evet", DialogInterface.OnClickListener { dialogInterface, i ->

                db.updateTable(tableID, newName)
                Toast.makeText(this@TableDetail, "Tablo Güncellendi", Toast.LENGTH_SHORT).show()
                db.filterFood(tableName!!).forEach {
                    db.moveFood(it.foodID!!, newName)
                }

            })
            alert.show()

        }
    }
}