package table;

import javax.persistence.*;

/**
 * Created by Константин on 30.03.2016.
 */
@Entity
@Table(name = "form", schema = "public", catalog = "conference")
public class FormEntity {
    private String nameForm;
    private int id;

    @Basic
    @Column(name = "name_form")
    public String getNameForm() {
        return nameForm;
    }

    public void setNameForm(String nameForm) {
        this.nameForm = nameForm;
    }

    @Id
    @Column(name = "id")
    @SequenceGenerator(allocationSize=1, initialValue=1, sequenceName="form_seq", name="form_seq")
    @GeneratedValue(generator="form_seq", strategy=GenerationType.SEQUENCE)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FormEntity that = (FormEntity) o;

        if (id != that.id) return false;
        if (nameForm != null ? !nameForm.equals(that.nameForm) : that.nameForm != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nameForm != null ? nameForm.hashCode() : 0;
        result = 31 * result + id;
        return result;
    }
}
