package xyz.shardion.badaccess.compat;

import java.util.UUID;
import java.util.WeakHashMap;

public class PlayerInventoryTracker {
    public static final WeakHashMap<Object, UUID> INVENTORIES = new WeakHashMap<>();
}
