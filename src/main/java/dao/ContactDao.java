package dao;

import table.ContactEntity;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Константин on 01.04.2016.
 */
public interface ContactDao {
        public void addContact(ContactEntity contact) throws SQLException;
        public void deleteContact(ContactEntity contact) throws SQLException;
        public ContactEntity getContact(int id) throws SQLException;
        public List<ContactEntity> getContacts() throws SQLException;
}
