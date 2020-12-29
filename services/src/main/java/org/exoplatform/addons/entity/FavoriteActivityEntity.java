package org.exoplatform.addons.entity;

import org.exoplatform.commons.api.persistence.ExoEntity;
import org.exoplatform.social.core.jpa.storage.entity.ActivityEntity;

import javax.persistence.*;
import java.util.Calendar;
@ExoEntity
@Entity
@Table(name = "ADDON_FAVORITE_ACTIVITY")
public class FavoriteActivityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;
    @Column(name = "Activity_Title")
    private String ActivityTitle;
    @ManyToOne
    @JoinColumn(name = "Target_Entity_Id")
    private ActivityEntity TargetActivity;
    @Column(name = "Favorite_Date")
    private Calendar favoriteDate;

    public FavoriteActivityEntity() {}

    public FavoriteActivityEntity(Long ID, String activityTitle, ActivityEntity targetActivity, Calendar favoriteDate) {
        this.ID = ID;
        this.ActivityTitle = activityTitle;
        this.TargetActivity = targetActivity;
        this.favoriteDate = favoriteDate;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getActivityTitle() {
        return ActivityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        ActivityTitle = activityTitle;
    }

    public ActivityEntity getTargetActivity() {
        return TargetActivity;
    }

    public void setTargetActivity(ActivityEntity targetActivity) {
        TargetActivity = targetActivity;
    }

    public Calendar getFavoriteDate() {
        return favoriteDate;
    }

    public void setFavoriteDate(Calendar favoriteDate) {
        this.favoriteDate = favoriteDate;
    }
}
