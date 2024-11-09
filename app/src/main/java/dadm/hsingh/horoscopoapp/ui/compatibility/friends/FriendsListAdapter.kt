package dadm.hsingh.horoscopoapp.ui.compatibility.friends

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dadm.hsingh.horoscopoapp.databinding.FriendItemBinding
import dadm.hsingh.horoscopoapp.domain.model.Friend
import java.time.format.DateTimeFormatter

class FriendsListAdapter(val onEditClick: (Friend) -> Unit = {}, val onDeleteClick: (Friend) -> Unit = {}) : ListAdapter<Friend, FriendsListAdapter.ViewHolder>(FriendDiff){

    class ViewHolder(val onEditClick: (Friend) -> Unit, val onDeleteClick: (Friend) -> Unit, private val binding: FriendItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(friend: Friend){
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            binding.nameItem.text = friend.name + " (" +friend.zodiacSign + ")"
            binding.dateBirthItem.text = friend.dateBirth.format(formatter).toString()
            binding.placeBirthItem.text = friend.placeBirth
            binding.editButton.setOnClickListener { onEditClick(friend) }
            binding.deleteButton.setOnClickListener { onDeleteClick(friend) }
            if(friend.imageUri == null){
                binding.userPhoto.setImageResource(friend.defaultImage)
            }else{
                val uri = Uri.parse(friend.imageUri)
                binding.userPhoto.setImageURI(uri)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(onEditClick, onDeleteClick, FriendItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

object FriendDiff : DiffUtil.ItemCallback<Friend>(){
    override fun areItemsTheSame(oldItem: Friend, newItem: Friend): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Friend, newItem: Friend): Boolean {
        return oldItem == newItem
    }

}
