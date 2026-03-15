package ioc_md02.dao;

import java.sql.ResultSet;
import java.time.LocalDate;

import ioc_md02.model.Admin;

public interface IAdminDAO {
    boolean login(Admin admin);
    ResultSet getStatistic(LocalDate dateStart, LocalDate dateEnd);
}
