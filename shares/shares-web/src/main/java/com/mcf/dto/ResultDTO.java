package com.mcf.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultDTO<T> {
	/**
	 * 0 成功,非0为错误
	 */
	@JsonProperty("error_code")
	private Integer errorCode=0;
	/**
	 * 单条数据是object，多条数据是array，接口返回不能因为某个key的值是null而去掉key，应保留
	 */
	@JsonProperty("data")
	private T data;
	

	/**
	 * 结果总数
	 */
	@JsonProperty("total_records")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer totalRecords;
	

	/**
	 * 总页数
	 */
	@JsonProperty("total_pages")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer totalPages;
	
	
	/**
	 * 每页结果数量
	 */
	@JsonProperty("records_perpage")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer recordsPerpage;

	/**
	 * 当前页码
	 */
	@JsonProperty("current_page")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer currentPage;
	
	
	
	public ResultDTO(T data) {
		this.data = data;
	}
	

	public ResultDTO( T data, Integer totalRecords) {
		this.data = data;
		this.totalRecords = totalRecords;
	}
	
	public ResultDTO( T data, Integer totalRecords, Integer recordsPerpage) {
		this.data = data;
		this.totalRecords = totalRecords;
		this.recordsPerpage = recordsPerpage;
	}
	public ResultDTO(T data, Integer totalRecords, Integer recordsPerpage, Integer currentPage) {
		this.data = data;
		this.totalRecords = totalRecords;
		this.recordsPerpage = recordsPerpage;
		this.currentPage = currentPage;
	}
	
	public ResultDTO(T data, Integer totalRecords,Integer totalPages, Integer recordsPerpage, Integer currentPage) {
		this.data = data;
		this.totalRecords = totalRecords;
		this.totalPages=totalPages;
		this.recordsPerpage = recordsPerpage;
		this.currentPage = currentPage;
	}


	public int getErrorCode() {
		return errorCode;
	}


	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}


	public T getData() {
		return data;
	}


	public void setData(T data) {
		this.data = data;
	}


	public Integer getTotalRecords() {
		return totalRecords;
	}


	public void setTotalRecords(Integer totalRecords) {
		this.totalRecords = totalRecords;
	}


	public Integer getRecordsPerpage() {
		return recordsPerpage;
	}


	public void setRecordsPerpage(Integer recordsPerpage) {
		this.recordsPerpage = recordsPerpage;
	}


	public Integer getCurrentPage() {
		return currentPage;
	}


	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}


	public Integer getTotalPages() {
		return totalPages;
	}


	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}
}
