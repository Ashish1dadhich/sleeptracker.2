package com.example.android.trackmysleepquality.sleeptracker


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.database.SleepNight
import com.example.android.trackmysleepquality.databinding.ListItemSleepNightBinding


class SleepNightAdapter(val clickListener:SleepNightListener):ListAdapter<SleepNight,
        SleepNightAdapter.ViewHolder>(SleepNightDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder.from(parent)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!,clickListener)

    }



    class ViewHolder private constructor(val binding: ListItemSleepNightBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SleepNight, clickListener: SleepNightListener) {
            binding.sleep = item
            binding.clickListener=clickListener
            binding.executePendingBindings()

        }
//        fun bind(item: SleepNight) {
//            val res = itemView.context.resources
//            binding.sleepLength.text = convertDurationToFormatted(item.startTimeMilli, item.endTimeMilli, res).toString()
//            binding.qualityString.text = convertNumericQualityToString(item.sleepQuality, res)
//            binding.QualityImage.setImageResource(when (item.sleepQuality) {
//                    0 -> R.drawable.ic_sleep_0
//                    1 -> R.drawable.ic_sleep_1
//                    2 -> R.drawable.ic_sleep_2
//                    3 -> R.drawable.ic_sleep_3
//                    4 -> R.drawable.ic_sleep_4
//                    5 -> R.drawable.ic_sleep_5
//                    else -> R.drawable.ic_launcher_sleep_tracker_foreground
//                } we made bindingUtils.kt file to reduce code length here
//            )
//        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding =ListItemSleepNightBinding.inflate(layoutInflater,parent,false)
                return ViewHolder(binding)
            }


        }
    }
}
class SleepNightDiffCallback:DiffUtil.ItemCallback<SleepNight>(){
    override fun areItemsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
        return oldItem.nightId == newItem.nightId
    }

    override fun areContentsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
        return oldItem == newItem
    }

}

class SleepNightListener(val clickListener: (sleepId:Long) ->Unit){
    fun onClick(night: SleepNight)=clickListener(night.nightId)
}