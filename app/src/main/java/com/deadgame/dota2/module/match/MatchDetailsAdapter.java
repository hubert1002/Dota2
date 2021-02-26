package com.deadgame.dota2.module.match;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.deadgame.dota2.data.MatchInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by levent_j on 16-3-9.
 */
public class MatchDetailsAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<MatchInfo.PlayersDTO> matchPlayerList;


    public MatchDetailsAdapter(List<MatchInfo.PlayersDTO> players,Context mcontext){
        this.context = mcontext;
        this.matchPlayerList = players;
    }

    @Override
    public int getGroupCount() {
        return matchPlayerList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return matchPlayerList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return matchPlayerList.get(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }



}
