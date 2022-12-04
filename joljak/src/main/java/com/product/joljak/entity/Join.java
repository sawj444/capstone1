package com.product.joljak.entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Join {
    private Connection conn;
    private ResultSet rs;

    public Join() { // DB 연결문
        try { // 밑의 변수는 properties 파일을 활용해 꽁꽁 숨겨주세요.
            String dbURL = "DB 주소";
            String dbID = "계정 아이디";
            String dbPW = "계정 비번";
            
            Class.forName(""); // 예) com.mysql.cj.jdbc.Driver
            conn = DriverManager.getConnection(dbURL, dbID, dbPW);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public int join(String 대충아이디, String 대충비번, String 대충닉네임, String 대충이메일) {
        String sql = "INSERT INTO MEMBER(MID, MPWD, MNAME, MMAIL, MMCODE) VALUES(?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, 대충아이디);
            pstmt.setString(2, 대충비번);
            pstmt.setString(3, 대충닉네임);
            pstmt.setString(4, 대충이메일);
        } catch(Exception e) {
            e.printStackTrace();
        } return -1; //DB오류
    }

    public int mailCheck(String 대충이메일, String 대충메일코드) {
        String sql = "SELECT MMAIL FROM MEMBER WHERE MMAIL = (?)";
        try {
            //이메일 중복체크
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, 대충이메일);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                if(rs.getString(1).equals(대충이메일)) {
                    //대충 이미 등록된 이메일
                    return -1;
                } else {
                    //이메일 코드 전송
                    //GMAIL 혹은 네이버에서 메일을 전송해주는 더미 계정을 만들고 전송 API 를 이용해 인증 코드 발송 구현
                    //INSERT 문을 사용해 DB에 메일 인증코드 및 이메일 저장

                    //이메일 코드 유효성 확인
                    String sql_2 = "SELECT MMAIL FROM MEMBER WHERE MMAIL = (?)";
                    try {
                        PreparedStatement pstmt2 = conn.prepareStatement(sql_2);
                        pstmt2.setString(1, 대충이메일);
                        rs = pstmt2.executeQuery();
                        while(rs.next()) {
                            String dbMail = rs.getString(1);
                            if(rs.getString(2).equals(대충메일코드)) {
                                //메일 코드 일치, 메일 인증 성공
                                return 1;
                            } else {
                                //메일 코드 불일치, 메일 인증 실패
                                //DELETE를 활용해 등록했던 이메일 및 이메일 인증코드 삭제
                                return 0;
                            }
                        }
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return -2; //DB오류
    }
}
