package com.alereavila.usersshareprefereces

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alereavila.usersshareprefereces.databinding.ItemUserAlterBinding
import com.alereavila.usersshareprefereces.databinding.ItemUserBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

// recibe un listado de usuarios o de cualquier tipo de objeto
//heredamos de una clase ViewHolder que viene de RecyclerView. Adapter
//la clase ViewHolder es personalizable y es la clase interna que se tiene abajo
//para el click en el listener se debe de agregar el argumento listener
class UserAdapter (private val users :List<User>, private val listener : OnClickListener) :
    RecyclerView.Adapter<UserAdapter.ViewHolder> (){
    //para sobreescribir los metodos necesarios presionar
    //ctrl + i

    private lateinit var context: Context

    //aqui se infla una vista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context=parent.context
        //usar el alter o el item_user
        val view = LayoutInflater.from(context).inflate(R.layout.item_user_alter, parent,  false)
        //se debe de instanciar la  inner class creada
        return ViewHolder(view)
    }
//sirve como un for each que recorre el arreglo de users
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//no se si este bien en la clase decía .get(position)
        val user = users[position]

        with(holder){
            //click en el adaptador
            setListener(user,position+1)
            binding.tvOrder.text=(position+1).toString()
            //mejor practica regresar el nombre completo
            binding.tvName.text=user.getFullName()
            //acuerdate de la libreria en el gradle y los permisos de internet en el manifest
            //implementation 'com.github.bumptech.glide:glide:4.12.0'
            //kapt 'com.github.bumptech.glide:compiler:4.12.0'
            //id 'kotlin-kapt'
            //<uses-permission android:name="android.permission.INTERNET"/>
            Glide.with(context)
                .load(user.url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .circleCrop()
                .into(binding.imgPoto)
        }
    }
        //solo necesita el numero de elementos
    override fun getItemCount(): Int = users.size

    //es una clase interna para vincular la vista
    //Se debe de habilitar viewBinding
    /*
    *   buildFeatures{
    *        viewBinding true
    *   }
    *
    * */
    inner class ViewHolder (view:View) :RecyclerView.ViewHolder(view){
        //usar el alter o el item_user
        var binding = ItemUserAlterBinding.bind(view)
        //se necesita un metodo para el click
        fun setListener (user:User, position: Int){
            binding.root.setOnClickListener { listener.onclick(user, position) }
        }
    }


}