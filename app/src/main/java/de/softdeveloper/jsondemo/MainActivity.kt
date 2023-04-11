package de.softdeveloper.jsondemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.internal.NavigationMenu
import com.google.android.material.tabs.TabLayout.TabGravity
import de.softdeveloper.jsondemo.databinding.ActivityMainBinding
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.nio.file.attribute.UserPrincipalLookupService
import java.util.jar.Attributes

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

        val rootObject = JSONObject(jsonString)
        val userArrayJson: JSONArray
        if(rootObject.has(USERS))
            userArrayJson = rootObject.getJSONArray(USERS)
        else
            userArrayJson = JSONArray()

        for(index in 0 until userArrayJson.length()){
            var name :String? = null
            var job: String? = null
            var age: Int? = null
            var city: String? = null
            var hobbys: ArrayList<String?> = ArrayList()
            var PhonePrivate:String? = null
            var PhoneOffice:String? = null
            var PhoneMobile:String? = null

            val userJson = userArrayJson.getJSONObject(index)

            if(userJson.has(NAME))
                name = userJson.getString(NAME)
            if(userJson.has(JOB))
                job = userJson.getString(JOB)
            if(userJson.has(AGE))
                age = userJson.getInt(AGE)
            if(userJson.has(CITY))
                city = userJson.getString(CITY)
            if(userJson.has(HOBBYS)){
                val hobbyArray = userJson.getJSONArray(HOBBYS)
                for (indexHobby in 0 until hobbyArray.length()){
                    hobbys.add(hobbyArray.getString(indexHobby))
                }
            }

            if(userJson.has(PHONE)){
                val phoneUser = userJson.getJSONObject(PHONE)

                if(phoneUser.has(PRIVATE))
                    PhonePrivate = phoneUser.getString(PRIVATE)
                if(phoneUser.has(OFFICE))
                    PhoneOffice = phoneUser.getString(OFFICE)
                if(phoneUser.has(MOBILE))
                    PhoneMobile = phoneUser.getString(MOBILE)

            }

            val user = User(name,job,age,city,hobbys,Phone(PhonePrivate,PhoneOffice,PhoneMobile))
            userList.add(user)
        }

        userList.forEach {
            binding.tvOutput.append("\n$it")
        }
        
    }

    companion object{
        val USERS = "users"
        val NAME = "name"
        val JOB = "job"
        val AGE = "age"
        val CITY = "city"
        val HOBBYS = "hobbys"
        val PHONE = "phone"
        val PRIVATE = "private"
        val OFFICE = "office"
        val MOBILE = "mobile"
    }
}