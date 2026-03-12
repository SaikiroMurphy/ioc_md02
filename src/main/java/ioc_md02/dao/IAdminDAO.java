package ioc_md02.dao;

import java.sql.ResultSet;

import ioc_md02.model.Admin;

public interface IAdminDAO {
    boolean login(Admin admin);
    ResultSet getStatisticByDay(int day,int month,int year);
    ResultSet getStatisticByMonth(int month, int year);
    ResultSet getStatisticByYear(int year);
}
