package com.works.glycemicindex.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.works.glycemicindex.models.FoodModel
import com.works.glycemicindex.models.TableModel

class DB (context: Context?, name: String? = "project.db", factory: SQLiteDatabase.CursorFactory? = null, version: Int = 1)
    : SQLiteOpenHelper(context, name, factory,version) {
    override fun onCreate(db: SQLiteDatabase?) {

        db!!.execSQL("CREATE TABLE \"foods\" (\n" +
                "\t\"foodID\"\tINTEGER,\n" +
                "\t\"name\"\tTEXT,\n" +
                "\t\"glycemic\"\tINTEGER,\n" +
                "\t\"carbohydrate\"\tREAL,\n" +
                "\t\"calorie\"\tINTEGER,\n" +
                "\t\"tableName\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"foodID\" AUTOINCREMENT)\n" +
                ");")

        db.execSQL("CREATE TABLE \"tableNames\" (\n" +
                "\t\"tableID\"\tINTEGER,\n" +
                "\t\"tableName\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"tableID\" AUTOINCREMENT)\n" +
                ");")
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS foods")
        db.execSQL("DROP TABLE IF EXISTS tableNames")
        onCreate(db)
    }


    fun addFood (name: String, glycemic: Int, carbohydrate: Float, calorie: Int, tableName: String) {
        val write = this.writableDatabase
        val values = ContentValues()

        values.put("name", name)
        values.put("glycemic", glycemic)
        values.put("carbohydrate", carbohydrate)
        values.put("calorie", calorie)
        values.put("tableName", tableName)

        write.insert("foods", null, values)
    }

    fun addTable (tableName: String) {
        val write = this.writableDatabase
        val values = ContentValues()

        values.put("tableName", tableName)
        write.insert("tableNames", null, values);
    }

    fun allFood() : ArrayList<FoodModel> {
        val list = ArrayList<FoodModel>()
        val read = this.readableDatabase
        val querySql = "select * from foods"
        val cursor = read.rawQuery(querySql, null)
        while (cursor.moveToNext()) {
            val foodID = cursor.getInt(0)
            val name = cursor.getString(1)
            val glycemic = cursor.getInt(2)
            val carbohydrate = cursor.getFloat(3)
            val calorie = cursor.getInt(4)
            val tableName = cursor.getString(5)

            val f = FoodModel(foodID, name, glycemic, carbohydrate, calorie, tableName)
            list.add(f)
        }
        return list
    }

    fun allTable() : ArrayList<TableModel> {
        val list = ArrayList<TableModel>()
        val read = this.readableDatabase
        val querySql = "select * from tableNames"
        val cursor = read.rawQuery(querySql, null)
        while (cursor.moveToNext()) {
            val tableID = cursor.getInt(0)
            val tableName = cursor.getString(1)


            val f = TableModel(tableID, tableName)
            list.add(f)
        }
        return list
    }

    fun getTableNames() : ArrayList<String> {
        val list = ArrayList<String>()
        val read = this.readableDatabase
        val querySql = "select * from tableNames"
        val cursor = read.rawQuery(querySql, null)
        while (cursor.moveToNext()) {
            val tableID = cursor.getInt(0)
            val tableName = cursor.getString(1)

            val f = tableName
            list.add(f)
        }
        return list
    }

    fun deleteFood( foodID:Int ) {
        val write = this.writableDatabase
        write.delete("foods", "foodID="+foodID,null)
    }

    fun deleteTable( tableID:Int ) : Int {
        val write = this.writableDatabase
        val count = write.delete("tableNames", "tableID="+tableID,null)
        return count
    }

    fun moveFood(foodID: Int, tableName: String) {
        val write = this.writableDatabase
        val values = ContentValues()
        values.put("foodID", foodID)
        values.put("tableName", tableName)
        write.update("foods", values, "foodID = ?", arrayOf(foodID.toString()))
    }

    fun updateFood(foodID: Int, name: String, glycemic:Int, carbohydrate: Float, calorie: Int, tableName: String) {
        val write = this.writableDatabase
        val values = ContentValues()
        values.put("foodID", foodID)
        values.put("name", name)
        values.put("glycemic", glycemic)
        values.put("carbohydrate", carbohydrate)
        values.put("calorie", calorie)
        values.put("tableName", tableName)

        write.update("foods", values, "foodID = ?", arrayOf(foodID.toString()))
    }

    fun updateTable(tableID: Int, tableName: String): Int {
        val write = this.writableDatabase
        val values = ContentValues()
        values.put("tableID", tableID)
        values.put("tableName", tableName)

        return write.update("tableNames", values, "tableID = ?", arrayOf(tableID.toString()))
    }

    fun filterFood( filter: String= "nofilter", minIndex: Int = 0, maxIndex: Int = 999) : ArrayList<FoodModel> {
        val list = ArrayList<FoodModel>()
        val read = this.readableDatabase
        val querySql = "select * from foods where glycemic between $minIndex and $maxIndex"

        val cursor = read.rawQuery(querySql, null)
        while (cursor.moveToNext()) {
            val foodID = cursor.getInt(0)
            val name = cursor.getString(1)
            val glycemic = cursor.getInt(2)
            val carbohydrate = cursor.getFloat(3)
            val calorie = cursor.getInt(4)
            val tableName = cursor.getString(5)

            if(filter == "nofilter") {
                val f = FoodModel(foodID, name, glycemic, carbohydrate, calorie, tableName)
                list.add(f)
            }
            else if (tableName.equals(filter, ignoreCase = true)){
                val f = FoodModel(foodID, name, glycemic, carbohydrate, calorie, tableName)
                list.add(f)
            }
        }
        return list
    }

    fun filterMultipleFood( filter: ArrayList<String>, minIndex: Int = 0, maxIndex: Int = 999) : ArrayList<FoodModel> {
        val list = ArrayList<FoodModel>()
        val read = this.readableDatabase
        val querySql = "select * from foods where glycemic between $minIndex and $maxIndex"

        val cursor = read.rawQuery(querySql, null)
        while (cursor.moveToNext()) {
            val foodID = cursor.getInt(0)
            val name = cursor.getString(1)
            val glycemic = cursor.getInt(2)
            val carbohydrate = cursor.getFloat(3)
            val calorie = cursor.getInt(4)
            val tableName = cursor.getString(5)

            filter.forEach {
                if(tableName.contains(it, ignoreCase = true))
                {
                    val f = FoodModel(foodID, name, glycemic, carbohydrate, calorie, tableName)
                    list.add(f)
                }
            }
        }
        return list
    }

    fun findFood(str: Any) : ArrayList<FoodModel> {
        val list = ArrayList<FoodModel>()

        if(str != "")
        {
            val read = this.readableDatabase
            val querySql = "select * from foods where name like '%$str%' OR glycemic like '%${str}%' OR carbohydrate like '${str}%' OR calorie like '%${str}%'"

            val cursor = read.rawQuery(querySql, null)
            while (cursor.moveToNext()) {
                val foodID = cursor.getInt(0)
                val name = cursor.getString(1)
                val glycemic = cursor.getInt(2)
                val carbohydrate = cursor.getFloat(3)
                val calorie = cursor.getInt(4)
                val tableName = cursor.getString(5)
                val f = FoodModel(foodID, name, glycemic, carbohydrate, calorie, tableName)
                list.add(f)
            }
        }
        else {
            return allFood()
        }
            return list
    }
}
