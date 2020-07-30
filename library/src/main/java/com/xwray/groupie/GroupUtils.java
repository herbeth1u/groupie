package com.xwray.groupie;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class GroupUtils {
    @NonNull
    static Item getItem(Collection<? extends Group> groups, int position) {
        int previousPosition = 0;

        for (Group group : groups) {
            int size = group.getItemCount();
            if (size + previousPosition > position) {
                return group.getItem(position - previousPosition);
            }
            previousPosition += size;
        }

        throw new IndexOutOfBoundsException("Wanted item at " + position + " but there are only "
                + previousPosition + " items");
    }

    static int getItemCount(@NonNull Collection<? extends Group> groups) {
        int size = 0;
        for (Group group : groups) {
            size += group.getItemCount();
        }
        return size;
    }

    static List<Item<?>> flattenItems(Collection<? extends Group> groups) {
        List<Item<?>> res = new ArrayList<>();

        for (Group group : groups) {
            for (int i = 0; i < group.getItemCount(); i++) {
                res.add(group.getItem(i));
            }
        }

        return res;
    }
}