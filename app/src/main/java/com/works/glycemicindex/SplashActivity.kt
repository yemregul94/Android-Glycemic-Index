package com.works.glycemicindex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.core.content.ContextCompat
import com.works.glycemicindex.databinding.ActivitySplashBinding
import com.works.glycemicindex.db.DB
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.io.IOException

class SplashActivity : AppCompatActivity() {

    private lateinit var bind: ActivitySplashBinding
    lateinit var DB: DB

    override fun onCreate(savedInstanceState: Bundle?) {
        bind = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(bind.root)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        window.statusBarColor = ContextCompat.getColor(this, R.color.black)
        supportActionBar?.hide()

        DB = DB(this)

        htmlParse()
    }

    private fun htmlParse() {
        Thread(Runnable {
            try {
                var tableName = ""
                val doc: Document =
                    Jsoup.connect("http://kolaydoktor.com/saglik-icin-yasam/diyet-ve-beslenme/besinlerin-glisemik-indeks-tablosu/0503/1")
                        .ignoreContentType(true).get()
                val tableBody: Elements =
                    doc.getElementsByClass("contentGeneral").select("table").select("tbody")
                val tableRow: Elements = tableBody.select("tr")
                for (tableData in tableRow) {
                    if (tableData.select("td").size == 1) {
                        tableName = tableData.select("td").text().toString()
                        DB.addTable(tableName)
                    } else if (tableData == tableRow[1]) {
                        //Başlık Satırları
                    } else if (tableData.select("td")[0].text() != "Besin maddesi") {

                        var carbohydrate = 0f
                        var calorie = 0

                        val name = tableData.select("td")[0].text()
                        val glycemic = tableData.select("td")[1].text().toInt()
                        if (tableData.select("td")[2].text() != "") {
                            carbohydrate =
                                tableData.select("td")[2].text().replace(",", ".").toFloat()
                        }
                        if (tableData.select("td")[3].text() != "") {
                            calorie = tableData.select("td")[3].text().toInt()
                        }
                        val table = tableName

                        DB.addFood(name, glycemic, carbohydrate, calorie, table)

                    }
                }
                finish()
            } catch (e: IOException) {
                Log.d("Error", "htmlParse Error: $e")
            }

        }).start()
    }
}