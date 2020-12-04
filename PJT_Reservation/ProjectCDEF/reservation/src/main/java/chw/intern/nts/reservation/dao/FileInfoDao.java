package chw.intern.nts.reservation.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import chw.intern.nts.reservation.entity.FileInfo;

@Repository
public class FileInfoDao {
	private NamedParameterJdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert insertFileInfoAction;

	public FileInfoDao(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		this.insertFileInfoAction = new SimpleJdbcInsert(dataSource).withTableName("file_info")
				.usingGeneratedKeyColumns("id");
	}

	public int insertFileInfo(FileInfo fileInfo) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(fileInfo);

		return insertFileInfoAction.executeAndReturnKey(params).intValue();
	}
}
