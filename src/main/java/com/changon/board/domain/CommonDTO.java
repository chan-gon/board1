package com.changon.board.domain;

import java.util.Date;

import com.changon.board.paging.Criteria;
import com.changon.board.paging.PaginationInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonDTO extends Criteria {

	/** 페이징 정보 */
	private PaginationInfo paginationInfo;

	/** 삭제 여부 */
	private String deleteYn;

	/** 등록일 */
	private Date insertTime;

	/** 수정일 */
	private Date updateTime;

	/** 삭제일 */
	private Date deleteTime;

}
