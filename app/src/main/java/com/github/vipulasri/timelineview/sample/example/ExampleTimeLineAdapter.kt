package com.github.vipulasri.timelineview.sample.example

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.github.vipulasri.timelineview.TimelineView
import com.github.vipulasri.timelineview.sample.R
import com.github.vipulasri.timelineview.sample.model.OrderStatus
import com.github.vipulasri.timelineview.sample.model.TimeLineModel
import com.github.vipulasri.timelineview.sample.utils.DateTimeUtils
import com.github.vipulasri.timelineview.sample.utils.VectorDrawableUtils
import kotlinx.android.synthetic.main.item_timeline.view.*

/**
 * Created by Vipul Asri on 13-12-2018.
 */

class ExampleTimeLineAdapter(private val mFeedList: List<TimeLineModel>) : RecyclerView.Adapter<ExampleTimeLineAdapter.TimeLineViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return TimelineView.getTimeLineViewType(position, itemCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeLineViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TimeLineViewHolder(layoutInflater.inflate(R.layout.item_timeline, parent, false), viewType)
    }

    override fun onBindViewHolder(holder: TimeLineViewHolder, position: Int) {

        val timeLineModel = mFeedList[position]

        when (timeLineModel.status) {
            OrderStatus.INACTIVE -> {
                setMarker(holder, R.drawable.ic_marker_inactive, R.color.material_grey_500)
            }
            OrderStatus.ACTIVE -> {
                setMarker(holder, R.drawable.ic_marker_active, R.color.material_grey_500)
            }
            else -> {
                setMarker(holder, R.drawable.ic_marker, R.color.material_grey_500)
            }
        }

        if (timeLineModel.date.isNotEmpty()) {
            holder.date.visibility = View.VISIBLE
            holder.date.text = DateTimeUtils.parseDateTime(timeLineModel.date, "yyyy-MM-dd HH:mm", "hh:mm a, dd-MMM-yyyy")
        } else
            holder.date.visibility = View.GONE

        holder.message.text = timeLineModel.message
    }

    private fun setMarker(holder: TimeLineViewHolder, drawableResId: Int, colorFilter: Int) {
        holder.timeline.marker = VectorDrawableUtils.getDrawable(holder.itemView.context, drawableResId, ContextCompat.getColor(holder.itemView.context, colorFilter))
    }

    override fun getItemCount() = mFeedList.size

    inner class TimeLineViewHolder(itemView: View, viewType: Int) : RecyclerView.ViewHolder(itemView) {

        val date = itemView.text_timeline_date!!
        val message = itemView.text_timeline_title!!
        val timeline = itemView.timeline!!

        init {
            timeline.initLine(viewType)
        }
    }

}
