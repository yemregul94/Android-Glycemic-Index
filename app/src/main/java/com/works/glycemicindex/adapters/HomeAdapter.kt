package com.works.glycemicindex.adapters

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.works.glycemicindex.databinding.HomeRowBinding
import com.works.glycemicindex.db.DB
import com.works.glycemicindex.models.FoodModel
import com.works.glycemicindex.ui.foodMenu.FoodDetail

class HomeAdapter (val arr: ArrayList<FoodModel>) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    class ViewHolder(val bind: HomeRowBinding) : RecyclerView.ViewHolder(bind.root)

    override fun getItemCount(): Int {
        return arr.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val bind = HomeRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(bind)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = arr.get(position)
        val db = DB(holder.bind.root.context)

        holder.bind.apply {
            txtName.text = item.name
            txtIndex.text = item.glycemic.toString()
            txtCarbo.text = "Karbonhidrat: ${item.carbohydrate.toString()}"
            txtCalorie.text = "Kalori: ${item.calorie.toString()}"

            if (item.glycemic!! >= 70) {
                rowIndexCardView.setCardBackgroundColor(Color.RED)
                rowCardView.setCardBackgroundColor(Color.parseColor("#ffdddd"))
            }
            else if (item.glycemic!! < 70 && item.glycemic >= 55) {
                rowIndexCardView.setCardBackgroundColor(Color.YELLOW)
                rowCardView.setCardBackgroundColor(Color.parseColor("#fffaaa"))
            }
            else if (item.glycemic!! < 55 && item.glycemic >= 0) {
                rowIndexCardView.setCardBackgroundColor(Color.GREEN)
                rowCardView.setCardBackgroundColor(Color.parseColor("#ddffaa"))
            }

            rowCardView.setOnClickListener {
                val i = Intent(holder.bind.root.context, FoodDetail::class.java)
                i.putExtra("foodID", item.foodID)
                i.putExtra("name", item.name)
                i.putExtra("glycemic", item.glycemic)
                i.putExtra("carbohydrate", item.carbohydrate)
                i.putExtra("calorie", item.calorie)
                i.putExtra("table", item.table)
                holder.bind.root.context.startActivity(i)
            }

            rowCardView.setOnLongClickListener {
                val alert = AlertDialog.Builder(holder.bind.root.context)
                alert.setTitle("Silme Kontrolü!")
                alert.setMessage("Silmek istediğinizden emin misiniz? \n\nSilinecek Öge: ${txtName.text}")
                alert.setNegativeButton("İptal", DialogInterface.OnClickListener { dialogInterface, i ->  })
                alert.setPositiveButton("Evet", DialogInterface.OnClickListener { dialogInterface, i ->

                    db.deleteFood(item.foodID!!)
                    arr.removeAt(position)
                    notifyDataSetChanged()
                })
                alert.show()
                    true
                }
            }

        }

}
