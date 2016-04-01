package dao;

import table.ThemeEntity;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Константин on 01.04.2016.
 */
public interface ThemeDao {
        public void addTheme(ThemeEntity theme) throws SQLException;
        public void deleteTheme(ThemeEntity theme) throws SQLException;
        public ThemeEntity geTheme(int id) throws SQLException;
        public List<ThemeEntity> getThemes() throws SQLException;
}
