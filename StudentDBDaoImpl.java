/*******************************************************************************
 *  システム名 : 学生情報管理
 *  著作権    : Copyright (C)　2002-2008　Realsys Co. Ltd. 　All Rights Reserved.
 *  会社名    : リアルシス株式会社
 *  ****************************************************************************
 *  変更履歴
 *  2008/03/20  作成　
 */
package jp.co.realsys.dao.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jp.co.realsys.dao.DBManager;
import jp.co.realsys.dao.StudentDao;
import jp.co.realsys.error.TaskException;
import jp.co.realsys.model.StudentModel;

/**
 * 学生情報データベースのインタフェースの実装クラス
 * 
 * @author Realsys
 */
public class StudentDBDaoImpl implements StudentDao {

    /**
     * 学生情報を登録する
     * 
     * @param student 学生情報モデル
     * @return レコード数
     */
    public int doRegisterStduent(StudentModel student) throws TaskException {
        try {
            DBManager dbManager = DBManager.getInstance();
            Connection conn = dbManager.getConnection();
            Statement st = conn.createStatement();

            String sql = "INSERT INTO STUDENT(ID,NAME,BIRTHDATE,ADDRESS) VALUES(" + "'"
                    + student.getId() + "'" + "," + "'" + student.getName() + "'" + "," + "'"
                    + student.getBirthDate() + "'" + "," + "'" + student.getAddress() + "'" + ")";

            return st.executeUpdate(sql);

        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 学生情報を更新する
     * 
     * @param student 学生情報モデル
     * @return レコード数
     */
    public int doUpdateStduent(StudentModel student) throws TaskException {
        try {
            DBManager dbManager = DBManager.getInstance();
            Connection conn = dbManager.getConnection();
            Statement st = conn.createStatement();

            String sql = "UPDATE STUDENT SET NAME =" + "'" + student.getName() + "'" + ","
                    + "BIRTHDATE =" + "'" + student.getBirthDate() + "'" + "," + "ADDRESS =" + "'"
                    + student.getAddress() + "'" + "WHERE ID =" + "'" + student.getId() + "'";

            return st.executeUpdate(sql);

        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 学生情報を削除する
     * 
     * @param studentId 学生ID
     * @return レコード数
     */
    public int doDeleteStduent(String studentId) throws TaskException {
        try {

            DBManager dbManager = DBManager.getInstance();
            Connection conn = dbManager.getConnection();
            Statement st = conn.createStatement();

            String sql = "DELETE FROM STUDENT WHERE ID=" + "'" + studentId + "'";
            return st.executeUpdate(sql);

        } catch (Exception e) {
            return 0;
        }

    }

    /**
     * 学生情報を検索する
     * 
     * @param name 名前
     * @return 学生情報
     */
    public List<StudentModel> doQueryStduentList(String name) throws TaskException {
        List<StudentModel> studentList = new ArrayList<StudentModel>();

        try {
            DBManager dbManager = DBManager.getInstance();
            Connection conn = dbManager.getConnection();
            Statement st = conn.createStatement();

            String sql = "SELECT ID,NAME,BIRTHDATE,ADDRESS FROM STUDENT WHERE NAME='" + name + "'";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                StudentModel student = new StudentModel();
                student.setId(rs.getString("ID"));
                student.setName(rs.getString("NAME"));
                student.setBirthDate(rs.getString("BIRTHDATE"));
                student.setAddress(rs.getString("ADDRESS"));

                studentList.add(student);
            }

            return studentList;

        } catch (Exception e) {
            return null;
        }
    }

    /**
     * IDを取得する
     * 
     * @param id ID
     */
    public StudentModel getQueryStudentById(String id) throws TaskException {
        try {
            DBManager dbManager = DBManager.getInstance();
            Connection conn = dbManager.getConnection();
            Statement st = conn.createStatement();

            String sql = "SELECT ID,NAME,BIRTHDATE,ADDRESS FROM STUDENT WHERE ID=" + "'" + id + "'";
            ResultSet rs = st.executeQuery(sql);
            StudentModel student = new StudentModel();
            while (rs.next()) {
                student.setId(rs.getString("ID"));
                student.setName(rs.getString("NAME"));
                student.setBirthDate(rs.getString("BIRTHDATE"));
                student.setAddress(rs.getString("ADDRESS"));
            }
            return student;
        } catch (Exception e) {
            return null;
        }
       
    }

}
