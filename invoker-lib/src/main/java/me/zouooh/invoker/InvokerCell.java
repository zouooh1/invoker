package me.zouooh.invoker;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class InvokerCell extends ViewHolder implements
		View.OnClickListener, View.OnLongClickListener {
	
	public static <T extends ViewHolder> T newViewHolder(Class<T> clazz, ViewGroup viewGroup,int resId){
		View view = LayoutInflater.from(viewGroup.getContext()).inflate(
				resId, viewGroup, false);
		try {
			return clazz.getDeclaredConstructor(View.class).newInstance(view);
		} catch (Exception e) {
		
		}
		return null;
	}
	
	
	public static ViewHolder viewHolder(ViewGroup viewGroup,int resId){
		View view = LayoutInflater.from(viewGroup.getContext()).inflate(resId, viewGroup, false);
		ViewHolder viewHolder = new ViewHolder(view) {
		};
		return viewHolder;
	}


	public static <T extends ViewHolder> T viewHolder(ViewHolder viewHolder){
		return (T)viewHolder;
	}
	
	private AdapterItemListener adapterItemListener;
	private View itemView;
	private boolean clickable = true;

	public InvokerCell(View itemView) {
		super(itemView);
		this.itemView = itemView;
	}

	@Override
	public void onClick(View v) {
		if (v == itemView) {
			if (null != getAdapterItemListener()) {
				getAdapterItemListener().onItemClick(getAdapterPosition());
			}
		}
	}

	@Override
	public boolean onLongClick(View v) {
		if (v == itemView) {
			if (null != getAdapterItemListener()) {
				return getAdapterItemListener().onItemLongClick(
						getAdapterPosition());
			}
		}

		return false;
	}

	public View getItemView() {
		return itemView;
	}


	public AdapterItemListener getAdapterItemListener() {
		return adapterItemListener;
	}

	public void setAdapterItemListener(AdapterItemListener adapterItemListener) {
		this.adapterItemListener = adapterItemListener;
		itemView.setOnClickListener(this);
		itemView.setOnLongClickListener(this);
	}

	public boolean isClickable() {
		return clickable;
	}

	public void setClickable(boolean clickable) {
		this.clickable = clickable;
	}

}
