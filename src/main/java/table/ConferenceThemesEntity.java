package table;

import javax.persistence.*;

/**
 * Created by Константин on 01.04.2016.
 */
@Entity
@Table(name = "conference_themes", schema = "public", catalog = "conference")
public class ConferenceThemesEntity {
    private int id;
    private ThemeEntity themeEntity;
    private ConferenceEntity conferenceEntity;

    @Id
    @Column(name = "id")
    @SequenceGenerator(allocationSize=1, initialValue=1, sequenceName="conference_themes_seq", name="conference_themes_seq")
    @GeneratedValue(generator="conference_themes_seq", strategy=GenerationType.SEQUENCE)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theme_id", referencedColumnName = "id")
    public ThemeEntity getThemeEntity() {
        return themeEntity;
    }

    public void setThemeEntity(ThemeEntity themeEntity) {
        this.themeEntity = themeEntity;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conference_id", referencedColumnName = "id")
    public ConferenceEntity getConferenceEntity() {
        return conferenceEntity;
    }

    public void setConferenceEntity(ConferenceEntity conferenceEntity) {
        this.conferenceEntity = conferenceEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConferenceThemesEntity that = (ConferenceThemesEntity) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
