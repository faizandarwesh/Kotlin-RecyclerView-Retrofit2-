package com.bit14.kotlinrecyclerviewandretrofit2.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bit14.kotlinrecyclerviewandretrofit2.R
import com.bit14.kotlinrecyclerviewandretrofit2.models.Post

class PostAdapter:RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    private var dataList:List<Post> = ArrayList()

    fun submitList(_dataList:List<Post>?){

        val oldList = dataList
        val diffResult:DiffUtil.DiffResult = DiffUtil.calculateDiff(
            PostsDiffCallback(
                oldList,
                _dataList!!
            )
        )
        this.dataList = _dataList
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
    val layoutInflater = LayoutInflater.from(parent.context)
        .inflate(R.layout.post_list_layout,parent,false)
        return PostViewHolder(layoutInflater)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(position)
    }

   class PostsDiffCallback(private var oldPostList:List<Post>,private var newPostList:List<Post>):
       DiffUtil.Callback() {

       override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
       return this.oldPostList[oldItemPosition].id == newPostList[newItemPosition].id
       }

       override fun getOldListSize(): Int {
          return this.oldPostList.size
       }

       override fun getNewListSize(): Int {
           return this.newPostList.size
       }

       override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldPostList[oldItemPosition] == newPostList[newItemPosition]
       }
   }

   inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val id:TextView = itemView.findViewById(R.id.user_id)
        private val title:TextView = itemView.findViewById(R.id.title)
        private val body:TextView = itemView.findViewById(R.id.body)

        @SuppressLint("SetTextI18n")
        fun bind(position:Int){
           val list = dataList[position]
           id.text = "Id: ${list.id}"
           title.text = "Title : ${list.title}"
           body.text = "Body : ${list.body}"
        }
    }
}