package com.wip.hockey.adapter;

import java.util.List;

/**
 * Created by djorda on 25/07/2017.
 */

public interface DataListener {
    void dataHasChanged(List response);
    void updateFinish();
}
