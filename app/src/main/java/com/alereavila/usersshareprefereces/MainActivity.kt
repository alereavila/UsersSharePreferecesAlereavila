package com.alereavila.usersshareprefereces

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alereavila.usersshareprefereces.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import kotlin.math.log

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var userAdapter : UserAdapter
    private lateinit var linearLayoutManager : RecyclerView.LayoutManager
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //guardar informacion
        val preferences = getPreferences(Context.MODE_PRIVATE)

        val isFirstTime = preferences.getBoolean(getString(R.string.sp_first_time), true)
        val userNameIntroduced= preferences.getString(getString(R.string.sp_userName), "No se ha ingresado User Name")
        Log.i("SP", "${getString(R.string.sp_first_time)}= $isFirstTime")
        Log.i("SP", "${getString(R.string.sp_userName)}= $userNameIntroduced")


        if(isFirstTime){
            //no siempre se va a suplir al biding este es uno de esos casos
            val dialogView = layoutInflater.inflate(R.layout.dialog_register, null)

            MaterialAlertDialogBuilder(this)
                .setTitle(getString(R.string.dialog_title))

                    //se debe de pasar la vista que se inflo anteriormente
                    .setView(dialogView)
                    .setCancelable(false)
                    .setNeutralButton("Invitado",null)
                    .setPositiveButton(R.string.dialog_confirm, DialogInterface.OnClickListener { dialog, which ->
                    //Guardar lo que se ingresa en el dialogo
                    val userName = dialogView.findViewById<TextInputEditText>(R.id.etUserName).text.toString()
                    //se guardara con una funcion de alcance
                    with(preferences.edit()){
                        //guardar un booleano
                        putBoolean(getString(R.string.sp_first_time), false)
                        //guardar un String en sharePreferences
                        putString(getString(R.string.sp_userName), userName)
                                .apply()

                    }
                        Toast.makeText(this, R.string.register_success,Toast.LENGTH_LONG).show()


                })

                //.setNegativeButton("Cancelar",null)
                .show()
        }else{
            val username =preferences.getString(getString(R.string.sp_userName),getString(R.string.hint_userName))
            Toast.makeText(this,"Binvenido $username", Toast.LENGTH_LONG).show()
        }


        //como la actividad ya trae la interfaz ya se puede usar el this en el userAdapter
        userAdapter = UserAdapter(getUsers(), this)
        linearLayoutManager = LinearLayoutManager(this)
        binding.recycleView.apply {
            //como es fija la altura de la vista android:layout_height="@dimen/card_height"se agrega
            setHasFixedSize(true)
            layoutManager=linearLayoutManager
            adapter = userAdapter
        }
    }
    private fun getUsers (): MutableList<User>{
        val users = mutableListOf<User>()

        val ale = User(1,"Alejandro","Avila","https://media-exp1.licdn.com/dms/image/C4D03AQHgDSfV2je2lA/profile-displayphoto-shrink_100_100/0/1558498161841?e=1636588800&v=beta&t=QG8UPOPEzofxcKccjME4OIJEdXBkebeXId8Xfl4-cAY")
        val silvia =User(2,"Silvia", "Paez","https://contralacorrupcion.mx/wp-content/uploads/2019/06/amlo_nl.jpg")
        val alain = User(3, "Alain", "Nicolás", "https://frogames.es/wp-content/uploads/2020/09/alain-1.jpg")
        val samanta = User(4, "Samanta", "Meza", "https://upload.wikimedia.org/wikipedia/commons/b/b2/Samanta_villar.jpg")
        val javier = User(5, "Javier", "Gómez", "https://live.staticflickr.com/974/42098804942_b9ce35b1c8_b.jpg")
        val emma = User(6, "Emma", "Cruz", "https://upload.wikimedia.org/wikipedia/commons/d/d9/Emma_Wortelboer_%282018%29.jpg")

        users.add(ale)
        users.add(silvia)
        users.add(alain)
        users.add(samanta)
        users.add(javier)
        users.add(emma)
        users.add(ale)
        users.add(silvia)
        users.add(alain)
        users.add(samanta)
        users.add(javier)
        users.add(emma)
        users.add(ale)
        users.add(silvia)
        users.add(alain)
        users.add(samanta)
        users.add(javier)
        users.add(emma)
        users.add(ale)
        users.add(silvia)
        users.add(alain)
        users.add(samanta)
        users.add(javier)
        users.add(emma)

        return users
    }

    override fun onclick(user: User, position:Int) {
        Toast.makeText(this,"$position:  ${user.getFullName()}", Toast.LENGTH_LONG).show()
    }
}