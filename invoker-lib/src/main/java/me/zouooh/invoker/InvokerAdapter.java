package me.zouooh.invoker;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;

/**
 * 数据适配
 * @author zouooh
 *
 */
public abstract class InvokerAdapter extends RecyclerView.Adapter<ViewHolder>
		implements CellType, AdapterItemListener {

	private List<IndexPath> indexPaths = new LinkedList<>();

	private OnItemListener onRecyclerViewListener;

	public InvokerAdapter(OnItemListener onRecyclerViewListener) {
		setOnRecyclerViewListener(onRecyclerViewListener);
	}

	public  void  init(){
		initIndexPath();
	}

	public void clear() {
		getIndexPaths().clear();
		notifyDataSetChanged();
	}

	public List<IndexPath> getIndexPaths() {
		return indexPaths;
	}

	@Override
	public int getItemCount() {
		return getIndexPaths().size();
	}

	@Override
	public int getItemViewType(int position) {
		IndexPath indexPath = getIndexPaths().get(position);
		return indexPath.getType();
	}

	public OnItemListener getOnRecyclerViewListener() {
		return onRecyclerViewListener;
	}

	public abstract int getRowCountInSection(int sectionIndex);

	public int getSectionCount() {
		return 1;
	}

	protected abstract boolean hasFootViewInSection(int sectionIndex);

	protected abstract boolean hasHeadViewInSection(int sectionIndex);

	protected void initIndexPath() {
		int scount = getSectionCount();
		for (int s = 0; s < scount; s++) {
			if (hasHeadViewInSection(s)) {
				IndexPath indexPath = new IndexPath(s, 0, HEAD);
				initIndexPathHeadType(indexPath);
				getIndexPaths().add(indexPath);
			}
			int ris = getRowCountInSection(s);
			for (int i = 0; i < ris; i++) {
				IndexPath indexPath = new IndexPath(s, i, CELL);
				initIndexPathType(indexPath);
				getIndexPaths().add(indexPath);
			}

			if (hasFootViewInSection(s)) {
				IndexPath indexPath = new IndexPath(s, 0, FOOT);
				initIndexPathFootType(indexPath);
				getIndexPaths().add(indexPath);
			}
		}
	}

	protected void initIndexPathFootType(IndexPath indexPath) {
	}

	protected void initIndexPathHeadType(IndexPath indexPath) {
	}

	protected abstract void initIndexPathType(IndexPath indexPath);

	public void notifySectionChanged() {
		getIndexPaths().clear();
		initIndexPath();
		notifyDataSetChanged();
	}

	protected abstract void onBindCellViewHolder(ViewHolder viewHolder,
			IndexPath indexPath);

	protected abstract void onBindFootViewHolder(ViewHolder viewHolder, IndexPath indexPath);

	protected abstract void onBindHeadViewHolder(ViewHolder viewHolder, IndexPath indexPath);

	@Override
	public void onBindViewHolder(ViewHolder viewHolder, int postion) {
		IndexPath indexPath = getIndexPaths().get(postion);
		if (indexPath.isHeadCell()) {
			onBindHeadViewHolder(viewHolder, indexPath);
		} else if (indexPath.isFootCell()) {
			onBindFootViewHolder(viewHolder, indexPath);
		} else {
			onBindCellViewHolder(viewHolder, indexPath);
		}
	}

	protected abstract ViewHolder onCreateCellViewHolder(ViewGroup parent, int type);

	protected abstract ViewHolder onCreateFootViewHolder(ViewGroup parent, int type);

	protected abstract ViewHolder onCreateHeadViewHolder(ViewGroup parent, int type);

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int type) {
		ViewHolder viewHolder = null;
		if (IndexPath.isHeadCell(type)) {
			viewHolder = onCreateHeadViewHolder(parent, type);
		} else if (IndexPath.isFootCell(type)) {
			viewHolder = onCreateFootViewHolder(parent, type);
		} else {
			viewHolder = onCreateCellViewHolder(parent, type);
		}
		if (viewHolder == null) {
			throw new RuntimeException("!!!!!! type:" + type
					+ ".can not be null");
		}
		if (viewHolder instanceof InvokerCell) {
			InvokerCell simpleViewHolder = (InvokerCell) viewHolder;
			if (simpleViewHolder.isClickable()) {
				simpleViewHolder.setAdapterItemListener(this);
			}
		}
		return viewHolder;
	}

	@Override
	public void onItemClick(int position) {
		if (getOnRecyclerViewListener() != null) {
			IndexPath indexPath = getIndexPaths().get(position);
			getOnRecyclerViewListener().onItemClick(indexPath);
		}
	}
	
	@Override
	public boolean onItemLongClick(int position) {
		if (getOnRecyclerViewListener() != null) {
			IndexPath indexPath = getIndexPaths().get(position);
			return getOnRecyclerViewListener().onItemLongClick(indexPath);
		}
		return false;
	}

	public void setOnRecyclerViewListener(
			OnItemListener onRecyclerViewListener) {
		this.onRecyclerViewListener = onRecyclerViewListener;
	}
}
