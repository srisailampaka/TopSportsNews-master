package com.topsportsnews.app.ServiceConnections;



import com.topsportsnews.app.models.SportsItem;

import java.util.List;

/**
 * Created by Srisailam on 1/14/16.
 */
public interface ResultsCallback {

    public void onSuccess(List<SportsItem> sportsItemList);
    public void onFailure(String errorMessage);
}
