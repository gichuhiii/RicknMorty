package com.example.retrofittest
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.retrofittest.network.Character

//use list of characters from character class
class MainAdapter(val characterList: List<Character>) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    inner class MainViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        //function to bind data to RecyclerView
        fun bindData(character: Character) {
            val name = itemView.findViewById<TextView>(R.id.name)
            val image = itemView.findViewById<ImageView>(R.id.image)

            //load image using coil
            name.text = character.name
            image.load(character.image) {
                //how we want the image to be displayed
               transformations(CircleCropTransformation())
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
      //return the view holder with layout inflater
        return MainViewHolder(
          LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false)
      )
    }
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        //bind data according to characterslist on the holder
       holder.bindData(characterList[position])
    }
    override fun getItemCount(): Int {
        //return the list of characters
        return characterList.size
    }
}
