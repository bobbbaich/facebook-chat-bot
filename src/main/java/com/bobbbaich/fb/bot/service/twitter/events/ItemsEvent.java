package com.bobbbaich.fb.bot.service.twitter.events;

import java.util.Set;

public interface ItemsEvent<T> {
    Set<T> getItems();
}
