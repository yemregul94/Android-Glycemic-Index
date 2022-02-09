package com.works.glycemicindex.ui.foodMenu

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.works.glycemicindex.R
import com.works.glycemicindex.databinding.ActivityHomeDetailBinding
import com.works.glycemicindex.db.DB

class FoodDetail : AppCompatActivity() {

    lateinit var bind : ActivityHomeDetailBinding
    lateinit var db : DB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityHomeDetailBinding.inflate(layoutInflater)
        setContentView(bind.root)

        window.statusBarColor = ContextCompat.getColor(this, R.color.black)



        db = DB(this)
        val foodID = intent.getIntExtra("foodID", 0)
        val name = intent.getStringExtra("name")
        val glycemic = intent.getIntExtra("glycemic", 0)
        val carbohydrate = intent.getFloatExtra("carbohydrate", 0f)
        val calorie = intent.getIntExtra("calorie", 0)
        val table = intent.getStringExtra("table")

        val tableArr = db.getTableNames()

        val tableSpinner = ArrayAdapter(this, android.R.layout.simple_spinner_item, tableArr)
        tableSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        bind.txtName.setText(name)
        bind.txtGlycemic.setText(glycemic.toString())
        bind.txtCarbo.setText(carbohydrate.toString())
        bind.txtCalorie.setText(calorie.toString())
        bind.spinner.setAdapter(tableSpinner)
        bind.spinner.setSelection(tableArr.indexOf(table))

        bind.btnUpdate.setOnClickListener {
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

            val alert = AlertDialog.Builder(bind.root.context)
            alert.setTitle("Güncelleme İşlemi!")
            alert.setMessage("Güncellemek istediğinizden emin misiniz? " +
                    "\n\nEski İsim: ${name}\nYeni İsim: ${newName}" +
                    "\n\nEski Glisemik: ${glycemic}\nYeni Glisemik: ${newGlycemic}" +
                    "\n\nEski Karbonhidrat: ${carbohydrate}\nYeni Karbonhidrat: ${newCarbo}" +
                    "\n\nEski Kalori: ${calorie}\nYeni Kalori: ${newCalorie}" +
                    "\n\nEski Tablo: ${table}\nYeni Tablo: ${newTable}")

            alert.setNegativeButton("İptal", DialogInterface.OnClickListener { dialogInterface, i ->  })
            alert.setPositiveButton("Evet", DialogInterface.OnClickListener { dialogInterface, i ->

                db.updateFood(foodID, newName, newGlycemic.toInt(), newCarbo.toFloat(), newCalorie.toInt(), newTable)
                Toast.makeText(this@FoodDetail, "Besin Güncellendi", Toast.LENGTH_SHORT).show()

            })
            alert.show()
        }
    }

}