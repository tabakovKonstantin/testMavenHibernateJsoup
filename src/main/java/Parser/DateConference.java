package Parser;

import java.util.Date;

/**
 * Created by Константин on 31.03.2016.
 */
public class DateConference {
    private Date dateBegin;
    private Date  dateEnd;
    private Date  dateFilingEnd;

    public DateConference(Date dateBegin, Date dateEnd, Date dateFilingEnd) {
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.dateFilingEnd = dateFilingEnd;
    }

    public Date getDateBegin() {
        return dateBegin;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public Date getDateFilingEnd() {
        return dateFilingEnd;
    }
}
