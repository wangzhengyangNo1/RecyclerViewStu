package com.wzhy.recyclerviewstu.itemtouch;

import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.LinearLayout;

import com.wzhy.recyclerviewstu.base.sample.ItemEntity;

import java.util.Collections;
import java.util.List;

/**
 * Android 提供了ItemTouchHelper类，使得RecyclerView能够轻易地实现滑动和拖拽。
 * 此处我们实现上下拖拽和侧滑删除。
 *
 * @see {@link ItemTouchHelper}
 */
public class SimpleItemTouchCallback extends ItemTouchHelper.Callback {

    private ItemTouchTestAdapter mAdapter;

    public SimpleItemTouchCallback(ItemTouchTestAdapter mAdapter) {
        this.mAdapter = mAdapter;
    }

    /**
     * 设置支持的拖拽和滑动的方向。
     * 应该返回一个组合的标志（flag），这个标志定义了在每种状态（idle，swiping，dragging）下的移动方向。
     * <p>
     * 你可以使用@link #makeMovementFlags(int, int)} 或 {@link #makeFlag(int, int)}设置，而非手动组合这个标志。
     * <p>
     * 这个标志由3组8位（二进制）组成，第一个8位表示IDLE状态，第二个8位表示SWIPE状态，而第三个8位表示DRAG状态。
     * 每一个8位段可以通过{@link ItemTouchHelper}中定义的简单的方向标志来构造。
     *
     * <p>
     * 例如，你想让它允许左右滑动，但只允许通过右滑开始滑动。你可以返回：
     * <pre>
     *      makeFlag(ACTION_STATE_IDLE, RIGHT) | makeFlag(ACTION_STATE_SWIPE, LEFT | RIGHT);
     * </pre>
     * 这意味着，当静止状态（IDLE）下允许右滑，滑动状态（SWIPE）下允许左滑和右滑。
     *
     * @see #makeMovementFlags(int, int)
     * @see #makeFlag(int, int)
     */
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        int dragFlags = 0;
        int swipeFlags = 0;

        if (layoutManager instanceof GridLayoutManager || layoutManager instanceof StaggeredGridLayoutManager) {
            dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT; //上下拖拽}
            //swipeFlags = 0;
            swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;//左->右和右->左
        } else {
            if (((LinearLayoutManager) layoutManager).getOrientation() == OrientationHelper.VERTICAL) {
                dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN; //上下拖拽}
                swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;//左->右和右->左
            } else {
                dragFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT; //上下拖拽}
                swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;//左->右和右->左
            }
        }
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    /**
     * 当 ItemTouchHelper 想要把被拖拽的item从旧的位置移动到新的位置时调用。
     * <p>
     * 如果此方法返回 true，ItemTouchHelper 认为 viewHolder 已经被移动到了目标ViewHolder 的 适配器（adapter）位置。
     * ({@link RecyclerView.ViewHolder#getAdapterPosition() })
     * <p>
     * 如果不支持拖放，则永远不会调用此方法。
     *
     * @param recyclerView ItemTouchHelper 附着的 RecyclerView to which.
     * @param viewHolder   被拖拽的 ViewHolder
     * @param target       目标 ViewHolder，当前活动的item拖拽到它上方。
     * @return True 如果 {@code viewHolder} 已经被移动到了 {@code target} 的适配器中的位置，返回true。
     * @see #onMoved(RecyclerView, RecyclerView.ViewHolder, int, RecyclerView.ViewHolder, int, int, int)
     */
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        int from = viewHolder.getAdapterPosition();
        int to = target.getAdapterPosition();
        final List<ItemEntity> dataList = mAdapter.getDataList();
        Collections.swap(dataList, from, to);//交换位置
//        //移动位置
//        if (from < to) {
//            for (int i = from; i < to; i++) {
//                Collections.swap(dataList, i, i+1);
//            }
//        } else {
//            for (int i = from; i > to; i--) {
//                Collections.swap(dataList, i, i - 1);
//            }
//        }

        mAdapter.notifyItemMoved(from, to);
        return true;
    }

    /**
     * 当 ViewHolder 被滑动时调用。
     * <p>
     * 如你在 {@link #getMovementFlags(RecyclerView, RecyclerView.ViewHolder)} 方法中返回
     * 相对方向({@link ItemTouchHelper#START} 和 {@link ItemTouchHelper#END})，此方法也会
     * 使用相对方向。否则此方法会使用绝对方向。
     * <p>
     * 如果不支持滑动，这个方法将永远不会被调用。
     * <p>
     * ItemTouchHelper 会持有View的引用直到它从RecyclerView分离。一旦它被分离了，ItemTouchHelper就会
     * 调用 {@link #clearView(RecyclerView, RecyclerView.ViewHolder)}。
     *
     * @param viewHolder 被用户滑动的 ViewHolder。
     * @param direction  ViewHolder 被滑动的方向。
     *                   即上下左右其中一个方向：
     *                   {@link ItemTouchHelper#UP}，
     *                   {@link ItemTouchHelper#DOWN}，
     *                   {@link ItemTouchHelper#LEFT，
     *                   {@link ItemTouchHelper#RIGHT}。
     *                   -------------------------------
     *                   如果你的{@link #getMovementFlags(RecyclerView, RecyclerView.ViewHolder)}
     *                   方法返回相对标志 代替 {@link ItemTouchHelper#LEFT} / {@link ItemTouchHelper#RIGHT}，
     *                   `direction` 也会是相对的。 ({@link ItemTouchHelper#START} 或 {@link ItemTouchHelper#END})。
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        int pos = viewHolder.getAdapterPosition();
        mAdapter.removeItem(pos);//删除项
    }


    /**
     * 状态变化时回调。
     * <p/>
     * 当ViewHolder 滑动和拖放 状态变化时调用。
     * <p>
     * 重写此方法需要调用super的该方法。
     *
     * @param viewHolder  滑动或拖放的ViewHolder
     * @param actionState 三种状态中的一个：
     *                    静止，{@link ItemTouchHelper#ACTION_STATE_IDLE}，
     *                    滑动，{@link ItemTouchHelper#ACTION_STATE_SWIPE}，
     *                    拖放，{@link ItemTouchHelper#ACTION_STATE_DRAG}。
     * @see #clearView(RecyclerView, RecyclerView.ViewHolder)
     */
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            viewHolder.itemView.setBackgroundColor(Color.LTGRAY);//设置拖拽和侧滑时的背景色
        }
        super.onSelectedChanged(viewHolder, actionState);
    }

    /**
     * 用户交互结束时回调。此方法可以做一些状态的清空，比如拖拽结束后还原背景色。
     * <p>
     * 这是一个很好的地方去清除所有对 View 在
     * {@link #onSelectedChanged(RecyclerView.ViewHolder, int)}，
     * {@link #onChildDraw(Canvas, RecyclerView, RecyclerView.ViewHolder, float, float, int, boolean)} 或
     * {@link #onChildDrawOver(Canvas, RecyclerView, RecyclerView.ViewHolder, float, float, int, boolean)}
     * 方法中的改变。
     *
     * @param recyclerView ItemTouchHelper 控制的 RecyclerView
     * @param viewHolder   与用户交互的 ViewHolder
     */
    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        viewHolder.itemView.setBackgroundColor(Color.TRANSPARENT);//背景色还原
        super.clearView(recyclerView, viewHolder);
    }

    /**
     * 是否支持长按拖拽
     */
    @Override
    public boolean isLongPressDragEnabled() {
        return false;
    }

    /**
     * 是否支持滑动
     */
    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }


}
