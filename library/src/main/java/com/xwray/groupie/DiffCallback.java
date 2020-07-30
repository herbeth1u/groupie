package com.xwray.groupie;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import java.util.Collection;
import java.util.List;

class DiffCallback extends DiffUtil.Callback {
    private final List<Item<?>> oldItems;
    private final List<Item<?>> newItems;

    DiffCallback(Collection<? extends Group> oldGroups, Collection<? extends Group> newGroups) {
        this.oldItems = GroupUtils.flattenItems(oldGroups);
        this.newItems = GroupUtils.flattenItems(newGroups);
    }

    @Override
    public int getOldListSize() {
        return oldItems.size();
    }

    @Override
    public int getNewListSize() {
        return newItems.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldItems.get(oldItemPosition).isSameAs(newItems.get(oldItemPosition));
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldItems.get(oldItemPosition).hasSameContentAs(newItems.get(oldItemPosition));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return oldItems.get(oldItemPosition).getChangePayload(newItems.get(oldItemPosition));
    }
}