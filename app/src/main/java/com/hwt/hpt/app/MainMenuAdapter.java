package com.hwt.hpt.app;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ArrayAdapter;

/**
 * Created by oVANILLAz on 6/1/14 AD.
 */
public class MainMenuAdapter extends ArrayAdapter<MainMenu> {

    private Context context;
    private int itemLayoutId;
    private com.hwt.hpt.app.MainMenu mainMenus[] = null;

    public MainMenuAdapter(Context context, int itemLayoutId, com.hwt.hpt.app.MainMenu[] mainMenu) {
        super(context, itemLayoutId, mainMenu);

        this.context = context;
        this.itemLayoutId = itemLayoutId;
        this.mainMenus = mainMenu;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View item = inflater.inflate(itemLayoutId, parent, false);

        ImageView imgMenu = (ImageView) item.findViewById(R.id.menu_image);

        TextView txtMenu = (TextView) item.findViewById(R.id.menu_name);

        com.hwt.hpt.app.MainMenu mainMenu = mainMenus[position];
        imgMenu.setImageResource(mainMenu.getMenuImageId());
        txtMenu.setText(mainMenu.getMenu_name());

        return item;


    }
}