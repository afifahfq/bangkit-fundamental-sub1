package com.example.githubuser

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ListUserAdapter(private val listUsers: ArrayList<User2>): RecyclerView.Adapter<ListUserAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_user, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, location, repository, company, followers, following, photo) = listUsers[position]
        Glide.with(holder.itemView.getContext())
            .load(photo)
            .circleCrop()
            .into(holder.imgPhoto);

        holder.tvName.text = name
        holder.tvDescription.text = description

        /* holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Kamu memilih " + listHero[holder.adapterPosition].name, Toast.LENGTH_SHORT).show()
        } */
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listUsers[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = listUsers.size

    class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        var tvDescription:TextView = itemView.findViewById(R.id.tv_item_description)
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: User2)
    }
}