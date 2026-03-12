package ioc_md02.business;

import java.sql.ResultSet;
import java.util.Scanner;

public interface IAdminSerrvice {
    void login(Scanner scanner);
    void showStatistic(ResultSet rs);
    void getStatisticByDay(Scanner scanner);
    void getStatisticByMonth(Scanner scanner);
    void getStatisticByYear(Scanner scanner);
}
