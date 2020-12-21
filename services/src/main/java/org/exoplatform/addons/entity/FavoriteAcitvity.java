package org.exoplatform.addons.entity;

import javax.jcr.Property;
import java.util.Date;

public class FavoriteAcitvity {
    private String Title ;
    private String Link ;
    private String Publication_Date;

    public FavoriteAcitvity(String title, String link, String publication_Date) {
        this.Title = title;
        this.Link = link;
        this.Publication_Date = publication_Date;
    }

    public FavoriteAcitvity() { }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }

    public String getPublication_Date() {
        return Publication_Date;
    }

    public void setPublication_Date(String publication_Date) {
        Publication_Date = publication_Date;
    }
}
