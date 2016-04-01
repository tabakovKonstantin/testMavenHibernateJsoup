package table;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Константин on 30.03.2016.
 */
@Entity
@Table(name = "conference", schema = "public", catalog = "conference")
public class ConferenceEntity {
    private int id;
    private ContactEntity contactEntity;
    private FormEntity form;
    private String conferenceTitle;
    private Date dateBegin;
    private Date dateEnd;
    private String language;
    private String description;
    private Date dateFilingEnd;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "form_id", referencedColumnName = "id")
    public FormEntity getForm() {
        return form;
    }

    public void setForm(FormEntity form) {
        this.form = form;
    }

    @Id
    @Column(name = "id")
    @SequenceGenerator(allocationSize=1, initialValue=1, sequenceName="conference_seq", name="conference_seq")
    @GeneratedValue(generator="conference_seq", strategy=GenerationType.SEQUENCE)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "conference_title")
    public String getConferenceTitle() {
        return conferenceTitle;
    }

    public void setConferenceTitle(String conferenceTitle) {
        this.conferenceTitle = conferenceTitle;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "date_begin")
    public Date getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(Date dateBegin) {
        this.dateBegin = dateBegin;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "date_end")
    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_id", referencedColumnName = "id")
    public ContactEntity getContactEntity() {
        return contactEntity;
    }

    public void setContactEntity(ContactEntity contactEntity) {
        this.contactEntity = contactEntity;
    }

    @Basic
    @Column(name = "language")
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "date_filing_end")
    public Date getDateFilingEnd() {
        return dateFilingEnd;
    }

    public void setDateFilingEnd(Date dateFilingEnd) {
        this.dateFilingEnd = dateFilingEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConferenceEntity that = (ConferenceEntity) o;

        if (id != that.id) return false;
        if (conferenceTitle != null ? !conferenceTitle.equals(that.conferenceTitle) : that.conferenceTitle != null)
            return false;
        if (dateBegin != null ? !dateBegin.equals(that.dateBegin) : that.dateBegin != null) return false;
        if (dateEnd != null ? !dateEnd.equals(that.dateEnd) : that.dateEnd != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (conferenceTitle != null ? conferenceTitle.hashCode() : 0);
        result = 31 * result + (dateBegin != null ? dateBegin.hashCode() : 0);
        result = 31 * result + (dateEnd != null ? dateEnd.hashCode() : 0);
        return result;
    }

}
