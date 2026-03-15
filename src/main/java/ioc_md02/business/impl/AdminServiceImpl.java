package ioc_md02.business.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import ioc_md02.business.IAdminSerrvice;
import ioc_md02.dao.impl.AdminDAOImpl;
import ioc_md02.model.Admin;
import ioc_md02.model.Invoice;
import ioc_md02.model.Statistic;

public class AdminServiceImpl implements IAdminSerrvice{

    private static AdminServiceImpl instance;

    private AdminServiceImpl() {}

    public static AdminServiceImpl getInstance() {
        if (instance == null) {
            instance = new AdminServiceImpl();
        }
        return instance;
    }

    @Override
    public void login(Scanner scanner) {
        Admin admin = new Admin();

        do {
            while (true) {
                System.out.print("Enter username: ");
                admin.setUsername(scanner.nextLine());

                if (admin.getUsername().isEmpty()) {
                    System.out.println("Tên đăng nhập không được để trống!");
                    continue;
                }
                break;
            }

            while (true) {
                System.out.print("Enter password: ");
                admin.setPassword(scanner.nextLine());

                if (admin.getPassword().isEmpty()) {
                    System.out.println("Mật khẩu không được để trống!");
                    continue;
                }
                break;
            }

            if(AdminDAOImpl.getInstance().login(admin)) {
                System.out.println("Đăng nhập thành công!");
                System.out.println("=========================================");
                break;
            } else {
                System.out.println("Tên đăng nhập hoặc mật khẩu không đúng!");
                System.out.println("=========================================");
            }

        } while (true);
    }

    @Override
    public void showStatistic(ResultSet rs) {
        double totalRevenue = 0;
        System.out.println();
        System.out.println("=========================== THỐNG KÊ DOANH THU ===========================");

        try {
            if (!rs.next()) {
                System.out.println("Không tìm thấy dữ liệu thống kê trong khoảng thời gian lựa chọn.");
                return;
            }

            System.out.println("Các sản phẩm đã bán được: ");
            System.out.println("+--------------------------------------------------+-----+---------------+");
            do {

                Statistic stat = new Statistic();
                stat.setProductName(rs.getString("product"));
                stat.setTotalQuantity(rs.getInt("total_quantity"));
                stat.setRevenue(rs.getDouble("revenue"));
                System.out.println(stat);
                System.out.println("+--------------------------------------------------+-----+---------------+");

                totalRevenue += stat.getRevenue();

            } while (rs.next());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Tổng doanh thu: " + String.format("%.2f", totalRevenue));
        System.out.println("==========================================================================");
    }

    @Override
    public void getStatisticByDay(Scanner scanner) {
        LocalDate date;
        while (true) {
            try {
                System.out.print("Nhập ngày muốn hiển thị thống kê (dd/mm/yyyy): ");
                date = LocalDate.parse(scanner.nextLine(), Invoice.formatter);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Dữ liệu nhập vào không hợp lệ!");
            }
        }

        ResultSet rs = AdminDAOImpl.getInstance().getStatistic(date, date.plusDays(1));
        showStatistic(rs);
    }

    @Override
    public void getStatisticByMonth(Scanner scanner) {
        YearMonth ym;

        while (true) {
            try {
                System.out.print("Nhập tháng muốn hiển thị thống kê (MM/yyyy): ");
                String input = scanner.nextLine();

                ym = YearMonth.parse(input, DateTimeFormatter.ofPattern("MM/yyyy"));

                break;
            } catch (Exception e) {
                System.out.println("Sai định dạng thông tin!");
            }
        }

        LocalDate start = ym.atDay(1);
        LocalDate end = start.plusMonths(1);

        ResultSet rs = AdminDAOImpl.getInstance().getStatistic(start, end);
        showStatistic(rs);
    }
    @Override
    public void getStatisticByYear(Scanner scanner) {
        int year;

        while (true) {
            try {
                System.out.print("Nhập năm muốn hiển thị thống kê (yyyy): ");
                year = Integer.parseInt(scanner.nextLine());

                if(year > 1000 && year < 9999) {
                    break;
                }
                System.out.println("Năm tra cứu phải lớn hơn 1000 và nhỏ hơn 9999.");

            } catch (IllegalArgumentException e) {
                System.out.println("Sai định dạng thông tin!");
            }
        }

        LocalDate start = LocalDate.of(year, 1, 1);
        LocalDate end = start.plusYears(1);

        ResultSet rs = AdminDAOImpl.getInstance().getStatistic(start, end);
        showStatistic(rs);

    }

}
