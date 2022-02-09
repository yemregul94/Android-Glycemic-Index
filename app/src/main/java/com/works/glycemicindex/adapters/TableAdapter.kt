package com.works.glycemicindex.adapters

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.works.glycemicindex.databinding.TableRowBinding
import com.works.glycemicindex.db.DB
import com.works.glycemicindex.models.TableModel
import com.works.glycemicindex.ui.tableMenu.TableDetail

class TableAdapter (val arr: ArrayList<TableModel>) : RecyclerView.Adapter<TableAdapter.ViewHolder>() {

    class ViewHolder(val bind: TableRowBinding) : RecyclerView.ViewHolder(bind.root)

    override fun getItemCount(): Int {
        return arr.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableAdapter.ViewHolder {
        val bind = TableRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TableAdapter.ViewHolder(bind)
    }

    override fun onBindViewHolder(holder: TableAdapter.ViewHolder, position: Int) {
        val item = arr.get(position)
        val db = DB(holder.bind.root.context)

        holder.bind.apply {
            txtTableName.text = item.tableName

            tableRowCardView.setOnClickListener {
                val i = Intent(holder.bind.root.context, TableDetail::class.java)
                i.putExtra("tableID", item.tableID)
                i.putExtra("tableName", item.tableName)
                holder.bind.root.context.startActivity(i)

            }

            tableRowCardView.setOnLongClickListener {
                val alert = AlertDialog.Builder(holder.bind.root.context)
                alert.setTitle("Silme Kontrolü!")
                alert.setMessage("Tabloyu TÜM içeriğiyle beraber silmek istediğinizden emin misiniz? \n\nSilinecek Tablo: ${txtTableName.text}")
                alert.setNegativeButton("İptal", DialogInterface.OnClickListener { dialogInterface, i ->  })
                alert.setPositiveButton("Evet", DialogInterface.OnClickListener { dialogInterface, i ->

                    db.filterFood(item.tableName!!).forEach {
                        db.deleteFood(it.foodID!!)
                    }

                    db.deleteTable(item.tableID!!)
                    arr.removeAt(position)
                    notifyDataSetChanged()
                })
                alert.show()
                true
            }
        }

    }

}