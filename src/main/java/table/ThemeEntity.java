package table;

import javax.persistence.*;

/**
 * Created by Константин on 01.04.2016.
 */
@Entity
@Table(name = "theme", schema = "public", catalog = "conference")
public class ThemeEntity {
    private int id;
    private String nameTheme;

    @Id
    @Column(name = "id")
    @SequenceGenerator(allocationSize=1, initialValue=1, sequenceName="theme_seq", name="theme_seq")
    @GeneratedValue(generator="theme_seq", strategy=GenerationType.SEQUENCE)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name_theme")
    public String getNameTheme() {
        return nameTheme;
    }

    public void setNameTheme(String nameTheme) {
        this.nameTheme = nameTheme;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ThemeEntity that = (ThemeEntity) o;

        if (id != that.id) return false;
        if (nameTheme != null ? !nameTheme.equals(that.nameTheme) : that.nameTheme != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nameTheme != null ? nameTheme.hashCode() : 0);
        return result;
    }
}
