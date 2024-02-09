package ru.kaspenium.database

import android.os.Bundle
import android.view.Gravity
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val table = findViewById<TableLayout>(R.id.table)
        val param = TableRow.LayoutParams(
            TableRow.LayoutParams.WRAP_CONTENT,
            TableRow.LayoutParams.WRAP_CONTENT,
            1f
        )
        val db = DBHelper(this, null)
        val names = arrayOf(
            "Даниил",
            "Константин",
            "Максим",
            "Владислав",
            "Евгений",
            "Сергей",
            "Александр",
            "Григорий",
            "Егор",
            "Валерий",
            "Яша"
        )
        val c = db.getName()
        if (!c!!.moveToFirst()) {
            for (i in 1..100) {
                db.addName(
                    names[(0 until 11).random()],
                    (50..100).random(),
                    (140..200).random(),
                    (15..25).random()
                )
            }
        }
        do {
            val tableRow = TableRow(this)
            tableRow.layoutParams = TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT
            )
            val name = TextView(this)
            name.setLayoutParams(param);
            name.gravity = Gravity.CENTER
            name.text = c.getString(1)
            val weight = TextView(this)
            weight.setLayoutParams(param);
            weight.gravity = Gravity.CENTER
            weight.text = c.getString(2)
            val height = TextView(this)
            height.setLayoutParams(param);
            height.gravity = Gravity.CENTER
            height.text = c.getString(3)
            val age = TextView(this)
            age.setLayoutParams(param);
            age.gravity = Gravity.CENTER
            age.text = c.getString(4)
            tableRow.addView(name)
            tableRow.addView(weight)
            tableRow.addView(height)
            tableRow.addView(age)
            table.addView(tableRow)
        } while (c.moveToNext())
    }
}
