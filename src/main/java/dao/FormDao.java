package dao;

import table.FormEntity;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Константин on 30.03.2016.
 */
public interface FormDao {
    public void addForm(FormEntity form) throws SQLException;
    public void deleteForm(FormEntity form) throws SQLException;
    public  FormEntity getForm(int id) throws SQLException;
    public List<FormEntity> getForms() throws SQLException;
}
