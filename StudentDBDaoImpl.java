/*******************************************************************************
 *  �V�X�e���� : �w�����Ǘ�
 *  ���쌠    : Copyright (C)�@2002-2008�@Realsys Co. Ltd. �@All Rights Reserved.
 *  ��Ж�    : ���A���V�X�������
 *  ****************************************************************************
 *  �ύX����
 *  2008/03/20  �쐬�@
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
 * �w�����f�[�^�x�[�X�̃C���^�t�F�[�X�̎����N���X
 * 
 * @author Realsys
 */
public class StudentDBDaoImpl implements StudentDao {

    /**
     * �w������o�^����
     * 
     * @param student �w����񃂃f��
     * @return ���R�[�h��
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
     * �w�������X�V����
     * 
     * @param student �w����񃂃f��
     * @return ���R�[�h��
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
     * �w�������폜����
     * 
     * @param studentId �w��ID
     * @return ���R�[�h��
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
     * �w��������������
     * 
     * @param name ���O
     * @return �w�����
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
     * ID���擾����
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
