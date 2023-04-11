package de.softdeveloper.jsondemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.tabs.TabLayout.TabGravity
import de.softdeveloper.jsondemo.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val TAG = this.javaClass.simpleName
    private var userList: ArrayList<User> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val inputStream = assets.open("users.json")
        // auf alter Java Basis wie folgt zu verwenden
//        val isr = InputStreamReader(inputStream)
//        val br = BufferedReader(isr)
//        val jsonString = br.readText()
        // so geht es aktuell
        val jsonString = inputStream.bufferedReader().use {bufferedReader -> 
            bufferedReader.readText()
        }
        Log.d(TAG, "onCreate: JsonString -> $jsonString")
        
        
    }
}