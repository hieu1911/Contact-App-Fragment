package com.example.contactappfragment

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar

val items = arrayListOf<ItemModel>(
    ItemModel(1, "David", "0928234876", "david@gmail.com"),
    ItemModel(2, "John", "0123456789", "john@gmail.com"),
    ItemModel(3, "Emily", "0231458876", "emily@gmail.com"),
    ItemModel(4, "Sarah", "0987864128", "sarah@gmail.com"),
    ItemModel(5, "Michael", "0369852147", "michael@gmail.com"),
    ItemModel(6, "Jessica", "0857496321", "jessica@gmail.com"),
    ItemModel(7, "Daniel", "0789632145", "daniel@gmail.com"),
    ItemModel(8, "Olivia", "0258964713", "olivia@gmail.com"),
    ItemModel(9, "William", "0856932147", "william@gmail.com"),
    ItemModel(10, "Sophia", "0896547123", "sophia@gmail.com")
)
val listFragment = ListFragment.newInstance(items)

class MainActivity : AppCompatActivity(), AddContactFragment.OnContactAddedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = "Contact App"
        toolbar.setTitleTextColor(Color.WHITE)
        setSupportActionBar(toolbar)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, listFragment)
            .commit()
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.item_menu, menu)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.new_contact -> {
                val newContactFragment = AddContactFragment.newInstance(items.size)
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, newContactFragment)
                    .commit()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onContactAdded(item: ItemModel) {
        items.add(item)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, listFragment)
            .commit()
    }
}