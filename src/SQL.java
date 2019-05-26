import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQL {
    private String hostname;
    private String login;
    private String pass;


    public SQL(String hostname, String login, String pass) {
        this.hostname = hostname;
        this.login = login;
        this.pass = pass;
    }


    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public boolean isPreferenceEmpty() {
        if (hostname == null || login == null || pass == null) {
            return true;
        } else {
            return false;
        }
    }

    public void getEmployee(int id) {
        if (!isPreferenceEmpty()) {
            java.sql.Connection conn = null;
            try {
                conn = Connect.getConnection(getHostname(), getLogin(), getPass());
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM EMP WHERE EMPNO = ?");
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                int col = rs.getMetaData().getColumnCount();
                while (rs.next() == true) {
                    for (int i = 1; i < col; i++) {
                        System.out.print(rs.getString(i) + "\t" + "|" + "\t");
                    }
                    System.out.println();
                }
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public void getAll () {
        if (!isPreferenceEmpty()) {
            try (java.sql.Connection conn = Connect.getConnection(getHostname(), getLogin(), getPass())) {
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM emp");
                int col = rs.getMetaData().getColumnCount();
                while (rs.next() == true) {
                    for (int i = 1; i < col; i++) {
                        System.out.print(rs.getString(i) + "\t" + "|" + "\t");
                    }
                    System.out.println();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void getEmpInfo(int id) throws SQLNoDataFoundException{
        if (!isPreferenceEmpty()) {
            java.sql.Connection conn = null;
            try {
                conn = Connect.getConnection(getHostname(), getLogin(), getPass());
                PreparedStatement ps = conn.prepareStatement
                        ("SELECT * FROM EMP e, DEPT d, SALEGRADE s "
                                + "WHERE e.EMPNO = ? "
                                + "AND e.DEPTNO = d.DEPTNO "
                                + "and e.SAL BETWEEN s.LOSAL and s.HISAL");
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (!rs.isBeforeFirst()) {
                    conn.close();
                    throw new SQLNoDataFoundException();
                }
                else {
                    int col = rs.getMetaData().getColumnCount();
                    while(rs.next()) {
                        for (int i = 1; i < col; i++) {
                            System.out.print(rs.getString(i) + "\t" + "|" + "\t");
                        }
                        System.out.println();
                    }
                    conn.close();
                }
            } catch (ClassNotFoundException | SQLException e) {

                e.printStackTrace();
                }
        }
    }

    public void deleteEmp(int id) {
        if (!isPreferenceEmpty()) {
            java.sql.Connection conn = null;
            try {
                conn = Connect.getConnection(getHostname(), getLogin(), getPass());
                PreparedStatement ps = conn.prepareStatement
                        ("DELETE FROM EMP WHERE EMPNO = ?");
                ps.setInt(1, id);
                ps.execute();

            } catch (ClassNotFoundException | SQLException e) {

                e.printStackTrace();
            }
        }
    }

    public void addEmp(int id, String name, String pos, int managerid, int sal, int comm, int dep) {
        if (!isPreferenceEmpty()) {
            java.sql.Connection conn = null;
            try {
                conn = Connect.getConnection(getHostname(), getLogin(), getPass());
                Statement m = conn.createStatement();
                ResultSet mrs = m.executeQuery("Select empno from emp where job = 'SALESMAN'");
                List<Integer> mgr = new ArrayList<>();
                while(mrs.next() == true) {
                    mgr.add(mrs.getInt(1));
                }
                Statement d = conn.createStatement();
                ResultSet drs = m.executeQuery("Select deptno from dept");
                List<Integer> departments = new ArrayList<>();
                while(drs.next() == true) {
                    departments.add(drs.getInt(1));
                }
                PreparedStatement ps = conn.prepareStatement
                        ("INSERT INTO EMP VALUES (? , ? , ? , ? , ? , ? , ? , ? )");
                ps.setInt(1, id);
                ps.setString(2, name);
                ps.setString(3, pos);
                ps.setInt(4, managerid);
                ps.setDate(5, new Date(System.currentTimeMillis()));
                ps.setInt(6, sal);
                ps.setInt(7, comm);
                ps.setInt(8, dep);
                ps.execute();

            } catch (ClassNotFoundException | SQLException e) {

                e.printStackTrace();
            }
        }
    }

}