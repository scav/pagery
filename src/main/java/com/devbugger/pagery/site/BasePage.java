package com.devbugger.pagery.site;

import com.devbugger.pagery.transform.fontmatter.FontMatterMeta;

import java.util.ArrayList;
import java.util.List;

public class BasePage extends Page {

    List<MenuItem> menuItems = new ArrayList<>();

    public BasePage() {
    }

    public BasePage(FontMatterMeta fontMatterMeta) {
        super(fontMatterMeta);
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }
}
