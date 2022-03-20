package com.hk.sns.dtos;

import java.util.Date;

import lombok.Data;

@Data
public class AnsDto {

	/**데이터를 질렬화하여 전송하면 안전하게 전달할 수 있다.
	 * 질렬화: Serializable을 구현한다.
	 */
	private static final long serialVersionUID = 1L;

	private int seq;
	private String id;
	private String title;
	private String content;
	private Date regdate;
	private String regdateStr;
	private int refer;
	private int step;
	private int dep;
	private int readcount;
	private String delflag;
	private String report;




}