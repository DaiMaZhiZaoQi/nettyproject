package com.sx.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.sx.pojo.User;
import com.sx.pojo.Userfile;
import com.sx.pojo.UserfileExample;

public interface UserfileMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userfile
     *
     * @mbg.generated Tue Sep 05 17:02:20 CST 2017
     */
    long countByExample(UserfileExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userfile
     *
     * @mbg.generated Tue Sep 05 17:02:20 CST 2017
     */
    int deleteByExample(UserfileExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userfile
     *
     * @mbg.generated Tue Sep 05 17:02:20 CST 2017
     */
    int deleteByPrimaryKey(Integer fileid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userfile
     *
     * @mbg.generated Tue Sep 05 17:02:20 CST 2017
     */
    int insert(Userfile record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userfile
     *
     * @mbg.generated Tue Sep 05 17:02:20 CST 2017
     */
    int insertSelective(Userfile record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userfile
     *
     * @mbg.generated Tue Sep 05 17:02:20 CST 2017
     */
    List<Userfile> selectByExampleWithRowbounds(UserfileExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userfile
     *
     * @mbg.generated Tue Sep 05 17:02:20 CST 2017
     */
    List<Userfile> selectByExample(UserfileExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userfile
     *
     * @mbg.generated Tue Sep 05 17:02:20 CST 2017
     */
    Userfile selectByPrimaryKey(Integer fileid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userfile
     *
     * @mbg.generated Tue Sep 05 17:02:20 CST 2017
     */
    int updateByExampleSelective(@Param("record") Userfile record, @Param("example") UserfileExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userfile
     *
     * @mbg.generated Tue Sep 05 17:02:20 CST 2017
     */
    int updateByExample(@Param("record") Userfile record, @Param("example") UserfileExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userfile
     *
     * @mbg.generated Tue Sep 05 17:02:20 CST 2017
     */
    int updateByPrimaryKeySelective(Userfile record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userfile
     *
     * @mbg.generated Tue Sep 05 17:02:20 CST 2017
     */
    int updateByPrimaryKey(Userfile record);
    
    List<Userfile> selectUserFileByUserId(User user);
    
}