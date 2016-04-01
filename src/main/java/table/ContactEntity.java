package table;

import javax.persistence.*;

/**
 * Created by Константин on 01.04.2016.
 */
@Entity
@Table(name = "contact", schema = "public", catalog = "conference")
public class ContactEntity {
    private int id;
    private String counry;
    private String city;
    private String contact;
    private String email;

    @Id
    @Column(name = "id")
    @SequenceGenerator(allocationSize=1, initialValue=1, sequenceName="contact_seq", name="contact_seq")
    @GeneratedValue(generator="contact_seq", strategy=GenerationType.SEQUENCE)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "counry")
    public String getCounry() {
        return counry;
    }

    public void setCounry(String counry) {
        this.counry = counry;
    }

    @Basic
    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "contact")
    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactEntity that = (ContactEntity) o;

        if (id != that.id) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (contact != null ? !contact.equals(that.contact) : that.contact != null) return false;
        if (counry != null ? !counry.equals(that.counry) : that.counry != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (counry != null ? counry.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (contact != null ? contact.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
