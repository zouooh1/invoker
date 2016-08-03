package me.zouooh.invoker;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;

public class InvokerDecoration extends RecyclerView.ItemDecoration {

	private static final int[] ATTRS = { android.R.attr.listDivider };
	private Drawable divider;

	public InvokerDecoration(Context context) {
		TypedArray typedArray = context.obtainStyledAttributes(ATTRS);
		divider = typedArray.getDrawable(0);
		typedArray.recycle();
	}

	public InvokerDecoration(Drawable drawable) {
		divider = drawable;
	}

	@Override
	public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
			State state) {
		final int childCount = state.getItemCount() - 1;
		final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) view
				.getLayoutParams();
		if (params.getViewLayoutPosition() != childCount) {
			outRect.set(0, 0, 0, divider.getIntrinsicHeight());
		} else {
			outRect.set(0, 0, 0, 0);
		}
	}

	@Override
	public void onDraw(Canvas c, RecyclerView parent, State state) {
		final int childCount = parent.getChildCount();
		InvokerAdapter sectionAdapter = (InvokerAdapter) parent.getAdapter();
		int total = sectionAdapter.getItemCount();
		for (int i = 0; i < childCount; i++) {
			final View child = parent.getChildAt(i);
			final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
					.getLayoutParams();
			int t = params.getViewAdapterPosition();
			if (t < 0) {
				continue;
			}
			IndexPath indexPath = sectionAdapter.getIndexPaths().get(t);
			if (indexPath.isHeadCell()) {
				final int left = 0;
				final int right = child.getWidth();
				final int top = child.getBottom() + params.bottomMargin;
				final int bottom = top + divider.getIntrinsicHeight();
				divider.setBounds(left, top, right, bottom);
				divider.draw(c);
			} else if (indexPath.isFootCell()) {
				if (params.getViewAdapterPosition() + 1 != total) {
					final int left = 0;
					final int right = child.getWidth();
					final int top = child.getBottom() + params.bottomMargin;
					final int bottom = top + divider.getIntrinsicHeight();
					divider.setBounds(left, top, right, bottom);
					divider.draw(c);
				}
			} else {
				if (indexPath.getRow() != sectionAdapter
						.getRowCountInSection(indexPath.getSection()) - 1) {
					final int left = child.getPaddingLeft()
							+ extLeft(indexPath);
					final int right = child.getWidth()
							- child.getPaddingRight();
					final int top = child.getBottom() + params.bottomMargin;
					final int bottom = top + divider.getIntrinsicHeight();
					divider.setBounds(left, top, right, bottom);
					divider.draw(c);
				} else {
					final int left = 0;
					final int right = child.getWidth();
					final int top = child.getBottom() + params.bottomMargin;
					final int bottom = top + divider.getIntrinsicHeight();
					divider.setBounds(left, top, right, bottom);
					divider.draw(c);
				}
			}
		}
	}

	protected int extLeft(IndexPath indexPath) {
		return 0;
	}
}