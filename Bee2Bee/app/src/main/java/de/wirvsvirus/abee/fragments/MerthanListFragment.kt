package de.wirvsvirus.abee.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import de.wirvsvirus.abee.R
import de.wirvsvirus.abee.data.ListPosting
import de.wirvsvirus.abee.databinding.LayoutPostingItemBinding
import kotlinx.android.synthetic.main.fragment_bieter_list.*
import kotlinx.android.synthetic.main.layout_posting_item.view.*

class MerthanListFragment: Fragment(R.layout.fragment_bieter_list) {

    var index=0
    fun lp(name:String,type:String,stadtPlz:String,demandTrue:Boolean,personAmount:Int,skillAmount:Int)
            =ListPosting(index,name,stadtPlz,type,demandTrue,"$personAmount Arbeiter","$skillAmount Skills")

    private val myAdapter: MyPostingAdapter by lazy { MyPostingAdapter() }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(postingRecyclerView){
            layoutManager=LinearLayoutManager(context)
            adapter=myAdapter
            myAdapter.submitList(list)
        }

        searchView.setOnQueryTextListener(object:androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val newList=originalList.filter { it.name.toUpperCase().contains(newText.toString().toUpperCase()) }
                myAdapter.submitList(newList)
                return true
            }

        })

    }

    val originalList=listOf(
        lp("Mcdonald's Köpenick","Angebot","12445 Berlin",true,6,8),
        lp("Lidl","Bedarf","10830 Berlin",false,4,8),
        lp("Apotheke Yorckstr","Bedarf","10820 Berlin",true,2,12),
        lp("Werner Reinigungen","Angebot","10555 Berlin",true,14,4),
        lp("Rentnerheim MaxLife","Angebot","12545 Berlin",true,14,5)
    )

    val list= originalList.toMutableList()

}



class MyPostingAdapter:ListAdapter<ListPosting,MyPostingAdapter.ViewHolder>(callback){

    inner class ViewHolder(private val binding:LayoutPostingItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item:ListPosting){
            binding.posting=item
            with(itemView.star){
                setOnClickListener {
                    setImageResource(R.drawable.star_filled)
                }
            }

            binding.executePendingBindings()
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        ViewHolder(LayoutPostingItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position))

}
private val callback=object : DiffUtil.ItemCallback<ListPosting>(){
    override fun areItemsTheSame(oldItem: ListPosting, newItem: ListPosting)=oldItem.id==newItem.id

    override fun areContentsTheSame(oldItem: ListPosting, newItem: ListPosting)=
        oldItem.id==newItem.id&&oldItem.name==newItem.name

}